package xyz.dingjiacheng.networkcar.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.dingjiacheng.networkcar.service.InfoService;
import xyz.dingjiacheng.networkcar.service.ScanService;
import xyz.dingjiacheng.networkcar.util.CoordinatesConvertUtil;
import xyz.dingjiacheng.networkcar.util.DBUtil;
import xyz.dingjiacheng.networkcar.util.MapCordinatesVo;

@RestController
public class InfoController {
	
	@GetMapping("/api/position")
	public String getPosition() { 
		return InfoService.getPositionString();	
	}
	
	@GetMapping("/api/orderInfo")
	public String getOrderInfo(@RequestParam(name = "id", required = true) String driverID) {
		String order = ScanService.getOrder(driverID);
		if("".equals(order)||order==null||order.length()==0) {
			return "{\"order\":[]}";
		}
		String[] split = order.split("\n");
		StringBuilder stringBuilder = new StringBuilder("");
		for (String string : split) {
			String[] info = string.split(":");
			stringBuilder.append("[\""+info[0]+"\",\""+info[1]+"\",\""+info[2]+"\",\""+info[3]+"\"],");
		}
		String json = stringBuilder.toString();
		json = "{\"order\":["+json.substring(0,json.length()-1)+"]}";
		return json;
	}
	
	@GetMapping("/api/positionInfo")
	public String getPositionInfo(@RequestParam(name = "id", required = true) String orderID) {
		String position = ScanService.getPosition(orderID);
		if("".equals(position)||position==null||position.length()==0) {
			return "{\"position\":[]}";
		}
		String[] split = position.split("\n");
		StringBuilder stringBuilder = new StringBuilder("");
		for (String string : split) {
			String[] info = string.split(":");
			MapCordinatesVo mcv = CoordinatesConvertUtil.bd_encrypt(Double.valueOf(info[0]), Double.valueOf(info[1]));
			stringBuilder.append("[\""+mcv.getLat()+"\",\""+mcv.getLon()+"\",\""+info[2]+"\"],");
		}
		String json = stringBuilder.toString();
		json = "{\"position\":["+json.substring(0,json.length()-1)+"]}";
		return json;
	}
	
	@GetMapping("/api/changepassword")
	public String changepassword(@RequestParam(name = "email", required = true) String email,@RequestParam(name = "password", required = true) String password) {
		Connection conn = DBUtil.getConnection();
		String sql = "update user set password = ? where username = ?";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, (new BCryptPasswordEncoder().encode(password.trim())));
			pst.setString(2, email);
			pst.execute();
		} catch (SQLException e) {
			return "error";
		}finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return (new BCryptPasswordEncoder().encode(password.trim()));
	}
	
	@GetMapping("/api/deleteuser")
	public String deleteuser(@RequestParam(name = "email", required = true) String email) {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from user where username = ?";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.execute();
		} catch (SQLException e) {
			return "error";
		}finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "success";
	}
	@GetMapping("/api/adduser")
	public String adduser(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "role", required = true) String role) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into user (username,password,role) values(?,?,?)";
		password = (new BCryptPasswordEncoder().encode(password.trim()));
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			pst.setString(3, role);
			pst.execute();
		} catch (SQLException e) {
			return "error";
		}finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return password;
	}
}
