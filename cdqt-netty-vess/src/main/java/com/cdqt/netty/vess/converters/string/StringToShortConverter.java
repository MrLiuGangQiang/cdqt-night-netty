package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToShortConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToShortConverter implements IFistGenericConverter<Short> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToShortConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToShortConverter INSTANCE = new StringToShortConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToShortConverter}
	 */
	public static StringToShortConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Short convert(Object source, Type targetType) {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return targetType == short.class ? (short) 0 : null;
			} else {
				return Short.parseShort(source.toString());
			}
		}
		return targetType == short.class ? (short) 0 : null;
	}
}
