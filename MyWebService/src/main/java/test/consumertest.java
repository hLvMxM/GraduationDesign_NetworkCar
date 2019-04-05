package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import HbaseUtil.HbaseUtil;
import Properties.PM;

public class consumertest {

	public static void main(String[] args) throws IOException {
		new PM(args[0]);
		HbaseUtil.initHbaseUtil();
		for (int i = 4; i < 10; i++) {
			String format = String.format("%02d",i);
			File file = new File("/home/ding/networkcardata/allnewgps2016_10"+format);
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String str = "";
			int count = 0;
			while((str = br.readLine()) != null) {
				count++;
				if(count % 1000 == 0)
					System.out.println(i+":"+count);
				HbaseUtil.sendInfo(str);
			}
			br.close();
			fileReader.close();
		}
	}

}
