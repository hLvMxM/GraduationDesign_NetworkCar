package xyz.dingjiacheng.hbaseWebService.service.impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import xyz.dingjiacheng.hbaseWebService.service.WebService;
import xyz.dingjiacheng.hbaseWebService.util.HbaseUtil;

@javax.jws.WebService
public class WebServiceImpl implements WebService {
	
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
		String end = start.substring(0,start.length()-1);
		char charAt = start.charAt(start.length()-1);
		charAt += 1;
		return end+charAt;
	}

}
