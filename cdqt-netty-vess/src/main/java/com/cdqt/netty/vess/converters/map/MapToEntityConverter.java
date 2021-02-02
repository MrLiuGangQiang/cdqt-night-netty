package com.cdqt.netty.vess.converters.map;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import com.cdqt.netty.vess.converters.FistConverterFactory;
import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToEntityConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class MapToEntityConverter implements IFistGenericConverter<Object> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private MapToEntityConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final MapToEntityConverter INSTANCE = new MapToEntityConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link MapToEntityConverter}
	 */
	public static MapToEntityConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @throws Exception
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Object convert(Object source, Type targetType) throws Exception {
		if (source instanceof Map) {
			if (source == null || ((Map<?, ?>) source).size() <= 0) {
				return null;
			} else {
				Object entity = ((Class<?>) targetType).newInstance();
				for (Entry<?, ?> entry : ((Map<?, ?>) source).entrySet()) {
					if (entry.getValue() != null) {
						PropertyDescriptor pd = new PropertyDescriptor(entry.getKey().toString(), (Class<?>) targetType);
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(entity, FistConverterFactory.getConverter(pd.getPropertyType(), entry.getValue().getClass()).convert(entry.getValue(), targetType));
					}
				}
				return entity;
			}
		}
		return null;
	}
}
