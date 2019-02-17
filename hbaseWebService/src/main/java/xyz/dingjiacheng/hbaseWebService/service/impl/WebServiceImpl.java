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

	public static void main(String[] args) {
		WebServiceImpl ws = new WebServiceImpl();
		ws.scanOrderByDriver("123");
	}
	
	@Override
	public String scanOrderByDriver(String driverId) {
		StringBuilder sb = new StringBuilder("");
		HTable orderHTable = HbaseUtil.getOrderHTable();
		Scan scan = new Scan();
		scan.withStartRow(driverId.getBytes());
		scan.withStopRow(findEnd(driverId).getBytes());
		scan.addColumn("orderID".getBytes(), null);
		try {
			ResultScanner scanner = orderHTable.getScanner(scan);
			for (Result result : scanner) {
				String string = Bytes.toString(result.getValue("orderID".getBytes(), null));
				sb.append(string +"\n");
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
		try {
			ResultScanner scanner = positionTable.getScanner(scan);
			for (Result result : scanner) {
				//String string = Bytes.toString(result.getValue("orderID".getBytes(), null));
				sb.append(result +"\n");
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
