package xyz.dingjiacheng.networkcar.service;

import xyz.dingjiacheng.networkcar.webservice.WebServiceImpl;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImplService;

public class thermodynamicService {
	public static String scanthermodynamic(Long starttime,Long stoptime) {
		WebServiceImplService wsis = new WebServiceImplService();
		WebServiceImpl wsi = wsis.getWebServiceImplPort();
		String scanthermodynamic = wsi.scanthermodynamic(starttime,stoptime);
		return scanthermodynamic;
	}
}
