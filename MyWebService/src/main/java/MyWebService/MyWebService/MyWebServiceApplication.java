package MyWebService.MyWebService;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import SparkCount.SparkCount;
import consumerKafkaToHashMap.SimpleKafkaConsumer;
import predict.Predict;
import readFileAndSendKafka.ReadFileSendKafka;
import thermodynamiccount.Count;
import time.Time;
import xyz.dingjiacheng.networkcar.webService.WebServiceImpl;



@SpringBootApplication
public class MyWebServiceApplication {

	public static boolean isYes(String str) {
		return "yes".equals(str);
	}
	
	public static void main(String[] args)  {
		new PM(args[0]);
		HbaseUtil.initHbaseUtil();
		Time.initTime();
		
		String producekafka = PM.pps.getProperty("Application.producekafka");
		String consumekafka = PM.pps.getProperty("Application.consumekafka");
		String thermodynamiccount = PM.pps.getProperty("Application.thermodynamiccount");
		String webservice = PM.pps.getProperty("Application.webservice");
		String sparkcount = PM.pps.getProperty("Application.sparkcount");
		String prenum = PM.pps.getProperty("Application.prenum");
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean flag = false;
				while (!flag) {
					flag = true;
					try {
						ReadFileSendKafka.main(null);
					} catch (IOException | InterruptedException e) {
						flag = false;
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean True = true;
				while (True) {
					SimpleKafkaConsumer.consumerKafka();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Count.main(null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						SparkCount.main(args);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Thread t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long getdispart = Time.getdispart();
				Predict.main(args);
				while (true) {
					if (new Date(getdispart).getMinutes()==0) {
						Predict.main(args);
						try {
							Thread.sleep(5000 * 20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		if(isYes(producekafka)) t1.start();
		if(isYes(consumekafka)) t2.start();
		if(isYes(thermodynamiccount)) t3.start();
		if(isYes(sparkcount)) t4.start();
		if(isYes(prenum)) t5.start();
		if(isYes(webservice)) {
			SpringApplication.run(MyWebServiceApplication.class, args);
			String url = PM.pps.getProperty("WebServiceUrl");
			Endpoint.publish(url,new WebServiceImpl());
			System.out.println(url+"|success");
		}
		System.out.println("all Done");
	}

}
