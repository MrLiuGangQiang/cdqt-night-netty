package com.cdqt.netty.data;

import com.cdqt.netty.base.model.FistBaseEntity;

/**
 * User
 *
 * @author LiuGangQiang Create in 2021/02/02
 */
public class User extends FistBaseEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	private String password;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
