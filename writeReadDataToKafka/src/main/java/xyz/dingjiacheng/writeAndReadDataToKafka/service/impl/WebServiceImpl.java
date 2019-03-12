package xyz.dingjiacheng.writeAndReadDataToKafka.service.impl;

import writeReadDataToKafka.writeReadDataToKafka.SimpleKafkaConsumer;
import xyz.dingjiacheng.writeAndReadDataToKafka.service.WebService;

@javax.jws.WebService
public class WebServiceImpl implements WebService{

	@Override
	public String getPosition() {
		return SimpleKafkaConsumer.getPositionString();
	}
	
}
