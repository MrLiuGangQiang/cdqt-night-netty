package com.cdqt.netty.vess.converters;

import java.lang.reflect.Type;
import java.text.ParseException;

/**
 * 转换器接口
 *
 * @author LiuGangQiang Create in 2021/02/01
 */
public interface IFistGenericConverter<T> {
	/**
	 * 数据转换
	 *
	 * @author LiuGangQiang Create in 2021/02/01
	 * @param source     源数据
	 * @param targetType 目标数据类型
	 * @return {@link Object}
	 * @throws ParseException 
	 */
	T convert(Object source, Type targetType) throws Exception;
}