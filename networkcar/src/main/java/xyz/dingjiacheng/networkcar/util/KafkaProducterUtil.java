package xyz.dingjiacheng.networkcar.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

//向kafka发送message的Util工具
public class KafkaProducterUtil {
	private static KafkaTemplate<String, String> kafkaTemplate;
	static {
		try {
			Properties prop = new Properties();
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/application.properties"));
			prop.load(bufferedReader);
			Map<String, Object> props = new HashMap<>();
			props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, prop.getProperty("spring.kafka.bootstrap-servers"));
			props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			ProducerFactory<String, String> producerFactory = 
					new DefaultKafkaProducerFactory<String, String>(props);
			kafkaTemplate = new KafkaTemplate<String, String>(producerFactory);
			kafkaTemplate.setDefaultTopic(prop.getProperty("spring.kafka.topics"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void SendMessage(String message) {
		kafkaTemplate.sendDefault(message);
	}
	
	public static void flush(String[] args) {
		kafkaTemplate.flush();
	}
	
	
}
