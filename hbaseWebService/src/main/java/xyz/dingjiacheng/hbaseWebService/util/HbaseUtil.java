package xyz.dingjiacheng.hbaseWebService.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;

public class HbaseUtil {
	public static Configuration conf = null;
	public static HTable orderTable = null;
	public static HTable positionTable = null;
	
	
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.property.clientPort", "2181"); 
        conf.set("hbase.zookeeper.quorum", "192.168.201.2"); 
        conf.set("hbase.master", "192.168.201.2:600000");
        try {
			orderTable = new HTable(conf, "orderInfo");
			positionTable = new HTable(conf, "positionInfo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HTable getOrderHTable() {
		return orderTable;
	}
	
	public static HTable getPositionHTable() {
		return positionTable;
	}
}
