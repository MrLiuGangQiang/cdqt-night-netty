package com.cdqt.netty.data;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.cdqt.netty.data.jdbc.FistDataSource;
import com.cdqt.netty.data.mapper.User;
import com.cdqt.netty.data.mapper.UserMapper;

/**
 * FistDB
 *
 * @author LiuGangQiang Create in 2021/02/21
 */
public class FistDB {
	public static void main(String[] args) {
		try (SqlSession session = FistDataSource.getSqlSessionFactory().openSession()) {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User entity=new User();
			List<Map<String, Object>> result=mapper.queryList(entity);
			System.out.println(result);
		}
	}
}
