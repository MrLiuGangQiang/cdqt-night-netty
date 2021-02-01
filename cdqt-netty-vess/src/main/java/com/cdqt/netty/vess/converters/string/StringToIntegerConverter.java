package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToIntegerConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToIntegerConverter implements IFistGenericConverter<Integer> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToIntegerConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToIntegerConverter INSTANCE = new StringToIntegerConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToIntegerConverter}
	 */
	public static StringToIntegerConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Integer convert(Object source, Type targetType) {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return targetType == int.class ? 0 : null;
			} else {
				return Integer.parseInt(source.toString());
			}
		}
		return targetType == int.class ? 0 : null;
	}
}
