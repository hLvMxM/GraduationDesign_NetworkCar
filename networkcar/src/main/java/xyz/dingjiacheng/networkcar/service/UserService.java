package xyz.dingjiacheng.networkcar.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.dingjiacheng.networkcar.mapper.UserMapper;
import xyz.dingjiacheng.networkcar.mapper.UserMapperImpl;
import xyz.dingjiacheng.networkcar.model.User;
import xyz.dingjiacheng.networkcar.util.DBUtil;

@Service
public class UserService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserMapper usermapper = new UserMapperImpl();
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usermapper.selectByUsername(username);
		logger.info("用户的用户名是: {}", user.getUsername());
		logger.info("用户的权限是: {}", user.getRole());
		if(user==null) {
			throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
		}
		return user;
	}
	
	public boolean addUser(String username,String password,String role) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into user (username,password,role) value(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, role);
			return pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<User> loadAllUser(){
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement("select * from user");
			rs = pst.executeQuery();
			while (rs.next()) {
				User u = new User(rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		return list;
	}
}
