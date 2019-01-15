package xyz.dingjiacheng.networkcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NetworkcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkcarApplication.class, args);
	}
	
	
}

