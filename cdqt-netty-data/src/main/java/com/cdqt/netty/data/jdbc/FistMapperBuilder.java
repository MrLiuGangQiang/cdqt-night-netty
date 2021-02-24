package com.cdqt.netty.data.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdqt.netty.data.mapper.IFistBaseMapper;

/**
 * FistMapperBuilder
 *
 * @author LiuGangQiang Create in 2021/02/25
 */
public class FistMapperBuilder {
	private static ClassPathXmlApplicationContext context;

	/**
	 * 获取上下文
	 *
	 * @author LiuGangQiang Create in 2021/02/25
	 * @return {@link ClassPathXmlApplicationContext}
	 */
	private static ClassPathXmlApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath:fistdb.xml");
		}
		return context;
	}

	/**
	 * 获取Mapper
	 *
	 * @author LiuGangQiang Create in 2021/02/25
	 * @param <T>
	 * @param requiredType 类型
	 * @return <T>
	 */
	public static <T extends IFistBaseMapper<?, ?>> T getMapper(Class<T> requiredType) {
		return getContext().getBean(requiredType);
	}
}
