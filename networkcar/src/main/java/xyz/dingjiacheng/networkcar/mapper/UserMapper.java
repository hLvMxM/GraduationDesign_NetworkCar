package xyz.dingjiacheng.networkcar.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.dingjiacheng.networkcar.model.User;
@Mapper
public interface UserMapper{
	User selectById(String id);
    User selectByUsername(String username);
}
