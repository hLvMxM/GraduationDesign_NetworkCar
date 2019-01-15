package xyz.dingjiacheng.networkcar.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import xyz.dingjiacheng.networkcar.util.CoordinatesConvertUtil;
import xyz.dingjiacheng.networkcar.util.MapCordinatesVo;

public class InfoService {
	
	private static LinkedList<String> list = new LinkedList<String>();
	private static Long time = 1475251327L;
	static {
		list.clear();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					File file = new File("F:\\滴滴云成都十月份\\chengdu\\gps_20161001");
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String temp;
					while(true){
						System.out.println(list.size());
						Thread.sleep(5000);
						list.clear();
						time+=5;
						while((temp=reader.readLine())!=null) {
							String[] info = temp.split(",");
							if (Long.valueOf(info[2])<=time) {
								MapCordinatesVo mcv = CoordinatesConvertUtil.bd_encrypt(Double.valueOf(info[3].trim()), Double.valueOf(info[4].trim()));
								list.add("\""+
										mcv.getLat()+":"+
										mcv.getLon()+"\"");
							}else {
								break;
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	public static String getPosition() {
		return "{\"position\":"+list.toString()+"}";
	}
	
	public static void main(String[] args) {
		InfoService.getPosition();
	}
	
}
