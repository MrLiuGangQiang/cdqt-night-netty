package com.cdqt.netty.base.converters.array;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import com.cdqt.netty.base.converters.IFistGenericConverter;

/**
 * CollectionToIntegerConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToIntegerConverter implements IFistGenericConverter<Integer> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToIntegerConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToIntegerConverter INSTANCE = new CollectionToIntegerConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToIntegerConverter}
	 */
	public static CollectionToIntegerConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Integer convert(Object source, Type targetType) {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			if (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return targetType == int.class ? 0 : null;
				} else {
					return Integer.parseInt(obj.toString());
				}
			}
		}
		return null;
	}
}
