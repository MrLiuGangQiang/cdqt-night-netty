package com.cdqt.netty.vess.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdqt.netty.base.converters.array.CollectionToBooleanConverter;
import com.cdqt.netty.base.converters.array.CollectionToByteConverter;
import com.cdqt.netty.base.converters.array.CollectionToCharacterConverter;
import com.cdqt.netty.base.converters.array.CollectionToDoubleConverter;
import com.cdqt.netty.base.converters.array.CollectionToFolatConverter;
import com.cdqt.netty.base.converters.array.CollectionToIntegerConverter;
import com.cdqt.netty.base.converters.array.CollectionToLongConverter;
import com.cdqt.netty.base.converters.array.CollectionToStringConverter;
import com.cdqt.netty.base.model.FistBaseEntity;

/**
 * ClassHelper
 *
 * @author LiuGangQiang Create in 2020/12/20
 */
public class ClassHelper {
	/**
	 * 获取对象类型
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @param obj 对象
	 * @return {@link Class}
	 */
	public static Class<?> getType(Object obj) {
		if (obj instanceof Integer) {
			return Integer.TYPE;
		} else if (obj instanceof Double) {
			return Double.TYPE;
		} else if (obj instanceof Float) {
			return Float.TYPE;
		} else if (obj instanceof Character) {
			return Character.TYPE;
		} else if (obj instanceof Long) {
			return Long.TYPE;
		} else if (obj instanceof Byte) {
			return Byte.TYPE;
		} else if (obj instanceof Boolean) {
			return Boolean.TYPE;
		} else if (obj instanceof Short) {
			return Short.TYPE;
		} else if (obj instanceof String) {
			return String.class;
		} else {
			return obj.getClass();
		}
	}

	public static Object change(Type targetType, Object source) throws ClassNotFoundException, ParseException {
		/* 判断源数据类型 */
		if (List.class.isAssignableFrom(source.getClass())) {
			/* 判断目标类型 */
			if (targetType == String.class) {
				return CollectionToStringConverter.getInstance().convert(source, targetType);
			} else if (targetType == Integer.class || targetType == int.class) {
				return CollectionToIntegerConverter.getInstance().convert(source, targetType);
			} else if (targetType == Double.class || targetType == double.class) {
				return CollectionToDoubleConverter.getInstance().convert(source, targetType);
			} else if (targetType == Float.class || targetType == float.class) {
				return CollectionToFolatConverter.getInstance().convert(source, targetType);
			} else if (targetType == Long.class || targetType == long.class) {
				return CollectionToLongConverter.getInstance().convert(source, targetType);
			} else if (targetType == Boolean.class || targetType == boolean.class) {
				return CollectionToBooleanConverter.getInstance().convert(source, targetType);
			} else if (targetType == Byte.class || targetType == byte.class) {
				return CollectionToByteConverter.getInstance().convert(source, targetType);
			} else if (targetType == Character.class) {
				return CollectionToCharacterConverter.getInstance().convert(source, targetType);
			}else {
				return source.toString();
			}
		}
		/* 判断参数类型是否是参数化参数 */
		if (targetType instanceof ParameterizedType)

		{
			if (((ParameterizedType) targetType).getRawType() == List.class) {
				/* 处理List参数 */
				String clazz = ((ParameterizedType) targetType).getActualTypeArguments()[0].getTypeName();
				Class<?> cls = Class.forName(clazz);
				return JSONObject.parseArray(source.toString(), cls);
			} else if (((ParameterizedType) targetType).getRawType() == Map.class) {
				/* 处理Map参数 */
				return JSONObject.parseObject(source.toString(), HashMap.class);
			}
		} else {
			if (targetType == int.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return 0;
				}
				return Integer.parseInt(source.toString());
			} else if (targetType == Integer.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return null;
				}
				return Integer.parseInt(source.toString());
			} else if (targetType == double.class || targetType == Double.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return 0D;
				}
				return Double.parseDouble(source.toString());
			} else if (targetType == float.class || targetType == Float.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return 0F;
				}
				return Float.parseFloat(source.toString());
			} else if (targetType == long.class || targetType == Long.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return 0L;
				}
				return Long.parseLong(source.toString());
			} else if (targetType == byte.class || targetType == Byte.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return (byte) 0;
				}
				return Byte.parseByte(source.toString());
			} else if (targetType == boolean.class || targetType == Boolean.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return false;
				}
				return Boolean.parseBoolean(source.toString());
			} else if (targetType == short.class || targetType == Short.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return (short) 0;
				}
				return Short.parseShort(source.toString());
			} else if (targetType == String.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return "";
				}
				return source.toString();
			} else if (targetType == Date.class) {
				if (source == null || "".equals(source) || "\"\"".equals(source)) {
					return null;
				}
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sf.parse(source.toString());
			} else {
				String clazz = targetType.getTypeName();
				Class<?> cls = Class.forName(clazz);
				if (FistBaseEntity.class.isAssignableFrom(cls)) {
					return JSON.parseObject(source.toString(), targetType);
				}
			}
		}
		return source.toString();
	}
}
