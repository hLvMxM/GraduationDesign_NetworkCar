package xyz.dingjiacheng.hbaseWebService.service;

import javax.jws.WebMethod;


@javax.jws.WebService
public interface WebService {
	
	@WebMethod
	String sayHello(String hello);
	
	@WebMethod
	String scanOrderByDriver(String driverId);
	
	@WebMethod
	String scanPositionByOrder(String orderId);
	
	@WebMethod
	String updateData(String data);
}	
