package com.cdqt.netty.data.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.cdqt.netty.base.exception.FistException;

/**
 * FistDataSource
 *
 * @author LiuGangQiang Create in 2021/02/24
 */
public class FistDataSource {
	/**
	 * SqlSessionFactory
	 *
	 * @author LiuGangQiang Create in 2021/02/24
	 */
	private static SqlSessionFactory sqlSessionFactory;
	/**
	 * 配置文件
	 *
	 * @author LiuGangQiang Create in 2021/02/24
	 */
	private final static String RESOURCE = "fistdb.xml";

	/**
	 * 获取SqlSession工厂
	 *
	 * @author LiuGangQiang Create in 2021/02/24
	 * @return {@link SqlSessionFactory}
	 */
	private static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream(RESOURCE);
			} catch (IOException e) {
				throw new FistException(e.getMessage());
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sqlSessionFactory;
	}

	/**
	 * 获取SqlSession
	 *
	 * @author LiuGangQiang Create in 2021/02/24
	 * @param autoCommit 是否自动提交
	 * @return {@link SqlSession}
	 */
	public static SqlSession getSession(boolean autoCommit) {
		return getSqlSessionFactory().openSession(autoCommit);
	}

}
