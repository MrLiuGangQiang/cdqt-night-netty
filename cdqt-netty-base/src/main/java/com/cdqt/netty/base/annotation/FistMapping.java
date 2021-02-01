package com.cdqt.netty.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface FistMapping {
	/** 
	 * 映射名 
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @return {@link String[]}
	 */
	String[] value();
	
}
