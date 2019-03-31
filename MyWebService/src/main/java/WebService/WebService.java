package WebService;

import javax.jws.WebMethod;


@javax.jws.WebService
public interface WebService {
	
	@WebMethod
	String getPosition();
	
	@WebMethod
	String scanPositionByOrder(String orderid);
	
	@WebMethod
	String scanIndexByOrder(String time,String endtime);
	
	@WebMethod
	String scanOrderByDriver(String driverid);

	@WebMethod
	String scanthermodynamic(Long starttime,Long stoptime);
	
	@WebMethod
	String getdoingcount();

	@WebMethod
	String scancount(Long starttime,Long stoptime);
	
	@WebMethod
	String getnowTime();
	
	@WebMethod
	String getDoingNumberAndCount(long time);
}	
