package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToBooleanConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToBooleanConverter implements IFistGenericConverter<Boolean> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToBooleanConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToBooleanConverter INSTANCE = new StringToBooleanConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToBooleanConverter}
	 */
	public static StringToBooleanConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Boolean convert(Object source, Type targetType) {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return targetType == boolean.class ? false : null;
			} else {
				return Boolean.parseBoolean(source.toString());
			}
		}
		return targetType == boolean.class ? false : null;
	}
}
