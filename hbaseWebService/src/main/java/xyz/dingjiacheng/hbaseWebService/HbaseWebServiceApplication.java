package xyz.dingjiacheng.hbaseWebService;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImpl;

@SpringBootApplication
public class HbaseWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbaseWebServiceApplication.class, args);
		String ip = args[0];
		String url = "http://"+ip+":8081/wsServeice";
		System.out.println(url);
		Endpoint.publish(url,new WebServiceImpl());
		System.out.println("success");
	}

}

