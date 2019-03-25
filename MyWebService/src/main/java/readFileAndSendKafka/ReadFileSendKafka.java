package readFileAndSendKafka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Properties.PM;

public class ReadFileSendKafka {
	
	public static Logger logger = Logger.getLogger("readFileAndSendKafka.ReadFileSendKafka"); 
	public static Long disparity = null;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SimpleKafkaProducer skp = new SimpleKafkaProducer();
		//logger设置
		FileHandler fileHandler = new FileHandler(PM.pps.getProperty("ReadFileSendKafka.logpath"));
		SimpleFormatter sf = new SimpleFormatter();
		fileHandler.setFormatter(sf);
		logger.addHandler(fileHandler);
		logger.setLevel(Level.OFF);
		logger.log(Level.INFO, "path-->"+PM.pps.getProperty("ReadFileSendKafka.gpsfile"));
		
		File file = new File(PM.pps.getProperty("ReadFileSendKafka.writeProgress"));
		Long nowTime = System.currentTimeMillis()/1000;
		Long startTime = 0L;
		disparity = 0L;
		int i = 1;
		boolean initflag = false;
		if(file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String readLine = br.readLine();
			if(readLine==null||"".equals(readLine)) startTime=0L;
			else {
				String[] split = readLine.split(",");
				i = Integer.valueOf(split[0]);
				startTime = Long.valueOf(split[1]);
			}
		}else {startTime = 0L;}
		logger.log(Level.INFO,"readfile done:time is:"+startTime+"|file is:"+i);
		for (; i <= 31; i++) {
			String num = String.format("%02d", i);
			File readFile = new File(PM.pps.getProperty("ReadFileSendKafka.gpsfile")+num);
			FileReader fr = new FileReader(readFile);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine())!=null) {
				Long time = Long.valueOf(str.split(",")[2]);
				if(!initflag) {//未初始化
					if(startTime-1>time) continue;
					startTime = time;
					nowTime = (System.currentTimeMillis()/1000);
					disparity = nowTime - startTime;
					initflag = true;
					logger.log(Level.INFO, "init done:"+disparity);
				}else {//已经初始化
					skp.produce(str);
					File lckfile = new File(PM.pps.getProperty("ReadFileSendKafka.writeProgressRenameFrom"));
					FileWriter fw = new FileWriter(lckfile,false);
					fw.write(i+","+time+"\r\n");
					fw.flush();
					fw.close();
					lckfile.renameTo(new File(PM.pps.getProperty("ReadFileSendKafka.writeProgress")));
					logger.log(Level.INFO,"send:"+str);
					if(startTime < time) {
						Thread.sleep(1000);
					}
					nowTime = (System.currentTimeMillis()/1000);
					startTime = nowTime - disparity;
				}
			}
			logger.log(Level.INFO,"fileDone:"+i);
			br.close();
			fr.close();
		}
	}
}
