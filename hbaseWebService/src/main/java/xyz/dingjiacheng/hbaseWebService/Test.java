package xyz.dingjiacheng.hbaseWebService;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterBase;

import xyz.dingjiacheng.hbaseWebService.util.HbaseUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println(new Date().getTime());
		HTable ot = HbaseUtil.getOrderHTable();
		Scan scan = new Scan();
		ResultScanner scanner = ot.getScanner(scan);
		int count = 0;
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			if(count <= 10) {
				System.out.println(result);
			}
			count ++;
		}
		System.out.println(new Date().getTime());
		scanner.close();
	}

	
}
