package writeReadDataToKafka.writeReadDataToKafka;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.dingjiacheng.writeAndReadDataToKafka.service.impl.WebServiceImpl;

@SpringBootApplication
public class WriteReadDataToKafkaApplication {

	public static void main(String[] args) {
		final String[] margs = {"/home/ding/文档/networkcar/"};
        Runnable r1 = new Runnable() {
			
			public void run() {
				try {
					SimpleKafkaProducer.main(margs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Runnable r2 = new Runnable() {
			
			public void run() {
				try {
					String[] margs = {};
					SimpleKafkaConsumer.main(margs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		SpringApplication.run(WriteReadDataToKafkaApplication.class, args);
		String ip = args[0];
		String url = "http://"+ip+":8083/kafka";
		System.out.println(url);
		Endpoint.publish(url,new WebServiceImpl());
		System.out.println("success");
	}

}
