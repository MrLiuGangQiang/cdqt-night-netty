package com.cdqt.netty.vess.targets;

import java.util.List;
import java.util.Map;

import io.netty.handler.codec.http.HttpMethod;

/**
 * FistHttpTarget
 *
 * @author LiuGangQiang Create in 2021/01/27
 */
public class FistTarget {
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
	 * 所在Jar包名
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private String jar;
	/**
	 * 包路径
	 *
	 * @author LiuGangQiang Create in 2021/01/29
	 */
	private String[] pcks;

	/**
	 * 是否遍历子包
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private Boolean isergodic;

	/**
	 * 请求方法 HTTP适用
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private HttpMethod method;
	/**
	 * 头部参数 HTTP适用
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, String> headerParams;
	/**
	 * 路径参数 HTTP适用
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, String> pathParams;
	/**
	 * 查询参数 通用
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, List<String>> queryParams;
	/**
	 * Body参数 HTTP适用
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, Object> bodyParams;

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
	 * @return the bizName
	 */
	public String getBizName() {
		return bizName;
	}

	/**
	 * @return the isergodic
	 */
	public Boolean getIsergodic() {
		return isergodic;
	}

	/**
	 * @param isergodic the isergodic to set
	 */
	public void setIsergodic(Boolean isergodic) {
		this.isergodic = isergodic;
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
	 * @param funName the funName to set
	 */
	public void setFunName(String funName) {
		this.funName = funName;
	}

	/**
	 * @return the method
	 */
	public HttpMethod getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	/**
	 * @return the headerParams
	 */
	public Map<String, String> getHeaderParams() {
		return headerParams;
	}

	/**
	 * @param headerParams the headerParams to set
	 */
	public void setHeaderParams(Map<String, String> headerParams) {
		this.headerParams = headerParams;
	}

	/**
	 * @return the pathParams
	 */
	public Map<String, String> getPathParams() {
		return pathParams;
	}

	/**
	 * @param pathParams the pathParams to set
	 */
	public void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}

	/**
	 * @return the queryParams
	 */
	public Map<String, List<String>> getQueryParams() {
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public void setQueryParams(Map<String, List<String>> queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * @return the bodyParams
	 */
	public Map<String, Object> getBodyParams() {
		return bodyParams;
	}

	/**
	 * @param bodyParams the bodyParams to set
	 */
	public void setBodyParams(Map<String, Object> bodyParams) {
		this.bodyParams = bodyParams;
	}

}
