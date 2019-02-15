package xyz.dingjiacheng.hbaseWebService;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImpl;

@SpringBootApplication
public class HbaseWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbaseWebServiceApplication.class, args);
		String url = "http://192.168.201.2:8081/wsServeice";
		Endpoint.publish(url,new WebServiceImpl());
		System.out.println("success");
	}

}

