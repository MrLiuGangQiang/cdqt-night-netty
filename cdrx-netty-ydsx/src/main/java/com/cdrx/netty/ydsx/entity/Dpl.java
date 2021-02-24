package com.cdrx.netty.ydsx.entity;

import com.cdqt.netty.base.model.FistBaseEntity;

/**
 * User
 *
 * @author LiuGangQiang Create in 2021/02/21
 */
public class Dpl extends FistBaseEntity {

	/**
	 * serialVersionUID
	 *
	 * @author LiuGangQiang Create in 2021/02/21
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

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

}
