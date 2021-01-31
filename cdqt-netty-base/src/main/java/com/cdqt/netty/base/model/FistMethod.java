package com.cdqt.netty.base.model;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 框架反射方法类
 *
 * @author LiuGangQiang Create in 2021/01/31
 */
public class FistMethod implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Class对象
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private Class<?> clazz;

	/**
	 * 类实例
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private Object entity;

	/**
	 * 方法
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private Method method;

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 * @param clazz  Class类
	 * @param entity 实例
	 * @param method 方法
	 */
	public FistMethod(Class<?> clazz, Object entity, Method method) {
		super();
		this.clazz = clazz;
		this.entity = entity;
		this.method = method;
	}

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

}
