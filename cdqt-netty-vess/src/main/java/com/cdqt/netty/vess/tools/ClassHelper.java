package com.cdqt.netty.vess.tools;

import java.lang.reflect.Type;
import java.text.ParseException;
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
 * ClassHelper
 *
 * @author LiuGangQiang Create in 2020/12/20
 */
public class ClassHelper {

	public static Object change(Type targetType, Object source) throws ClassNotFoundException, ParseException {
		/* 判断源数据类型 */
		if (List.class.isAssignableFrom(source.getClass())) {
			/*
			 * if (targetType instanceof ParameterizedType) { 参数化参数 if (((ParameterizedType) targetType).getRawType() == List.class) { 处理List参数 String clazz = ((ParameterizedType) targetType).getActualTypeArguments()[0].getTypeName();
			 * Class<?> cls = Class.forName(clazz); return JSONObject.parseArray(source.toString(), cls); } else if (((ParameterizedType) targetType).getRawType() == Map.class) { 处理Map参数 return JSONObject.parseObject(source.toString(),
			 * HashMap.class); } } else
			 */
			/* 判断目标类型 */
			if (((Class<?>)targetType).isArray() ) {
				/* 集合转数组 */
				return CollectionToArrayConverter.getInstance().convert(source, targetType);
			} else if (targetType == String.class) {
				/* 集合转字符串 */
				return CollectionToStringConverter.getInstance().convert(source, targetType);
			} else if (targetType == Integer.class || targetType == int.class) {
				/* 集合转Integer */
				return CollectionToIntegerConverter.getInstance().convert(source, targetType);
			} else if (targetType == Double.class || targetType == double.class) {
				/* 集合转Double */
				return CollectionToDoubleConverter.getInstance().convert(source, targetType);
			} else if (targetType == Float.class || targetType == float.class) {
				/* 集合转Float */
				return CollectionToFolatConverter.getInstance().convert(source, targetType);
			} else if (targetType == Long.class || targetType == long.class) {
				/* 集合转Long */
				return CollectionToLongConverter.getInstance().convert(source, targetType);
			} else if (targetType == Boolean.class || targetType == boolean.class) {
				/* 集合转Boolean */
				return CollectionToBooleanConverter.getInstance().convert(source, targetType);
			} else if (targetType == Byte.class || targetType == byte.class) {
				/* 集合转Byte */
				return CollectionToByteConverter.getInstance().convert(source, targetType);
			} else if (targetType == Short.class || targetType == short.class) {
				/* 集合转Short */
				return CollectionToShortConverter.getInstance().convert(source, targetType);
			} else if (targetType == Date.class) {
				/* 集合转Date */
				return CollectionToDateConverter.getInstance().convert(source, targetType);
			} else if (targetType == Character.class) {
				/* 集合转Character */
				return CollectionToCharacterConverter.getInstance().convert(source, targetType);
			} else {
				/* 默认直接返回字符串 */
				return source.toString();
			}
		}
		return source.toString();
	}
}
