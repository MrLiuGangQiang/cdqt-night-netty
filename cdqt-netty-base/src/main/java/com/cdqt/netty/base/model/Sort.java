package com.cdqt.netty.base.model;

/**
 * 排序辅助类
 * 
 * @author LiuGangQiang Create in 2020/02/29
 */
public class Sort {

	/**
	 * 排序字段名
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	private String field;
	/**
	 * 排序规则 只支持ASC和DESC 默认ASC
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	private String direction;

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		if (direction != null && ("ASC".equalsIgnoreCase(direction) || "DESC".equalsIgnoreCase(direction))) {
			return direction;
		} else {
			return "ASC";
		}
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

}
