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

	public static Object change(Type temp, Object obj) throws ClassNotFoundException, ParseException {
		if (temp instanceof ParameterizedType) {
			if (((ParameterizedType) temp).getRawType() == List.class) {
				String clazz = ((ParameterizedType) temp).getActualTypeArguments()[0].getTypeName();
				Class<?> cls = Class.forName(clazz);
				return JSONObject.parseArray(obj.toString(), cls);
			} else if (((ParameterizedType) temp).getRawType() == Map.class) {
				return JSONObject.parseObject(obj.toString(), HashMap.class);
			}
		} else {
			if (temp == int.class || temp == Integer.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return 0;
				}
				return Integer.parseInt(obj.toString());
			} else if (temp == double.class || temp == Double.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return 0D;
				}
				return Double.parseDouble(obj.toString());
			} else if (temp == float.class || temp == Float.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return 0F;
				}
				return Float.parseFloat(obj.toString());
			} else if (temp == long.class || temp == Long.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return 0L;
				}
				return Long.parseLong(obj.toString());
			} else if (temp == byte.class || temp == Byte.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return (byte) 0;
				}
				return Byte.parseByte(obj.toString());
			} else if (temp == boolean.class || temp == Boolean.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return false;
				}
				return Boolean.parseBoolean(obj.toString());
			} else if (temp == short.class || temp == Short.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return (short) 0;
				}
				return Short.parseShort(obj.toString());
			} else if (temp == String.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return "";
				}
				return obj.toString();
			} else if (temp == Date.class) {
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return null;
				}
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sf.parse(obj.toString());
			} else {
				String clazz = temp.getTypeName();
				Class<?> cls = Class.forName(clazz);
				if (FistBaseEntity.class.isAssignableFrom(cls)) {
					return JSON.parseObject(obj.toString(), temp);
				}
			}
		}
		return obj.toString();
	}
}
