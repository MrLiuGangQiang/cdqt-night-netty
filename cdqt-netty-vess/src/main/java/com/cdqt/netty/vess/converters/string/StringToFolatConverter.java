package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToFolatConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToFolatConverter implements IFistGenericConverter<Float> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToFolatConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToFolatConverter INSTANCE = new StringToFolatConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToFolatConverter}
	 */
	public static StringToFolatConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Float convert(Object source, Type targetType) {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return targetType == float.class ? 0F : null;
			} else {
				return Float.parseFloat(source.toString());
			}
		}
		return targetType == float.class ? 0F : null;
	}
}
