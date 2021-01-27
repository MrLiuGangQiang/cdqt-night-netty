package com.cdqt.netty.vess.targets;

/**
 * 请求通用对象
 *
 * @author LiuGangQiang Create in 2021/01/27
 */
public abstract class FistTarget {
	/**
	 * 业务名称
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private String bizName;
	/**
	 * 方法名称
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private String funName;

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param bizName 业务名称
	 * @param funName 方法名称
	 */
	public FistTarget(String bizName, String funName) {
		super();
		this.bizName = bizName;
		this.funName = funName;
	}

	/**
	 * @return the bizName
	 */
	public String getBizName() {
		return bizName;
	}

	/**
	 * @param bizName the bizName to set
	 */
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	/**
	 * @return the funName
	 */
	public String getFunName() {
		return funName;
	}

	/**
	 * @param funName the funName to set
	 */
	public void setFunName(String funName) {
		this.funName = funName;
	}

}
