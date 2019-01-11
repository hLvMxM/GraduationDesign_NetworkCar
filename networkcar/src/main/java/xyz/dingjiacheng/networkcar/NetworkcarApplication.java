package xyz.dingjiacheng.networkcar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("xyz.dingjiacheng.networkcar.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NetworkcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkcarApplication.class, args);
	}

}

