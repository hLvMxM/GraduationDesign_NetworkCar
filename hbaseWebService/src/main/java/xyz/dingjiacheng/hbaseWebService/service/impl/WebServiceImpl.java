package xyz.dingjiacheng.hbaseWebService.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import xyz.dingjiacheng.hbaseWebService.service.WebService;
import xyz.dingjiacheng.hbaseWebService.util.HbaseUtil;

@javax.jws.WebService
public class WebServiceImpl implements WebService {
	public static Map<String, String> orderMap = new HashMap<String, String>();
	public static Configuration conf = HbaseUtil.conf;
	public static HTable positionHTable = HbaseUtil.positionTable;
	public static HTable orderHTable = HbaseUtil.orderTable;
	
	@Override
	public String sayHello(String hello) {
		System.out.println("hello");
		return "hello:" + hello;
	}
	
	@Override
	public String scanOrderByDriver(String driverId) {
		StringBuilder sb = new StringBuilder("");
		HTable orderHTable = HbaseUtil.getOrderHTable();
		Scan scan = new Scan();
		scan.withStartRow(driverId.getBytes());
		scan.withStopRow(findEnd(driverId).getBytes());
		scan.addColumn("orderID".getBytes(), null);
		scan.addColumn("time".getBytes(), null);
		scan.addColumn("state".getBytes(), "state".getBytes());
		try {
			ResultScanner scanner = orderHTable.getScanner(scan);
			for (Result result : scanner) {
				String string = Bytes.toString(result.getValue("orderID".getBytes(), null));
				String time = Bytes.toString(result.getValue("time".getBytes(), null));
				String state = Bytes.toString(result.getValue("state".getBytes(), "state".getBytes()));
				sb.append(string  + ":" + time + ":" + state +"\n");
			}
		} catch (IOException e) {
			return "error";
		}
		return sb.toString();
	}
	
	@Override
	public String scanPositionByOrder(String orderId) {
		StringBuilder sb = new StringBuilder("");
		HTable positionTable = HbaseUtil.getPositionHTable();
		Scan scan = new Scan();
		scan.withStartRow(orderId.getBytes());
		scan.withStopRow(findEnd(orderId).getBytes());
		scan.addColumn("position".getBytes(), "lat".getBytes());
		scan.addColumn("position".getBytes(), "lon".getBytes());
		scan.addColumn("time".getBytes(), null);
		try {
			ResultScanner scanner = positionTable.getScanner(scan);
			for (Result result : scanner) {
				String lat = Bytes.toString(result.getValue("position".getBytes(), "lat".getBytes()));
				String lon = Bytes.toString(result.getValue("position".getBytes(), "lon".getBytes()));
				String time = Bytes.toString(result.getValue("time".getBytes(), null));
				sb.append(lat  + ":" + lon + ":" + time +"\n");
			}
		} catch (IOException e) {
			return "error";
		}
		return sb.toString();
	}
	
	private static String findEnd(String start) {
		if(start.length()==0)
			return "";
		String end = start.substring(0,start.length()-1);
		char charAt = start.charAt(start.length()-1);
		charAt += 1;
		return end+charAt;
	}

	@Override
	public String updateData(String data) {
		String[] split = data.split(",");
		try {
			if (split[5].equals("0")) {
				writeDataToOrderStart(split);
			}
			if(split[5].equals("2")) {
				writeDataToOrderEnd(split);
			}
			writeDataToPosition(split);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	public void writeDataToPosition(String[] split) throws Exception {
		String driverID = split[0];
		String orderID = split[1];
		String time = split[2];
		String positionlat = split[3];
		String positionlon = split[4];
		String state = split[5];
		String key = orderID+"_"+time;
		Put p = new Put(tb(key));
		p.add(tb("driverID"), null, tb(driverID));
		p.add(tb("orderID"), null, tb(orderID));
		p.add(tb("position"), tb("lat"), tb(positionlat));
		p.add(tb("position"), tb("lon"), tb(positionlon));
		p.add(tb("time"), null, tb(time));
		positionHTable.put(p);
	}

	public static void writeDataToOrderStart(String[] split) throws Exception {
		String driverID = split[0];
		String orderID = split[1];
		String time = split[2];
		String positionlat = split[3];
		String positionlon = split[4];
		String state = split[5];
		String key = driverID+"_"+orderID+"_"+time;
		orderMap.put(orderID, key);
		Put p = new Put(tb(key));
		p.add(tb("driverID"), null, tb(driverID));
		p.add(tb("orderID"), null, tb(orderID));
		p.add(tb("state"),tb("state"),tb("1"));
		p.add(tb("state"),tb("startlat"),tb(positionlat));
		p.add(tb("state"),tb("startlon"),tb(positionlon));
		p.add(tb("time"), null, tb(time));
		orderHTable.put(p);
	}
	
	public static void writeDataToOrderEnd(String[] split) throws IOException {
		String driverID = split[0];
		String orderID = split[1];
		String time = split[2];
		String positionlat = split[3];
		String positionlon = split[4];
		String state = split[5];
		String key = orderMap.get(orderID);
		if(key==null) {
			return;
		}
		orderMap.remove(key);
		Put p = new Put(tb(key));
		p.add(tb("state"),tb("state"),tb("2"));
		p.add(tb("state"),tb("endlat"),tb(positionlat));
		p.add(tb("state"),tb("endlon"),tb(positionlon));
		orderHTable.put(p);
	}
	
	public static byte[] tb(String string) {
		return Bytes.toBytes(string);
	}
}
