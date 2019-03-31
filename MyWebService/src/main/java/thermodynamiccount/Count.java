package thermodynamiccount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import readFileAndSendKafka.ReadFileSendKafka;
import static time.Time.getdispart;

public class Count {
	
	public static Logger logger = Logger.getLogger("ThermodynamicCount");
	
	public static void main(String[] args) throws IOException  {
		String startflag = PM.pps.getProperty("ThermodynamicCount.startoncetime");
		
		//设置日志相关信息
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler(PM.pps.getProperty("ThermodynamicCount.logpath"));
			SimpleFormatter sf = new SimpleFormatter();
			fileHandler.setFormatter(sf);
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.log(Level.INFO,"start find disparity");
		logger.log(Level.INFO,"find disparity");
		
		//
		long disparity = getdispart();
		while (true) {
			long thattime = System.currentTimeMillis()/1000-disparity;
			if(thattime%3600<=10||"yes".equals(startflag)) {
				try {
					logger.log(Level.INFO,"start count:"+(thattime-3600));
					countThe(thattime-3600);
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					logger.log(Level.WARNING,"error:"+e);
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if("yes".equals(startflag)) break;
		}
	}
	
	public static void countThe(long time) {
		long starttime = time-7200;
		String result = HbaseUtil.scanIndexByOrder(String.valueOf(starttime),String.valueOf(time));
		String[] split = result.split("\n");
		Hashtable<String, Integer> startmap = new Hashtable<String, Integer>();
		Hashtable<String, Integer> stopmap = new Hashtable<String, Integer>();
		for (String string : split) {
			String[] split2 = string.split(":");
			if(split2.length<3) continue;
			String key = split2[0] + "_" + split2[1];
			String orderInfo = HbaseUtil.getOrderInfo(key);
			if(orderInfo==null||orderInfo.length()==0) continue;
			Double startlat;
			Double startlon;
			Double endlat;
			Double endlon;
			try {
				startlat = Double.valueOf(orderInfo.split(":")[2]);
				startlon = Double.valueOf(orderInfo.split(":")[3]);
				endlat = Double.valueOf(orderInfo.split(":")[5]);
				endlon = Double.valueOf(orderInfo.split(":")[6]);
			} catch (Exception e) {
				continue;
			}
			String startGeohash = GeoHashUtil.getHash(startlat, startlon, 28);
			String stopGeohash = GeoHashUtil.getHash(endlat, endlon, 28);
			Integer startcount = startmap.getOrDefault(startGeohash, 0);
			startmap.put(startGeohash, startcount+1);
			Integer stopcount = stopmap.getOrDefault(startGeohash, 0);
			stopmap.put(stopGeohash, stopcount+1);
		}
		long keytime = starttime;
		for(Iterator<String> iterator=startmap.keySet().iterator();iterator.hasNext();){
			String key=iterator.next();
			if(key.length()>=6)
			{		
				HbaseUtil.updateThermodynamic(keytime+"_"+key+"_1", startmap.get(key), keytime, 1);
				logger.log(Level.INFO, "update1:" + key);
			}
		}
		for(Iterator<String> iterator=stopmap.keySet().iterator();iterator.hasNext();){
			String key=iterator.next();
			if(key.length()>=6)
			{		
				HbaseUtil.updateThermodynamic(keytime+"_"+key+"_2", stopmap.get(key), keytime, 2);
				logger.log(Level.INFO, "update2:" + key);
			}
		}
	}
}
