package com.cdqt.netty.base.converters.array;

import java.lang.reflect.Type;
import java.util.Collection;

import com.cdqt.netty.base.converters.IFistGenericConverter;

/**
 * CollectionToStringConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToStringConverter implements IFistGenericConverter<String> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToStringConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToStringConverter INSTANCE = new CollectionToStringConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToStringConverter}
	 */
	public static CollectionToStringConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public String convert(Object source, Type targetType) {
		StringBuffer sb = new StringBuffer();
		if (source instanceof Collection) {
			Collection<?> collection = (Collection<?>) source;
			if (collection == null || collection.size() <= 0) {
				sb.append("");
			} else if (collection.size() == 1) {
				((Iterable<?>) source).forEach(item -> {
					sb.append(item);
				});
			} else {
				sb.append(source.toString());
			}
		}
		return sb.toString();
	}
}
