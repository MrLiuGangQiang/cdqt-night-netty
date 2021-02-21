package com.cdqt.netty.data.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.cdqt.netty.base.exception.FistException;

/**
 * FistDataSource
 *
 * @author LiuGangQiang Create in 2021/02/21
 */
public class FistDataSource {
	private static SqlSessionFactory sqlSessionFactory;
	private final static String resource = "mybatis.xml";

	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				throw new FistException(e.getMessage());
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sqlSessionFactory;
	}
}
