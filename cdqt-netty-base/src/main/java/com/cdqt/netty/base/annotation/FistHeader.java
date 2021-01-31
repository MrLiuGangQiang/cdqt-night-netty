package com.cdqt.netty.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP请求Header参数注解 代表此参数是Header参数
 *
 * @author LiuGangQiang Create in 2021/01/31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface FistHeader {
	/**
	 * 头部参数名
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @return {@link String}
	 */
	String value() default "";
}
