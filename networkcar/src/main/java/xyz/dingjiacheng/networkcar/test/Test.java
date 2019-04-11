package xyz.dingjiacheng.networkcar.test;

import xyz.dingjiacheng.networkcar.webservice.WebServiceImpl;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImplService;

public class Test {

	public static void main(String[] args) {
		WebServiceImplService webServiceImplService = new WebServiceImplService();
		WebServiceImpl webServiceImplPort = webServiceImplService.getWebServiceImplPort();
		String pre = webServiceImplPort.getPre();
		System.out.println(pre);
	}

}
