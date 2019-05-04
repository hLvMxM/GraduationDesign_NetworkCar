package xyz.dingjiacheng.networkcar.service;

import java.rmi.RemoteException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.dingjiacheng.networkcar.util.DBUtil;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImpl;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImplService;




public class ScanService {
	public static String getOrder(String driverID) {
		WebServiceImplService service = new WebServiceImplService();
		WebServiceImpl port = service.getWebServiceImplPort();
		String orderId = port.scanOrderByDriver(driverID);
		return orderId;
	}
	
	public static String getPosition(String orderID) {
		WebServiceImplService service = new WebServiceImplService();
		WebServiceImpl port = service.getWebServiceImplPort();
		String positionInfo = port.scanPositionByOrder(orderID);
		return positionInfo;
	}
	

	
	public static String getID(String phone) {
		Connection conn = DBUtil.getConnection();
		String sql = "select onlyid,name from user where username = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, phone);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getString(1)+","+rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getID("18000000002"));
	}
}
