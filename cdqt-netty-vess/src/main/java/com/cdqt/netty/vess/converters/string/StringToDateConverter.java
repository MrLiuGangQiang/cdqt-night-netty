package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToDateConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToDateConverter implements IFistGenericConverter<Date> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToDateConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToDateConverter INSTANCE = new StringToDateConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToDateConverter}
	 */
	public static StringToDateConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @throws ParseException
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Date convert(Object source, Type targetType) throws ParseException {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return null;
			} else {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sf.parse(source.toString());
			}
		}
		return null;
	}
}
