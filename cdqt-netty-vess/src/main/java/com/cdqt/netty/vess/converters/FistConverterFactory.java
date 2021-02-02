package com.cdqt.netty.vess.converters;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.cdqt.netty.vess.converters.collection.CollectionToArrayConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToBooleanConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToByteConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToCharacterConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToDateConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToDoubleConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToFolatConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToIntegerConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToLongConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToShortConverter;
import com.cdqt.netty.vess.converters.collection.CollectionToStringConverter;

/**
 * 转换器工厂
 *
 * @author LiuGangQiang Create in 2021/02/02
 */
public class FistConverterFactory {

	/**
	 * 获取转换器
	 *
	 * @author LiuGangQiang Create in 2021/02/02
	 * @param targetType 目标类型
	 * @param sourceType 源数据类型
	 * @return {@link IFistGenericConverter}
	 */
	public static IFistGenericConverter<?> getConverter(Type targetType, Type sourceType) {
		/* 判断源数据类型 */
		if (List.class.isAssignableFrom((Class<?>) sourceType)) {
			/* 判断目标类型 */
			if (((Class<?>) targetType).isArray()) {
				/* 集合转数组 */
				return CollectionToArrayConverter.getInstance();
			} else if (targetType == String.class) {
				/* 集合转字符串 */
				return CollectionToStringConverter.getInstance();
			} else if (targetType == Integer.class || targetType == int.class) {
				/* 集合转Integer */
				return CollectionToIntegerConverter.getInstance();
			} else if (targetType == Double.class || targetType == double.class) {
				/* 集合转Double */
				return CollectionToDoubleConverter.getInstance();
			} else if (targetType == Float.class || targetType == float.class) {
				/* 集合转Float */
				return CollectionToFolatConverter.getInstance();
			} else if (targetType == Long.class || targetType == long.class) {
				/* 集合转Long */
				return CollectionToLongConverter.getInstance();
			} else if (targetType == Boolean.class || targetType == boolean.class) {
				/* 集合转Boolean */
				return CollectionToBooleanConverter.getInstance();
			} else if (targetType == Byte.class || targetType == byte.class) {
				/* 集合转Byte */
				return CollectionToByteConverter.getInstance();
			} else if (targetType == Short.class || targetType == short.class) {
				/* 集合转Short */
				return CollectionToShortConverter.getInstance();
			} else if (targetType == Date.class) {
				/* 集合转Date */
				return CollectionToDateConverter.getInstance();
			} else if (targetType == Character.class) {
				/* 集合转Character */
				return CollectionToCharacterConverter.getInstance();
			}
		}
		return DefaultConverter.getInstance();
	};

}