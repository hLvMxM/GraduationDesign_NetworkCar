package xyz.dingjiacheng.networkcar.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import xyz.dingjiacheng.networkcar.service.CountService;
import xyz.dingjiacheng.networkcar.service.ScanService;
import xyz.dingjiacheng.networkcar.service.thermodynamicService;
import xyz.dingjiacheng.networkcar.util.DBUtil;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImpl;
import xyz.dingjiacheng.networkcar.webservice.WebServiceImplService;

@RestController
public class InfoController {
	
	@GetMapping("/api/thermodynamic")
	public String thermodynamic(@RequestParam(name = "start", required = true) Long starttime,@RequestParam(name = "stop", required = true) Long stoptime) {
		String scanthermodynamic = thermodynamicService.scanthermodynamic(starttime, stoptime);
		String[] split = scanthermodynamic.split("\n");
		StringBuilder sb = new StringBuilder();
		int flag = 0;
		for (String string : split) {
			if(flag==1) {
				sb.append(",");
			}
			String[] split2 = string.split(":");
			String string2 = Arrays.toString(split2);
			sb.append(string2);
			flag = 1;
		}
		return "{\"thermodynamic\":["+sb.toString()+"]}";
	}
	
	@GetMapping("/api/getdoingcount")
	public String getdoingcount() {
		return CountService.getCount();
	}
	@GetMapping("/api/scancount")
	public String scancount(@RequestParam(name = "start", required = true) Long starttime,@RequestParam(name = "stop", required = true) Long stoptime) {
		String scanCount = CountService.scanCount(starttime, stoptime);
		String[] split = scanCount.split("\n");
		StringBuilder sb1 = new StringBuilder("");
		StringBuilder sb2 = new StringBuilder("");
		StringBuilder sb3 = new StringBuilder("");
		HashMap<Long,Long> countMap = new HashMap<Long,Long>();
		HashMap<Long,Long> doneMap = new HashMap<Long,Long>();
		HashMap<Long,Long> newdriverMap = new HashMap<Long,Long>();
		for (String string : split) {
			String[] split2 = string.split(":");
			if("count".equals(split2[1])) {
				Long count = countMap.getOrDefault(Long.valueOf(split2[2])/600, 0L);
				countMap.put(Long.valueOf(split2[2])/600, count+Long.valueOf(split2[0]));
			}else if("driver".equals(split2[1])) {
				Long count = newdriverMap.getOrDefault(Long.valueOf(split2[2])/600, 0L);
				newdriverMap.put(Long.valueOf(split2[2])/600, count+Long.valueOf(split2[0]));
			}else if("done".equals(split2[1])) {
				Long count = doneMap.getOrDefault(Long.valueOf(split2[2])/600, 0L);
				doneMap.put(Long.valueOf(split2[2])/600, count+Long.valueOf(split2[0]));
			}
			
		}
		int flag = 0;
		for(Map.Entry<Long, Long> entry: doneMap.entrySet())
        {
			String tmp = "["+entry.getKey()+","+entry.getValue()+"]";
			if(flag == 1) {	sb1.append(",");	}
			sb1.append(tmp);
			flag = 1;
        }
		flag = 0;
		for(Map.Entry<Long, Long> entry: countMap.entrySet())
        {
			String tmp = "["+entry.getKey()+","+entry.getValue()+"]";
			if(flag == 1) {	sb2.append(",");	}
			sb2.append(tmp);
			flag = 1;
        }
		flag = 0;
		for(Map.Entry<Long, Long> entry: newdriverMap.entrySet())
        {
			String tmp = "["+entry.getKey()+","+entry.getValue()+"]";
			if(flag == 1) {	sb3.append(",");	}
			sb3.append(tmp);
			flag = 1;
        }
		return "{\"newdriver\":["+sb3.toString()+"],"
		+ "\"count\":["+sb2.toString()+"],"
		+ "\"done\":["+sb1.toString()+"]}";
	}
	
	
	@GetMapping("/api/position")
	public String getPosition() { 
		WebServiceImplService webServiceImplService = new WebServiceImplService();
		WebServiceImpl webServiceImplPort = webServiceImplService.getWebServiceImplPort();
		return webServiceImplPort.getPosition();
	}
	
	@GetMapping("/api/orderInfo")
	public String getOrderInfo(@RequestParam(name = "id", required = true) String driverPhone) {
		String driverIDandphone = ScanService.getID(driverPhone);
		if(driverIDandphone==null) {
			return "{\"order\":[]}";
		}
		String driverID = driverIDandphone.split(",")[0];
		String driverName = driverIDandphone.split(",")[1];
		String order = ScanService.getOrder(driverID);
		if("".equals(order)||order==null||order.length()==0) {
			return "{\"order\":[]}";
		}
		String[] split = order.split("\n");
		StringBuilder stringBuilder = new StringBuilder("");
		for (String string : split) {
			String[] info = string.split(":");
			stringBuilder.append("[\""+info[0]+"\",\""+info[1]+"\",\""+info[4]+"\",\""+info[8]+"\"],");
		}
		String json = stringBuilder.toString();
		json = "{\"order\":["+json.substring(0,json.length()-1)+"],\"name\":\""+driverName+"\",\"phone\":\""+driverPhone+"\"}";
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
			stringBuilder.append("[\""+info[2]+"\",\""+info[3]+"\",\""+info[2]+"\"],");
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
	@GetMapping("/api/time")
	public String gettime() {
		WebServiceImplService webServiceImplService = new WebServiceImplService();
		WebServiceImpl webServiceImplPort = webServiceImplService.getWebServiceImplPort();
		return webServiceImplPort.getnowTime();
	}
	
	@GetMapping("/api/getcount")
	public String getcount(@RequestParam(name = "time", required = true) Long time) {
		WebServiceImplService webServiceImplService = new WebServiceImplService();
		WebServiceImpl webServiceImplPort = webServiceImplService.getWebServiceImplPort();
		return webServiceImplPort.getDoingNumberAndCount(time);
	}
	
	@GetMapping("/api/getpre")
	public String getpre() {
		WebServiceImplService webServiceImplService = new WebServiceImplService();
		WebServiceImpl webServiceImplPort = webServiceImplService.getWebServiceImplPort();
		String pre = webServiceImplPort.getPre();
		String[] split = pre.split("\n");
		StringBuilder sb = new StringBuilder("{");
		int flag = 0;
		for (String string : split) {
			String[] split2 = string.split(":");
			if(flag!=0) {
				sb.append(",");
			}
			sb.append("\""+split2[0]+"\":"+split2[1]);
			flag = 1;
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		WebServiceImplService webServiceImplService = new WebServiceImplService();
		WebServiceImpl webServiceImplPort = webServiceImplService.getWebServiceImplPort();
		String  str =  webServiceImplPort.getPosition();
		JSONObject parseObject = JSONObject.parseObject(str);
		JSONArray jsonArray = parseObject.getJSONArray("position");
		Connection conn = DBUtil.getConnection();
		String sql = "select name,username from user where onlyid = ?";
		try {
		PreparedStatement pst = conn.prepareStatement(sql);
		for (Object object : jsonArray) {
			String value = (String) object;
			value = value.split(",")[0];
			pst.setString(1, value);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				//System.out.println(rs.getString(1)+":"+rs.getString(2));
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}
