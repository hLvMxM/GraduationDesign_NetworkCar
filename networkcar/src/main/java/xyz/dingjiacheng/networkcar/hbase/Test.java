package xyz.dingjiacheng.networkcar.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;

public class Test {
	public static void main(String[] args) throws Exception {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.201.2"); 
		conf.set("hbase.zookeeper.property.clientPort","2181"); 
        conf.set("hbase.master", "192.168.201.2:600000");
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor tableDescriptor = admin.getTableDescriptor(
				Bytes.toBytes("orderInfo"));
		byte[] name = tableDescriptor.getName();
		System.out.println(new String(name));
		HColumnDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
		for (HColumnDescriptor d : columnFamilies) {
		System.out.println(d.getNameAsString());
		}
	}
}