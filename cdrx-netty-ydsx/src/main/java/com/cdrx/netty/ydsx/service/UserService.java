package com.cdrx.netty.ydsx.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cdqt.netty.data.jdbc.FistMapperBuilder;
import com.cdrx.netty.ydsx.entity.User;
import com.cdrx.netty.ydsx.mapper.UserMapper;

/**
 * UserService
 *
 * @author LiuGangQiang Create in 2021/02/24
 */
@Service
public class UserService {
	private UserMapper mapper = FistMapperBuilder.getMapper(UserMapper.class);

	public List<Map<String, Object>> getUserList(User user) {
		List<Map<String, Object>> results = mapper.queryList(user);
		return results;
	}
}
