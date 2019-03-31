package HbaseUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.rabbitmq.client.AMQP.Connection.Start;

import Properties.PM;
import thermodynamiccount.GeoHashUtil;

public class HbaseUtil {
	
	public static Logger logger = Logger.getLogger("HbaseUtil");
	
	public static Configuration conf = null;
	public static HTable orderTable = null;
	public static HTable positionTable = null;
	public static HTable thermodynamic = null;
	public static HTable distinctTable = null;
	public static HTable driverTable = null;
	public static HTable userTable = null;
	public static HTable weatherTable = null;
	public static HTable geohashTable = null;
	public static HTable indexTable = null;
	public static HTable countTable = null;
	
	public static boolean updatePositionTable(String str) {
		String[] split = str.split(",");
		String driverID = split[0];
		String orderID = split[1];
		String time = split[2];
		String positionlat = split[3];
		String positionlon = split[4];
		String state = split[5];
		String key = orderID+"_"+time;
		Put p = new Put(tb(key));
		p.add(tb("driverid"), null, tb(driverID));
		p.add(tb("orderid"), null, tb(orderID));
		p.add(tb("position"), tb("lat"), tb(positionlat));
		p.add(tb("position"), tb("lon"), tb(positionlon));
		p.add(tb("time"), null, tb(time));
		try {
			positionTable.put(p);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateOrderTable(String str) {
		String[] split = str.split(",");
		String driverID = split[0];
		String orderID = split[1];
		String time = split[2];
		String positionlat = split[3];
		String positionlon = split[4];
		String status = split[5];
		String key = driverID+"_"+orderID;
		Put p = new Put(tb(key));
		if("0".equals(status)) {
			p.add(tb("driverid"), null, tb(driverID));
			p.add(tb("orderid"), null, tb(orderID));
			p.add(tb("info"), tb("startlat"), tb(positionlat));
			p.add(tb("info"), tb("startlon"), tb(positionlon));
			p.add(tb("info"), tb("starttime"), tb(time));
			p.add(tb("status"), null, tb("1"));
		}else if("2".equals(status)) {
			p.add(tb("info"), tb("endlat"), tb(positionlat));
			p.add(tb("info"), tb("endlon"), tb(positionlon));
			p.add(tb("info"), tb("endtime"), tb(time));
			p.add(tb("status"), null, tb("2"));
		}
		try {
			orderTable.put(p);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateThermodynamic(String key,int count,long time,int status) {
		Put p = new Put(tb(key));
		p.add(tb("count"), null, tb(String.valueOf(count)));
		p.add(tb("time"), null, tb(String.valueOf(time)));
		p.add(tb("status"), null, tb(String.valueOf(status)));
		double[] mytohash = GeoHashUtil.mytohash(key.split("_")[1]);
		p.add(tb("position"), tb("lat"), tb(String.valueOf(mytohash[0])));
		p.add(tb("position"), tb("lon"), tb(String.valueOf(mytohash[1])));
		try {
			thermodynamic.put(p);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateDistinctInfo(String driverid) {
		Put put = new Put(tb(driverid));
		put.add(tb("value"), null, tb("1"));
		try {
			distinctTable.put(put);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getDistinctInfo(String driverid) {
		Get get = new Get(tb(driverid));
		get.addColumn("value".getBytes(), null);
		try {
			Result result = distinctTable.get(get);
			String value = Bytes.toString(result.getValue("value".getBytes(), null));
			return value;
		} catch (IOException e) {return null;}
	}
	
	public static boolean updateIndexInfo(String str) {
		String[] split = str.split(",");
		String driverID = split[0];
		String orderID = split[1];
		String time = split[2];
		String positionlat = split[3];
		String positionlon = split[4];
		String status = split[5];
		String key;
		String statustmp;
		if("0".equals(status)) {
			statustmp = "1";
			key = time+"_1_"+orderID;
		}else if("2".equals(status)){
			statustmp = "2";
			key = time+"_2_"+orderID;
		}else {
			return true;
		}
		Put p = new Put(tb(key));
		p.add(tb("driverid"), null, tb(driverID));
		p.add(tb("orderid"), null, tb(orderID));
		p.add(tb("status"), null, tb(statustmp));
		try {
			indexTable.put(p);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void sendInfo(String str) {
		String[] split = str.split(",");
		System.out.println(str);
		System.out.println(split[5]);
		if("0".equals(split[5])) {
			if(updateOrderTable(str)) {
				logger.log(Level.INFO, "order"+str);
			}else {
				logger.log(Level.WARNING, "order"+str);
			}
			if(updatePositionTable(str)) {
				logger.log(Level.INFO, "position"+str);
			}else {
				logger.log(Level.WARNING, "position"+str);
			}
			if(updateIndexInfo(str)) {
				logger.log(Level.INFO, "index"+str);
			}else {
				logger.log(Level.WARNING, "index"+str);
			}
		}else if("2".equals(split[5])) {
			if(updateOrderTable(str)) {
				logger.log(Level.INFO, "order"+str);
			}else {
				logger.log(Level.WARNING, "order"+str);
			}
			if(updatePositionTable(str)) {
				logger.log(Level.INFO, "position"+str);
			}else {
				logger.log(Level.WARNING, "position"+str);
			}
			if(updateIndexInfo(str)) {
				logger.log(Level.INFO, "index"+str);
			}else {
				logger.log(Level.WARNING, "index"+str);
			}
		}else {
			if(updatePositionTable(str)) {
				logger.log(Level.INFO, "position"+str);
			}else {
				logger.log(Level.WARNING, "position"+str);
			}
		}
	}
	
	public static HTable getOrderHTable() {
		return orderTable;
	}
	
	public static HTable getPositionHTable() {
		return positionTable;
	}
	
	public static HTable getIndexHTable() {
		return indexTable;
	}
	
	public static byte[] tb(String str) {
		return str.getBytes();
	}
	
	public static void initHbaseUtil() {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.property.clientPort", "2181"); 
        conf.set("hbase.zookeeper.quorum", PM.pps.getProperty("ServiceIP")); 
        conf.set("hbase.master", PM.pps.getProperty("ServiceIP")+":600000");
        try {
			orderTable = new HTable(conf, "orderinfo");
			positionTable = new HTable(conf, "positioninfo");
			indexTable = new HTable(conf, "indexinfo");
			thermodynamic = new HTable(conf, "thermodynamic");
			distinctTable = new HTable(conf, "distinctinfo");
			countTable = new HTable(conf, "countinfo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler(PM.pps.getProperty("HbaseUtil.logpath"));
			SimpleFormatter sf = new SimpleFormatter();
			fileHandler.setFormatter(sf);
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String scanOrderByDriver(String driverId) {
		StringBuilder sb = new StringBuilder("");
		HTable orderHTable = HbaseUtil.getOrderHTable();
		Scan scan = new Scan();
		scan.withStartRow(driverId.getBytes());
		scan.withStopRow(findEnd(driverId).getBytes());
		scan.addColumn("driverid".getBytes(), null);
		scan.addColumn("orderid".getBytes(), null);
		scan.addColumn("info".getBytes(), "startlat".getBytes());
		scan.addColumn("info".getBytes(), "startlon".getBytes());
		scan.addColumn("info".getBytes(), "starttime".getBytes());
		scan.addColumn("info".getBytes(), "endlat".getBytes());
		scan.addColumn("info".getBytes(), "endlon".getBytes());
		scan.addColumn("info".getBytes(), "endtime".getBytes());
		scan.addColumn("status".getBytes(), null);
		try {
			ResultScanner scanner = orderHTable.getScanner(scan);
			for (Result result : scanner) {
				String driverID = Bytes.toString(result.getValue("driverid".getBytes(), null));
				String string = Bytes.toString(result.getValue("orderid".getBytes(), null));
				String startlat = Bytes.toString(result.getValue("info".getBytes(), "startlat".getBytes()));
				String startlon = Bytes.toString(result.getValue("info".getBytes(), "startlon".getBytes()));
				String starttime = Bytes.toString(result.getValue("info".getBytes(), "starttime".getBytes()));
				String endlat = Bytes.toString(result.getValue("info".getBytes(), "endlat".getBytes()));
				String endlon = Bytes.toString(result.getValue("info".getBytes(), "endlon".getBytes()));
				String endtime = Bytes.toString(result.getValue("info".getBytes(), "endtime".getBytes()));
				String status = Bytes.toString(result.getValue("state".getBytes(), null));
				sb.append(driverID + ":" + string  + ":" + startlat + ":" + startlon + ":" + starttime + 
						":" + endlat + ":" + endlon + ":" + endtime + ":" + status + "\n");
			}
		} catch (IOException e) {
			return "error";
		}
		return sb.toString();
	}
	
	
	public static String scanIndexByOrder(String time,String endtime) {
		StringBuilder sb = new StringBuilder("");
		HTable indexTable = HbaseUtil.getIndexHTable();
		Scan scan = new Scan();
		scan.withStartRow(time.getBytes());
		scan.withStopRow(endtime.getBytes());
		scan.addColumn("driverid".getBytes(), null);
		scan.addColumn("orderid".getBytes(), null);
		scan.addColumn("status".getBytes(), null);
		try {
			ResultScanner scanner = indexTable.getScanner(scan);
			for (Result result : scanner) {
				String driverid = Bytes.toString(result.getValue("driverid".getBytes(), null));
				String orderid = Bytes.toString(result.getValue("orderid".getBytes(), null));
				String status = Bytes.toString(result.getValue("status".getBytes(), null));
				sb.append(driverid + ":" + orderid +":" + status + "\n");
			}
		} catch (IOException e) {
			return "error";
		}
		return sb.toString();
	}
	
	public static String scanPositionByOrder(String orderId) {
		StringBuilder sb = new StringBuilder("");
		HTable positionTable = HbaseUtil.getPositionHTable();
		Scan scan = new Scan();
		scan.withStartRow(orderId.getBytes());
		scan.withStopRow(findEnd(orderId).getBytes());
		scan.addColumn("driverid".getBytes(), null);
		scan.addColumn("orderid".getBytes(), null);
		scan.addColumn("position".getBytes(), "lat".getBytes());
		scan.addColumn("position".getBytes(), "lon".getBytes());
		scan.addColumn("time".getBytes(), null);
		try {
			ResultScanner scanner = positionTable.getScanner(scan);
			for (Result result : scanner) {
				String driverid = Bytes.toString(result.getValue("driverid".getBytes(), null));
				String orderid = Bytes.toString(result.getValue("orderid".getBytes(), null));
				String lat = Bytes.toString(result.getValue("position".getBytes(), "lat".getBytes()));
				String lon = Bytes.toString(result.getValue("position".getBytes(), "lon".getBytes()));
				String time = Bytes.toString(result.getValue("time".getBytes(), null));
				sb.append(driverid + ":" + orderid +":" + lat  + ":" + lon + ":" + time +"\n");
			}
		} catch (IOException e) {
			return "error";
		}
		return sb.toString();
	}
		
	public static String scanthermodynamic(Long starttime,Long stoptime) {
		Scan scan = new Scan();
		scan.addColumn("count".getBytes(), null);
		scan.addColumn("position".getBytes(), "lat".getBytes());
		scan.addColumn("position".getBytes(), "lon".getBytes());
		scan.addColumn("status".getBytes(), null);
		scan.withStartRow(starttime.toString().getBytes());
		scan.withStopRow(stoptime.toString().getBytes());
		StringBuilder sb = new StringBuilder("");
		try {
			ResultScanner scanner = thermodynamic.getScanner(scan);
			for (Result result : scanner) {
				String count = Bytes.toString(result.getValue("count".getBytes(), null));
				String lat = Bytes.toString(result.getValue("position".getBytes(), "lat".getBytes()));
				String lon = Bytes.toString(result.getValue("position".getBytes(), "lon".getBytes()));
				String status = Bytes.toString(result.getValue("status".getBytes(), null));
				sb.append(count + ":" + lat + ":" + lon + ":" + status +"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String scancount(Long starttime,Long stoptime) {
		Scan scan = new Scan();
		scan.addColumn("count".getBytes(), null);
		scan.addColumn("type".getBytes(),null);
		scan.setStartRow(starttime.toString().getBytes());
		scan.setStopRow(stoptime.toString().getBytes());
		StringBuilder sb = new StringBuilder("");
		try {
			ResultScanner scanner = countTable.getScanner(scan);
			for (Result result : scanner) {
				String time = Bytes.toString(result.getRow());
				time = time.split("_")[0];
				String count = Bytes.toString(result.getValue("count".getBytes(), null));
				String type = Bytes.toString(result.getValue("type".getBytes(), null));
				sb.append(count + ":" + type +":"+time+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getdoingcount() {
		Get get = new Get("doing".getBytes());
		get.addColumn("count".getBytes(), null);
		String count = "-1";
		try {
			Result result = countTable.get(get);
			count = Bytes.toString(result.getValue("count".getBytes(), null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public static String getOrderInfo(String key) {
		Get get = new Get(key.getBytes());
		get.addColumn("driverid".getBytes(), null);
		get.addColumn("orderid".getBytes(), null);
		get.addColumn("info".getBytes(), "startlat".getBytes());
		get.addColumn("info".getBytes(), "startlon".getBytes());
		get.addColumn("info".getBytes(), "starttime".getBytes());
		get.addColumn("info".getBytes(), "endlat".getBytes());
		get.addColumn("info".getBytes(), "endlon".getBytes());
		get.addColumn("info".getBytes(), "endtime".getBytes());
		get.addColumn("status".getBytes(), null);
		StringBuilder sb = new StringBuilder("");
		try {
			Result result = orderTable.get(get);
			String driverID = Bytes.toString(result.getValue("driverid".getBytes(), null));
			String orderid = Bytes.toString(result.getValue("orderid".getBytes(), null));
			String startlat = Bytes.toString(result.getValue("info".getBytes(), "startlat".getBytes()));
			String startlon = Bytes.toString(result.getValue("info".getBytes(), "startlon".getBytes()));
			String starttime = Bytes.toString(result.getValue("info".getBytes(), "starttime".getBytes()));
			String endlat = Bytes.toString(result.getValue("info".getBytes(), "endlat".getBytes()));
			String endlon = Bytes.toString(result.getValue("info".getBytes(), "endlon".getBytes()));
			String endtime = Bytes.toString(result.getValue("info".getBytes(), "endtime".getBytes()));
			String status = Bytes.toString(result.getValue("status".getBytes(), null));
			sb.append(driverID + ":" + orderid  + ":" + startlat + ":" + startlon + ":" + starttime + 
					":" + endlat + ":" + endlon + ":" + endtime + ":" + status + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();

	}
	
	public static String getDone(Long time) {
		Scan scan = new Scan();
		String timeStamp2Date = timeStamp2Date(time.toString(),"yyyy-MM-dd");
		String date2TimeStamp = date2TimeStamp(timeStamp2Date,"yyyy-MM-dd");
		
		scan.withStopRow(time.toString().getBytes());
		scan.withStartRow(date2TimeStamp.toString().getBytes());
		scan.addColumn("count".getBytes(), null);
		scan.addColumn("type".getBytes(), null);
		long driver = 0l;
		long done = 0l;
		try {
			ResultScanner scanner = countTable.getScanner(scan);
			for (Result result : scanner) {
				String count = Bytes.toString(result.getValue("count".getBytes(), null));
				String type = Bytes.toString(result.getValue("type".getBytes(), null));
				if("driver".equals(type)) {
					driver += Long.valueOf(count);
				}else if("done".equals(type)) {
					done += Long.valueOf(count);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(driver) + ":" + String.valueOf(done);
	}
	
	private static String findEnd(String start) {
		if(start.length()==0)
			return "";
		String end = start.substring(0,start.length()-1);
		char charAt = start.charAt(start.length()-1);
		charAt += 1;
		return end+charAt;
	}

	
	public static void main(String[] args) {
		new PM("C:\\Users\\30837\\Desktop\\tmp.properties");
		initHbaseUtil();
		sendInfo("4dd86314a233709a624edf23a22480e7,e71a1b95ec999a249bb463ce618fae55,1475272543,104.1006289,30.7166302,1");
	}
	public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
	public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
}
