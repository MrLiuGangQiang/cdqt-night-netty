package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToDoubleConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToDoubleConverter implements IFistGenericConverter<Double> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToDoubleConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToDoubleConverter INSTANCE = new StringToDoubleConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToDoubleConverter}
	 */
	public static StringToDoubleConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Double convert(Object source, Type targetType) {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return targetType == double.class ? 0D : null;
			} else {
				return Double.parseDouble(source.toString());
			}
		}
		return targetType == double.class ? 0D : null;
	}
}
