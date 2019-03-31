package time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import Properties.PM;

public class Time {
	public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<String, Long>();
	public static boolean initTime() {
		File file = new File(PM.pps.getProperty("ReadFileSendKafka.writeProgress"));
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			if(line==null||"".equals(line)) return false;
			String string = line.split(",")[1];
			Long pasttime = Long.valueOf(string);
			Long d = System.currentTimeMillis()/1000-pasttime;
			map.put("time",d);
			map.put("file",Long.valueOf(line.split(",")[0]));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public static long getdispart() {
		return map.get("time");
	}
	
	public static long getnum() {
		return map.get("file");
	}
}
