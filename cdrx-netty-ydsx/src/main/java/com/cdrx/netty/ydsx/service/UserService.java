package com.cdrx.netty.ydsx.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.cdqt.netty.data.jdbc.FistDataSource;
import com.cdrx.netty.ydsx.entity.User;
import com.cdrx.netty.ydsx.mapper.UserMapper;

/**
 * UserService
 *
 * @author LiuGangQiang Create in 2021/02/24
 */
public class UserService {

	public List<Map<String, Object>> getUserList(User user) {
		SqlSession session = FistDataSource.getSession(true);
		UserMapper mapper= session.getMapper(UserMapper.class);
		List<Map<String, Object>> results =mapper.queryList(user);
		session.close();
		return results;
	}
}
