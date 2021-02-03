package com.cdqt.netty.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP请求Body参数注解 代表此参数是Body参数
 *
 * @author LiuGangQiang Create in 2021/01/31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface FistBody {
	/**
	 * 参数名
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @return {@link String}
	 */
	String value() default "";
}
