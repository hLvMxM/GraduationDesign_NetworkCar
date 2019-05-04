package HbaseUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import Properties.PM;

public class UpdateDriverInfo {

	public static void main(String[] args) throws Exception {
		new PM(args[0]);
		HbaseUtil.initHbaseUtil();
		HTable distinctTable = HbaseUtil.distinctTable;
		File file = new File("./user.csv");
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String str = "";
		int i = 0;
		while ((str=br.readLine())!=null) {
			System.out.println(str);
			i++;
			if(i%10000==0) System.out.println(i);
			String[] split = str.split(",");
			String phone = split[1].replaceAll("\"", "");
			String name = split[4].replaceAll("\"", "");
			String id = split[5].replaceAll("\"", "");
			Put p = new Put(Bytes.toBytes(id));
			p.add(Bytes.toBytes("name"), null, Bytes.toBytes(name));
			p.add(Bytes.toBytes("phone"), null, Bytes.toBytes(phone));
			distinctTable.put(p);
		}
	}

}
