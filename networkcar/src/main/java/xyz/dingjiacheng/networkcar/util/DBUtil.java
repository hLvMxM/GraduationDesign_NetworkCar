package xyz.dingjiacheng.networkcar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;


public class DBUtil {
	//@Value("{spring.datasource.driver-class-name}")
	public static String DRIVERCLASS = "com.mysql.jdbc.Driver";
	public static String URL="jdbc:mysql://localhost:3306/networkcar?SSL=FALSE";
	public static String USERNAME="root";
	public static String PASSWORD="19970510d";
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(DRIVERCLASS);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
