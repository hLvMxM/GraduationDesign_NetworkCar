package xyz.dingjiacheng.hbaseWebService.service;

import javax.jws.WebMethod;


@javax.jws.WebService
public interface WebService {
	
	@WebMethod
	String sayHello(String hello);
	
	@WebMethod
	String scanOrderByDriver(String driverId);
}	
