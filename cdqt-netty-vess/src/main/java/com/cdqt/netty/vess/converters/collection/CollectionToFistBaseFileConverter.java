package com.cdqt.netty.vess.converters.collection;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

import com.cdqt.netty.base.model.FistBaseFile;
import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToFistBaseFileConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToFistBaseFileConverter implements IFistGenericConverter<FistBaseFile> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToFistBaseFileConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToFistBaseFileConverter INSTANCE = new CollectionToFistBaseFileConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToFistBaseFileConverter}
	 */
	public static CollectionToFistBaseFileConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @throws Exception
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public FistBaseFile convert(Object source, Type targetType) throws Exception {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			if (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj == null) {
					return null;
				} else {
					return (FistBaseFile) obj;
				}
			}
		}
		return null;
	}
}
