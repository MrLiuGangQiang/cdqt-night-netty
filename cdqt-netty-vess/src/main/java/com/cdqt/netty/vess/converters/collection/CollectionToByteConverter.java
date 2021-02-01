package com.cdqt.netty.vess.converters.collection;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToByteConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToByteConverter implements IFistGenericConverter<Byte> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToByteConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToByteConverter INSTANCE = new CollectionToByteConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToByteConverter}
	 */
	public static CollectionToByteConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Byte convert(Object source, Type targetType) {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			if (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return targetType == byte.class ? (byte)0 : null;
				} else {
					return Byte.parseByte(obj.toString());
				}
			}
		}
		return targetType == byte.class ? (byte)0 : null;
	}
}
