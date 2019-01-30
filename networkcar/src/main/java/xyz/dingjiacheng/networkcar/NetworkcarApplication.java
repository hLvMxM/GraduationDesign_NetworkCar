package xyz.dingjiacheng.networkcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import xyz.dingjiacheng.networkcar.util.DBUtil;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NetworkcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkcarApplication.class, args);
	}
	
	
}

