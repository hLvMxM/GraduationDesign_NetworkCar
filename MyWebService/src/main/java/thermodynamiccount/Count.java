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

public class Count {
	
	public static Logger logger = Logger.getLogger("ThermodynamicCount");
	
	
	
	public static void main(String[] args) throws IOException  {
		String startflag = PM.pps.getProperty("ThermodynamicCount.startoncetime");
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
		File file = new File(PM.pps.getProperty("ReadFileSendKafka.writeProgress"));
		FileReader fr;
		long disparity;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String readLine = br.readLine();
		if(readLine==null) {
			disparity = System.currentTimeMillis()/1000 - 1475251327L;
		}else {
			String time = readLine.split(",")[1];
			br.close();fr.close();
			disparity = System.currentTimeMillis()/1000-Long.valueOf(time);
		}
		} catch (FileNotFoundException e1) {
			disparity = System.currentTimeMillis()/1000- 1475251327L;
		}
		while (true) {
			long thattime = System.currentTimeMillis()/1000-disparity;
			logger.log(Level.INFO,"time is "+thattime+"|disparity is "+disparity);
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
		long starttime = time-3600;
		String result = HbaseUtil.scanIndexByOrder(String.valueOf(starttime),String.valueOf(time));
		String[] split = result.split("\n");
		Hashtable<String, Integer> startmap = new Hashtable<String, Integer>();
		Hashtable<String, Integer> stopmap = new Hashtable<String, Integer>();
		for (String string : split) {
			String[] split2 = string.split(":");
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
				HbaseUtil.updateThermodynamic(keytime+"_"+key+"_2", startmap.get(key), keytime, 2);
				logger.log(Level.INFO, "update2:" + key);
			}
		}
	}
}
