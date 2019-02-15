package xyz.dingjiacheng.networkcar.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;

public class FindData {
	public static void main(String[] args) throws Exception {
		String tmp = "458e5a29d87d07be7a617f48698dc9fe";
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.property.clientPort", "2181"); 
        conf.set("hbase.zookeeper.quorum", "192.168.201.2"); 
        conf.set("hbase.master", "192.168.201.2:600000");
        HTable hTable = new HTable(conf, "orderInfo");
		PrefixFilter pff = new PrefixFilter(tmp.getBytes());
		Scan scan = new Scan();
		scan.setFilter(pff);
		scan.setStartRow(tmp.getBytes());
		scan.setStopRow((tmp+"1").getBytes());
		ResultScanner scanner = hTable.getScanner(scan);
		for (Result result = scanner.next(); result != null; result = scanner.next())
		      System.out.println("Found row : " + result);
	}
}
