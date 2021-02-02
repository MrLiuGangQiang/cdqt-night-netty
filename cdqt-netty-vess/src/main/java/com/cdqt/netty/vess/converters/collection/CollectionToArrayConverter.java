package com.cdqt.netty.vess.converters.collection;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import com.cdqt.netty.vess.converters.FistConverterFactory;
import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToBooleanConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToArrayConverter implements IFistGenericConverter<Object[]> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToArrayConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToArrayConverter INSTANCE = new CollectionToArrayConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToArrayConverter}
	 */
	public static CollectionToArrayConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @throws Exception
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Object[] convert(Object source, Type targetType) throws Exception {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			Class<?> targetClass = ((Class<?>) targetType).getComponentType();
			Object[] targets = (Object[]) Array.newInstance(targetClass, ((Collection<?>) source).size());
			int i = 0;
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj == null) {
					Array.set(targets, i, null);
				} else {
					Class<?> sourceType = obj.getClass();
					Array.set(targets, i, FistConverterFactory.getConverter(targetType, sourceType).convert(obj, targetClass));
				}
				i++;
			}
			return targets;
		}
		return null;
	}
}
