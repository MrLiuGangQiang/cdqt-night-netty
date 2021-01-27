package com.cdqt.netty.vess.tools.http.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdqt.netty.tool.valid.StringUtil;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.QueryStringDecoder;

/**
 * HTTP请求工具类
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistHttpRequest {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistHttpRequest() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistHttpRequestHolder {
		/**
		 * {@link FistHttpRequest} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistHttpRequest INSTANCE = new FistHttpRequest();
	}

	/**
	 * 获取{@link SingleFistHttpRequestHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistHttpRequestHolder#INSTANCE} 静态常量实例
	 */
	public static FistHttpRequest getInstance() {
		return SingleFistHttpRequestHolder.INSTANCE;
	}

	/**
	 * 获取HTTP请求头部参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param request 请求
	 * @return {@link Map}
	 */
	public Map<String, String> getHeaderParams(FullHttpRequest request) {
		Map<String, String> headers = new HashMap<String, String>();
		request.headers().forEach(header -> {
			headers.put(header.getKey(), header.getValue());
		});
		return headers;
	}

	/**
	 * 获取HTTP请求路径参数 目前暂时不用
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param request 请求
	 * @return {@link Map}
	 */
	public Map<String, String> getPathParams(FullHttpRequest request) {
		return null;
	}

	/**
	 * 获取HTTP请求查询参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param request 请求
	 * @return {@link Map}
	 */
	public Map<String, List<String>> getQueryParams(FullHttpRequest request) {
		QueryStringDecoder queryStrdecoder = new QueryStringDecoder(request.uri());
		return queryStrdecoder.parameters();
	}

	/**
	 * 获取HTTP请求Body参数
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param request 请求
	 * @return {@link Map}
	 * @throws IOException
	 */
	public Map<String, Object> getBodyParams(FullHttpRequest request) throws IOException {
		/* 获取请求的Content-Type 根据类型决定参数处理方式 */
		String contentType = request.headers().get(HttpHeaderNames.CONTENT_TYPE);
		if (contentType != null && StringUtil.containsIgnoreCase(contentType, HttpHeaderValues.APPLICATION_JSON)) {
			System.out.println(contentType);
		}else if(contentType != null && StringUtil.containsIgnoreCase(contentType, HttpHeaderValues.FORM_DATA)) {
			System.out.println(contentType);
		}
		return null;
	}
}
