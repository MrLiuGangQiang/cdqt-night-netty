package com.cdqt.netty.vess.targets.http;

import java.util.List;
import java.util.Map;

import com.cdqt.netty.vess.targets.FistTarget;

import io.netty.handler.codec.http.HttpMethod;

/**
 * FistHttpTarget
 *
 * @author LiuGangQiang Create in 2021/01/27
 */
public class FistHttpTarget extends FistTarget {
	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param bizName 业务名称
	 * @param funName 方法名称
	 */
	public FistHttpTarget(String bizName, String funName) {
		super(bizName, funName);
	}

	/**
	 * 请求方法
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private HttpMethod method;
	/**
	 * 头部参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, String> headerParams;
	/**
	 * 路径参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, String> pathParams;
	/**
	 * 查询参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, List<String>> queryParams;
	/**
	 * Body参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 */
	private Map<String, Object> bodyParams;

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
