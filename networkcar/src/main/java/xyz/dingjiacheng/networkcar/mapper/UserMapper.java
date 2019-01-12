package xyz.dingjiacheng.networkcar.mapper;

import xyz.dingjiacheng.networkcar.model.User;

public interface UserMapper{
	User selectById(String id);
    User selectByUsername(String username);
}
