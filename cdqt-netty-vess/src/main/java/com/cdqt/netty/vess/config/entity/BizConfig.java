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

	/**
	 * 业务描述
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String note;
	/**
	 * 业务类型
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String type;
	/**
	 * 主机地址
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String host;
	/**
	 * 主机端口
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private Integer port;

	/**
	 * 远程调用的路径
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String url;
	/**
	 * Jar包路径
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String jar;
	/**
	 * 包路径
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String[] pcks;
	/**
	 * 版本号
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String version;

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
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the jar
	 */
	public String getJar() {
		return jar;
	}

	/**
	 * @param jar the jar to set
	 */
	public void setJar(String jar) {
		this.jar = jar;
	}

	/**
	 * @return the pcks
	 */
	public String[] getPcks() {
		return pcks;
	}

	/**
	 * @param pcks the pcks to set
	 */
	public void setPcks(String[] pcks) {
		this.pcks = pcks;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
