package xyz.dingjiacheng.writeAndReadDataToKafka.service;

import javax.jws.WebMethod;


@javax.jws.WebService
public interface WebService {
	
	@WebMethod
	String getPosition();
	
}	
