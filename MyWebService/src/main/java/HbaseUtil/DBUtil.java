package HbaseUtil;

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
		try {
			URL = "jdbc:mysql://192.168.1.100:3306/networkcar?useSSL=false";
			USERNAME = "root";
			PASSWORD = "123456";
			DRIVERCLASS = "com.mysql.jdbc.Driver";
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
