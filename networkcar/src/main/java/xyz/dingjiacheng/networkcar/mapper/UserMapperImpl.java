package xyz.dingjiacheng.networkcar.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import xyz.dingjiacheng.networkcar.model.User;
import xyz.dingjiacheng.networkcar.util.DBUtil;

public class UserMapperImpl implements UserMapper{

	
	
	@Override
	public User selectById(String id) {
		User user = null;
		try {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user where id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		ResultSet rs;
		rs = pst.executeQuery();
		if(rs.next()) {
			user = new User(rs.getString(2),
							rs.getString(3),
							rs.getString(4));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String password = (new BCryptPasswordEncoder().encode("123456".trim()));
		return new User("123123",password,"admin");
	}

	@Override
	public User selectByUsername(String username) {
		User user = null;
		String password = "";
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from user where username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs;
			rs = pst.executeQuery();
			if(rs.next()) {
				password = (new BCryptPasswordEncoder().encode(rs.getString(3).trim()));
				user = new User(rs.getString(2),
								password,
								rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
