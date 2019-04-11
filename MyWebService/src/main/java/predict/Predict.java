package predict;

import java.util.Date;
import java.util.HashMap;


import HbaseUtil.HbaseUtil;
import hello.examples.spyne.Application;
import hello.examples.spyne.HelloWorldService;
import time.Time;

public class Predict {
	public double[] weather = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public static HashMap<String, Double> mapdayprepublic = null;
	
	public static void main(String[] args) {
		HelloWorldService helloWorldService = new HelloWorldService();
		Application application = helloWorldService.getApplication();
		Long time = System.currentTimeMillis()/1000-Time.getdispart();
		Long timelast = time - 3600;
		Long timeday1 = time - 3600 * 24;
		Long timeday2 = time - 3600 * 48;
		String timelaststr = HbaseUtil.scanIndexByOrder(timelast.toString(), String.valueOf(timelast + 3600));
		String timeday1str = HbaseUtil.scanIndexByOrder(timeday1.toString(), String.valueOf(timeday1 + 3600));
		String timeday2str = HbaseUtil.scanIndexByOrder(timeday2.toString(), String.valueOf(timeday2 + 3600));
		HashMap<String, Double> maplast = new HashMap<String, Double>();
		HashMap<String, Double> mapday1 = new HashMap<String, Double>();
		HashMap<String, Double> mapday2 = new HashMap<String, Double>();
		HashMap<String, Double> mapdaypre = new HashMap<String, Double>();
		String[] last = timelaststr.split("\n");
		for (String string : last) {
			String[] split = string.split(":");
			String orderInfo = HbaseUtil.getOrderInfo(split[0]+"_"+split[1]);
			
			String[] split2 = orderInfo.split(":");
			if (split2[0].equals("null")) {
				continue;
			}
			Double valueOf = Double.valueOf(split2[2]);
			Double valueOf2 = Double.valueOf(split2[3]);
			Double myknnpre = application.myknnpre(valueOf, valueOf2);
			Integer valueOf3 = (int)myknnpre.floatValue();
			Double orDefault = maplast.getOrDefault(valueOf3.toString(), (double) 0);
			maplast.put(valueOf3.toString(), orDefault+1);
		}

		last = timeday1str.split("\n");
		for (String string : last) {
			String[] split = string.split(":");
			String orderInfo = HbaseUtil.getOrderInfo(split[0]+"_"+split[1]);
			String[] split2 = orderInfo.split(":");
			Double valueOf = Double.valueOf(split2[2]);
			Double valueOf2 = Double.valueOf(split2[3]);
			Double myknnpre = application.myknnpre(valueOf, valueOf2);
			Integer valueOf3 = (int)myknnpre.floatValue();
			Double orDefault = mapday1.getOrDefault(valueOf3.toString(), (double) 0);
			mapday1.put(valueOf3.toString(), orDefault+1);
		}

		last = timeday2str.split("\n");
		for (String string : last) {
			String[] split = string.split(":");
			String orderInfo = HbaseUtil.getOrderInfo(split[0]+"_"+split[1]);
			String[] split2 = orderInfo.split(":");
			Double valueOf = Double.valueOf(split2[2]);
			Double valueOf2 = Double.valueOf(split2[3]);
			Double myknnpre = application.myknnpre(valueOf, valueOf2);
			Integer valueOf3 = (int)myknnpre.floatValue();
			Double orDefault = mapday2.getOrDefault(valueOf3.toString(), (double) 0);
			mapday2.put(valueOf3.toString(), orDefault+1);
		}
		for (int i = 1; i <= 50; i++) {
			Double orDefault = mapday1.getOrDefault(String.valueOf(i), 0.0);
			Double mymlppre = application.mymlppre(orDefault, mapday2.getOrDefault(String.valueOf(i), 0.0), 
					maplast.getOrDefault(String.valueOf(i), 0.0), 0.0, Double.valueOf(new Date(time).getHours()), Double.valueOf(i));
			mapdaypre.put(String.valueOf(i), mymlppre);
		}
		mapdayprepublic = mapdaypre;
	}
	
	public static String getPre() {
		StringBuilder stringBuilder = new StringBuilder("");
		for (int i = 1; i <= 50; i++) {
			Double value = mapdayprepublic.getOrDefault(String.valueOf(i), 0.0);
			stringBuilder.append(i+":"+value+"\n");
		}
		return stringBuilder.toString();
	}
}
