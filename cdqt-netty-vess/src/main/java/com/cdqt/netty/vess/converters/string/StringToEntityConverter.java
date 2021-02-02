package com.cdqt.netty.vess.converters.string;

import java.lang.reflect.Type;
import java.text.ParseException;

import com.alibaba.fastjson.JSON;
import com.cdqt.netty.base.model.FistBaseEntity;
import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * StringToEntityConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class StringToEntityConverter implements IFistGenericConverter<FistBaseEntity> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private StringToEntityConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final StringToEntityConverter INSTANCE = new StringToEntityConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link StringToEntityConverter}
	 */
	public static StringToEntityConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @throws ParseException
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public FistBaseEntity convert(Object source, Type targetType) throws ParseException {
		if (source instanceof String) {
			if (source == null || "".equals(source) || "\"\"".equals(source)) {
				return null;
			} else {
				return JSON.parseObject(source.toString(), targetType);
			}
		}
		return null;
	}
}
