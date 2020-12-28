package com.cdqt.netty.vess.config.entity;

import com.cdqt.netty.base.model.FistBaseEntity;

/**
 * BizConfig
 *
 * @author LiuGangQiang Create in 2020/12/19
 */
public class BizConfig extends FistBaseEntity {

	/**
	 * serialVersionUID
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 业务ID
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
