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
import static time.Time.getdispart;
import static time.Time.getnum;

public class ReadFileSendKafka {
	
	public static Logger logger = Logger.getLogger("readFileAndSendKafka.ReadFileSendKafka"); 
	public static Long disparity = null;
	public static Long filenum = null;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SimpleKafkaProducer skp = new SimpleKafkaProducer();
		//logger设置
		FileHandler fileHandler = new FileHandler(PM.pps.getProperty("ReadFileSendKafka.logpath"));
		SimpleFormatter sf = new SimpleFormatter();
		fileHandler.setFormatter(sf);
		logger.addHandler(fileHandler);
		logger.setLevel(Level.OFF);
		
		//
		
		filenum = getnum();
		disparity = getdispart();
		long startTime = System.currentTimeMillis()/1000 - disparity;
		logger.info("The time disparity is:"+disparity+",time is:"+startTime);
		
		boolean initflag = false;
		for (int i = Integer.valueOf(filenum.toString()); i <= 31; i++) {
			String num = String.format("%02d", i);
			File readFile = new File(PM.pps.getProperty("ReadFileSendKafka.gpsfile")+num);
			FileReader fr = new FileReader(readFile);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine())!=null) {
				Long time = Long.valueOf(str.split(",")[2]);
				if(!initflag) {//未初始化
					if(startTime-1>time) continue;
					logger.info("init done,time is :"+startTime);
					initflag = true;
				}else {//已经初始化
					skp.produce(str);
					File lckfile = new File(PM.pps.getProperty("ReadFileSendKafka.writeProgressRenameFrom"));
					FileWriter fw = new FileWriter(lckfile,false);
					fw.write(i+","+time+"\r\n");
					fw.flush();
					fw.close();
					lckfile.renameTo(new File(PM.pps.getProperty("ReadFileSendKafka.writeProgress")));
					logger.info("send:"+str+",time is "+ startTime);
					if(startTime < time) {
						Thread.sleep(1000);
					}
					long nowTime = (System.currentTimeMillis()/1000);
					startTime = nowTime - disparity;
				}
			}
			br.close();
			fr.close();
		}
	}
}
