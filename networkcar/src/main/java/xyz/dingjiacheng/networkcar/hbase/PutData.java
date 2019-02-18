package xyz.dingjiacheng.networkcar.hbase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;


public class PutData {
	
	public static Map<String, String> orderMap = new HashMap<String, String>();
	public static Configuration conf = null;
	public static HTable positionHTable = null;
	public static HTable orderHTable = null;
	public static int error = 0;
	
	public static void main(String[] args) throws Exception {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.property.clientPort", "2181"); 
		conf.set("hbase.zookeeper.quorum", "master"); 
        conf.set("hbase.master", "master:600000");
        positionHTable = new HTable(conf, "positionInfo");
        orderHTable = new HTable(conf, "orderInfo");
		File file = new File(args[0]);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		int count = 0;
		while ((str = br.readLine()) != null) {
			String[] split = str.split(",");
			if (split[5].equals("0")) {
				//writeDataToOrderStart(split);
			}
			if(split[5].equals("2")) {
				//writeDataToOrderEnd(split);
			}
			writeDataToPosition(split);
			count ++;
			if (count%1000==0) {
				System.out.println(count+"\t"+error);
			}
		}
		positionHTable.close();
		orderHTable.close();
		br.close();
		fr.close();
	}
	

	
	public static void writeDataToPosition(String[] split) throws Exception {
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
			error++;
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
