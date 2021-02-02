package com.cdqt.netty.vess.converters.collection;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.cdqt.netty.base.exception.FistRuntimeException;
import com.cdqt.netty.vess.converters.IFistGenericConverter;

/**
 * CollectionToDateConverter
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public class CollectionToDateConverter implements IFistGenericConverter<Date> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private CollectionToDateConverter() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 */
	private static class SingleHolder {
		private static final CollectionToDateConverter INSTANCE = new CollectionToDateConverter();
	}

	/**
	 * 获取类实例
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @return {@link CollectionToDateConverter}
	 */
	public static CollectionToDateConverter getInstance() {
		return SingleHolder.INSTANCE;
	}

	/**
	 * @throws ParseException
	 * @see com.cdqt.netty.base.converters.IFistGenericConverter#convert(java.lang.Object, java.lang.reflect.Type, java.lang.reflect.Type)
	 */
	@Override
	public Date convert(Object source, Type targetType) throws ParseException {
		if (source instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) source).iterator();
			if (iterator.hasNext()) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Object obj = iterator.next();
				if (obj == null || "".equals(obj) || "\"\"".equals(obj)) {
					return null;
				} else {
					try {
						return sf.parse(obj.toString());
					} catch (Exception e) {
						throw new FistRuntimeException("Fist Converter [{0}] To Date Error：{1}", obj,e.getMessage()) ;
					}
				}
			}
		}
		return null;
	}
}
