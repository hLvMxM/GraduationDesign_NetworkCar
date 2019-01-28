package xyz.dingjiacheng.networkcar.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	public static String DRIVERCLASS;
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	
	static {
		Properties prop = new Properties();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/application.properties"));
			prop.load(bufferedReader);
			URL = prop.getProperty("spring.datasource.url");
			USERNAME = prop.getProperty("spring.datasource.username");
			PASSWORD = prop.getProperty("spring.datasource.password");
			DRIVERCLASS = prop.getProperty("spring.datasource.driver-class-name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			prop.load(in);
//			URL = prop.getProperty("spring.datasource.url");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static Connection getConnection(){
		System.out.println(URL);
		Connection conn = null;
		try {
			Class.forName(DRIVERCLASS);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
	
}
