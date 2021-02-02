package com.cdqt.netty.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP请求Query参数注解 代表此参数是Query参数
 *
 * @author LiuGangQiang Create in 2021/01/31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface FistQuery {
	/**
	 * 参数名
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @return {@link String}
	 */
	String value();

	/**
	 * 参数是否在body内
	 *
	 * @author LiuGangQiang Create in 2021/02/02
	 * @return {@link Boolean}
	 */
	boolean isBody() default false;
}
