package xyz.dingjiacheng.networkcar.service;

import xyz.dingjiacheng.networkcar.webservice.WebServiceImpl;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImplService;

public class CountService {
	public static String getCount() {
		WebServiceImplService wsis = new WebServiceImplService();
		WebServiceImpl wsi = wsis.getWebServiceImplPort();
		String getdoingcount = wsi.getdoingcount();
		return getdoingcount;
	}
	
	public static String scanCount(Long stattime,Long stoptime) {
		WebServiceImplService wsis = new WebServiceImplService();
		WebServiceImpl wsi = wsis.getWebServiceImplPort();
		String getdoingcount = wsi.scancount(stattime, stoptime);
		return getdoingcount;
	}
	
	public static void main(String[] args) {
		System.out.println(scanCount(1475500682L,1475503682L));
	}
}
