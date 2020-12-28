package com.cdqt.netty.vess.config.entity;

import com.cdqt.netty.base.model.FistBaseEntity;

/**
 * NetConfig
 *
 * @author LiuGangQiang Create in 2020/12/19
 */
public class NetConfig extends FistBaseEntity {

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
	 * 主机地址
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String host;
	/**
	 * 主机端口
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private Integer port;
	/**
	 * 超时时间
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private Long timeout;
	/**
	 * 版本号
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String version;
	/**
	 * Jar包名
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String jar;
	/**
	 * 类名
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private String clazz;
	/**
	 * 间隔周期
	 *
	 * @author LiuGangQiang Create in 2020/12/19
	 */
	private Long step;

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
	 * @return the timeout
	 */
	public Long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
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
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the step
	 */
	public Long getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(Long step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "id=" + id + ", note=" + note + ", host=" + host + ", port=" + port + ", timeout=" + timeout + ", version=" + version + ", jar=" + jar + ", clazz=" + clazz + ", step=" + step;
	}

}
