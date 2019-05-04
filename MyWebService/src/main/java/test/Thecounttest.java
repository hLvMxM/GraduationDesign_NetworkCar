package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import ch.hsr.geohash.GeoHash;
import hello.examples.spyne.Application;
import hello.examples.spyne.HelloWorldService;
import thermodynamiccount.Count;

public class Thecounttest {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\30837\\Desktop\\newgps.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		HelloWorldService hws = new HelloWorldService();
		Application application = hws.getApplication();
		double[][] array = new double[50][3];
		for(int i=0;i<3;i++) {
			for (int j = 0; j < 50; j++) {
				array[j][i]= 0; 
			}
		}
		Long count = 0L;
		while((str = br.readLine()) != null) {
			count++;
			if (count % 1000 == 0) {
				System.out.println(count);
			}
			String[] split = str.split(",");
			String lat = split[1]; //30
			String lon = split[0]; //104
			Double myknnpre = application.myknnpre(Double.valueOf(lon), Double.valueOf(lat));
			int t = myknnpre.intValue();
			array[t][0] += Double.valueOf(lat) - 30;
			array[t][1] += Double.valueOf(lon) - 104;
			array[t][2] += 1;
		}
		for (int i = 0; i < 50; i++) {
			System.out.println(i+":"+(array[i][0]/array[i][2]+30)+":"+(array[i][1]/array[i][2]+104));
		}
	}
}
