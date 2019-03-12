package xyz.dingjiacheng.networkcar.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import xyz.dingjiacheng.networkcar.util.CoordinatesConvertUtil;
import xyz.dingjiacheng.networkcar.util.MapCordinatesVo;

public class InfoService {
	
	private static Map<String, String> map = new ConcurrentHashMap<String, String>();
	private static String json = "";
	private static LinkedList<String> list = new LinkedList<String>();
	private static Long time = 1475251801L;
	static {
		list.clear();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					File file = new File("C:\\Users\\30837\\Desktop\\gps_20161001\\gps_20161001");
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String temp;
					while(true){
						Thread.sleep(5000);
						list.clear();
						time+=5;
						while((temp=reader.readLine())!=null) {
							String[] info = temp.split(",");
							if (Long.valueOf(info[2])<=time) {
								//if ("74d8bf045b6d1c71d83813088c5cfd5f".equals(info[1])) {
									MapCordinatesVo mcv = CoordinatesConvertUtil.bd_encrypt(Double.valueOf(info[3].trim()), Double.valueOf(info[4].trim()));
//									list.add("\""+
//											mcv.getLat()+":"+
//											mcv.getLon()+"\"");
									if(info[5].equals("2"))
										map.remove(info[1]);
									else
										map.put(info[1], "\""+mcv.getLat()+":"+mcv.getLon()+":"+info[1]+"\"");
								//}
								InfoService.getPosition();
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
	
	public static void getPosition() {
		Set<Entry<String, String>> entrySet = map.entrySet();
		StringBuilder stringBuilder = new StringBuilder();
		int flag = 0;
		for (Entry<String, String> entry : entrySet) {
			if (flag!=0) {
				stringBuilder.append(",");
			}
			flag = 1;
			stringBuilder.append("\""+entry.getValue()+"\"");
		}
		json = "{\"position\":["+stringBuilder.toString()+"]}";
		//System.out.println(json);
	}
	
	public static String getPositionString() {
		return json;
	}
	
	public static void main(String[] args) {
		InfoService i = new InfoService();
	}
	
}
