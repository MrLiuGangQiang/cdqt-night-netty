package com.cdqt.netty.base.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 框架实例类
 *
 * @author LiuGangQiang Create in 2021/01/31
 */
public class FistBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private String id;

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
	 * 方法集合
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private Map<String, Method> methods;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the methods
	 */
	public Map<String, Method> getMethods() {
		return methods;
	}

	/**
	 * @param methods the methods to set
	 */
	public void setMethods(Map<String, Method> methods) {
		this.methods = methods;
	}

}
