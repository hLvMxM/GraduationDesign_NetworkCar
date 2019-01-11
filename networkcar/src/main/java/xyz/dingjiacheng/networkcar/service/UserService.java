package xyz.dingjiacheng.networkcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.dingjiacheng.networkcar.mapper.UserMapper;
import xyz.dingjiacheng.networkcar.model.User;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserMapper usermapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usermapper.selectById(username);
		if(user==null) {
			throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
		}
		return user;
	}

}
