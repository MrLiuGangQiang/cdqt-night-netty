package com.cdqt.netty.vess.converters;

import java.lang.reflect.Type;

import com.cdqt.netty.base.exception.FistRuntimeException;

/**
 * DefaultConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class DefaultConverter implements IFistGenericConverter<Object> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private DefaultConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final DefaultConverter INSTANCE = new DefaultConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link DefaultConverter}
	 */
	public static DefaultConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Object convert(Object source, Type targetType) {
		if (source.getClass()!=targetType) {
			throw new FistRuntimeException("Fist Converter {0} Error Because Type Error", targetType);
		}
		return source;
	}
}
