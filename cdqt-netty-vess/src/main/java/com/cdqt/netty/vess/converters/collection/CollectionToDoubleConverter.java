package com.cdqt.netty.vess.converters.collection;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToDoubleConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToDoubleConverter implements IFistGenericConverter<Double> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToDoubleConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToDoubleConverter INSTANCE = new CollectionToDoubleConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToDoubleConverter}
	 */
	public static CollectionToDoubleConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Double convert(Object source, Type targetType) {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			if (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return targetType == double.class ? 0D : null;
				} else {
					return Double.parseDouble(obj.toString());
				}
			}
		}
		return targetType == double.class ? 0D : null;
	}
}
