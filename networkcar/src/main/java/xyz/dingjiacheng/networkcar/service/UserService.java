package xyz.dingjiacheng.networkcar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.dingjiacheng.networkcar.mapper.UserMapper;
import xyz.dingjiacheng.networkcar.mapper.UserMapperImpl;
import xyz.dingjiacheng.networkcar.model.User;

@Service
public class UserService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserMapper usermapper = new UserMapperImpl();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info("用户的用户名: {}", username);
		User user = usermapper.selectByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
		}
		return user;
	}

}
