package com.cdqt.netty.vess.config.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.cdqt.netty.base.model.FistBaseEntity;

import io.netty.channel.EventLoopGroup;

/**
 * SvrConfig
 *
 * @author LiuGangQiang Create in 2020/12/19
 */
public class SvrConfig extends FistBaseEntity {

	/**
	 * serialVersionUID
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 服务ID
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String id;
	/**
	 * 服务描述
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String note;
	/**
	 * 服务类型
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String type;
	/**
	 * 是否自启动
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private Boolean auto;

	/**
	 * 是否运行 默认False<br>
	 * 不支持反序列化
	 *
	 * @author LiuGangQiang Create in 2020/12/28
	 */
	@JSONField(deserialize = false)
	private Boolean run = false;

	/**
	 * Netty服务线程
	 *
	 * @author LiuGangQiang Create in 2020/12/28
	 */
	private EventLoopGroup group;
	/**
	 * 网络配置
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private NetConfig netConfig;

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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the auto
	 */
	public Boolean getAuto() {
		return auto;
	}

	/**
	 * @param auto the auto to set
	 */
	public void setAuto(Boolean auto) {
		this.auto = auto;
	}

	/**
	 * @return the run
	 */
	public Boolean getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(Boolean run) {
		this.run = run;
	}

	/**
	 * @return the group
	 */
	public EventLoopGroup getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(EventLoopGroup group) {
		this.group = group;
	}

	/**
	 * @return the netConfig
	 */
	public NetConfig getNetConfig() {
		return netConfig;
	}

	/**
	 * @param netConfig the netConfig to set
	 */
	public void setNetConfig(NetConfig netConfig) {
		this.netConfig = netConfig;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id=" + id + ", note=" + note + ", type=" + type + ", auto=" + auto;
	}

}
