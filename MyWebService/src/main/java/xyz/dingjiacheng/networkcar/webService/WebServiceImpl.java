package xyz.dingjiacheng.networkcar.webService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import WebService.WebService;
import consumerKafkaToHashMap.SimpleKafkaConsumer;

@javax.jws.WebService
public class WebServiceImpl implements WebService{

	@Override
	public String getPosition() {
		return SimpleKafkaConsumer.getPositionString();
	}

	@Override
	public String scanPositionByOrder(String orderid) {
		return HbaseUtil.scanPositionByOrder(orderid);
	}

	@Override
	public String scanIndexByOrder(String time,String endtime) {
		return HbaseUtil.scanIndexByOrder(time,endtime);
	}

	@Override
	public String scanOrderByDriver(String driverid) {
		return HbaseUtil.scanOrderByDriver(driverid);
	}

	@Override
	public String scanthermodynamic(Long starttime, Long stoptime) {
		return HbaseUtil.scanthermodynamic(starttime, stoptime);
	}

	@Override
	public String getdoingcount() {
		return HbaseUtil.getdoingcount();
	}

	@Override
	public String scancount(Long starttime,Long stoptime) {
		return HbaseUtil.scancount(starttime, stoptime);
	}

	@Override
	public String getnowTime() {
		String property = PM.pps.getProperty("ReadFileSendKafka.writeProgress");
		File file = new File(property);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String readLine = br.readLine();
			if (readLine!=null) {
				return readLine.split(",")[1];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getDoingNumberAndCount(long time) {
		String doing = HbaseUtil.getdoingcount();
		String tmpString = HbaseUtil.getDone(time);
		String done = tmpString.split(":")[1];
		String newdriver = tmpString.split(":")[0];
		return "{\"doing\":"+doing+",\"done\":"+done+",\"newdriver\":"+newdriver+"}";
	}
	
	

}
