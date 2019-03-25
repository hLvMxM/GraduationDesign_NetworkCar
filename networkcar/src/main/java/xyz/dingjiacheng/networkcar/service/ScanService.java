package xyz.dingjiacheng.networkcar.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import xyz.dingjiacheng.networkcar.webservice.WebServiceImpl;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImplService;




public class ScanService {
	public static String getOrder(String driverID) {
		WebServiceImplService service = new WebServiceImplService();
		WebServiceImpl port = service.getWebServiceImplPort();
		String orderId = port.scanOrderByDriver(driverID);
		return orderId;
	}
	
	public static String getPosition(String orderID) {
		WebServiceImplService service = new WebServiceImplService();
		WebServiceImpl port = service.getWebServiceImplPort();
		String positionInfo = port.scanPositionByOrder(orderID);
		return positionInfo;
	}
	
	public static void main(String[] args) {
		System.out.println(getPosition("efac74a1486034190fcc5b474411fa11"));
	}
}
