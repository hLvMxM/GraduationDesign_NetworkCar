package xyz.dingjiacheng.networkcar.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.dingjiacheng.networkcar.mapper.UserMapper;
import xyz.dingjiacheng.networkcar.mapper.UserMapperImpl;
import xyz.dingjiacheng.networkcar.model.User;

@Service
public class UserService implements UserDetailsService {
	
	private UserMapper usermapper = new UserMapperImpl();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usermapper.selectByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
		}
		return user;
	}

}
