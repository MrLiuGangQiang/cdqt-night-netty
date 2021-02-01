package com.cdqt.netty.vess.converters.collection;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToBooleanConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToBooleanConverter implements IFistGenericConverter<Boolean> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToBooleanConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToBooleanConverter INSTANCE = new CollectionToBooleanConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToBooleanConverter}
	 */
	public static CollectionToBooleanConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Boolean convert(Object source, Type targetType) {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			if (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return targetType == boolean.class ? false : null;
				} else {
					return Boolean.parseBoolean(obj.toString());
				}
			}
		}
		return targetType == boolean.class ? false : null;
	}
}
