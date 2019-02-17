package xyz.dingjiacheng.networkcar.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import xyz.dingjiacheng.hbasewebservice.service.impl.WebServiceImpl;
import xyz.dingjiacheng.hbasewebservice.service.impl.WebServiceImplService;

public class ScanService {
	public static String getOrder(String driverID) {
		WebServiceImplService service = new WebServiceImplService();
		WebServiceImpl port = service.getWebServiceImplPort();
		String orderId = port.scanPositionByOrder(driverID);
		System.out.println("null"+orderId);
		return orderId;
	}
	
	public static void main(String[] args) {
		System.out.println(getOrder("46f5f1328a997fd338ab8d8625833efb"));
	}
}
