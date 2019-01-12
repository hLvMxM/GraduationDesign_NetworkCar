package xyz.dingjiacheng.networkcar.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import xyz.dingjiacheng.networkcar.model.User;

public class UserMapperImpl implements UserMapper{

	@Override
	public User selectById(String id) {
		String password = (new BCryptPasswordEncoder()).encode("123456".trim());
		return new User("user",password,"admin");
	}

	@Override
	public User selectByUsername(String username) {
		String password = (new BCryptPasswordEncoder()).encode("123456".trim());
		System.out.println(password);
		return new User("user",password,"admin");
	}

}
