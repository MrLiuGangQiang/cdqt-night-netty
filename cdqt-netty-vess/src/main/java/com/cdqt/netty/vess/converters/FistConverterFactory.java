package com.cdqt.netty.vess.converters;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cdqt.netty.base.model.FistBaseEntity;
import com.cdqt.netty.base.model.FistBaseFile;
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
import com.cdqt.netty.vess.converters.map.MapToEntityConverter;
import com.cdqt.netty.vess.converters.string.StringToBooleanConverter;
import com.cdqt.netty.vess.converters.string.StringToByteConverter;
import com.cdqt.netty.vess.converters.string.StringToCharacterConverter;
import com.cdqt.netty.vess.converters.string.StringToDateConverter;
import com.cdqt.netty.vess.converters.string.StringToDoubleConverter;
import com.cdqt.netty.vess.converters.string.StringToEntityConverter;
import com.cdqt.netty.vess.converters.string.StringToFolatConverter;
import com.cdqt.netty.vess.converters.string.StringToIntegerConverter;
import com.cdqt.netty.vess.converters.string.StringToLongConverter;
import com.cdqt.netty.vess.converters.string.StringToShortConverter;

/**
 * 转换器工厂
 *
 * @author LiuGangQiang Create in 2021/02/02
 */
public class FistConverterFactory {

	/**
	 * 获取转换器<br>
	 * 目前源数据类型只定义了以下几种<br>
	 *
	 * @author LiuGangQiang Create in 2021/02/02
	 * @param targetType 目标类型
	 * @param sourceType 源数据类型
	 * @return {@link IFistGenericConverter}
	 */
	public static IFistGenericConverter<?> getConverter(Type targetType, Class<?> sourceType) {
		/* 判断源数据类型 */
		if (sourceType == String.class) {
			/* 字符串类型 */
			if (targetType == Integer.class || targetType == int.class) {
				/* 字符串转Integer */
				return StringToIntegerConverter.getInstance();
			} else if (targetType == Double.class || targetType == double.class) {
				/* 字符串转Double */
				return StringToDoubleConverter.getInstance();
			} else if (targetType == Float.class || targetType == float.class) {
				/* 字符串转Float */
				return StringToFolatConverter.getInstance();
			} else if (targetType == Long.class || targetType == long.class) {
				/* 字符串转Long */
				return StringToLongConverter.getInstance();
			} else if (targetType == Boolean.class || targetType == boolean.class) {
				/* 字符串转Boolean */
				return StringToBooleanConverter.getInstance();
			} else if (targetType == Byte.class || targetType == byte.class) {
				/* 字符串转Byte */
				return StringToByteConverter.getInstance();
			} else if (targetType == Short.class || targetType == short.class) {
				/* 字符串转Short */
				return StringToShortConverter.getInstance();
			} else if (targetType == Date.class) {
				/* 字符串转Date */
				return StringToDateConverter.getInstance();
			} else if (targetType == Character.class) {
				/* 字符串转Character */
				return StringToCharacterConverter.getInstance();
			} else if (FistBaseEntity.class.isAssignableFrom((Class<?>) targetType)) {
				/* 字符串转实体类 */
				return StringToEntityConverter.getInstance();
			}
		} else if (FistBaseFile.class.isAssignableFrom(sourceType)) {
			/* 文件类型 */
			return DefaultConverter.getInstance();
		} else if (List.class.isAssignableFrom(sourceType)) {
			/* 集合类型 */
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
			System.out.println(targetType.getTypeName());
		} else if (Map.class.isAssignableFrom(sourceType)) {
			if (FistBaseEntity.class.isAssignableFrom((Class<?>) targetType)) {
				/* 集合转实体类 */
				return MapToEntityConverter.getInstance();
			}
		}
		return DefaultConverter.getInstance();
	};

}