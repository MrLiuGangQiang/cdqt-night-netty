package com.cdqt.netty.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP请求文件参数注解 代表此参数是文件参数
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface FistFile {
	/**
	 * 文件名
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @return {@link String}
	 */
	String value();

}
