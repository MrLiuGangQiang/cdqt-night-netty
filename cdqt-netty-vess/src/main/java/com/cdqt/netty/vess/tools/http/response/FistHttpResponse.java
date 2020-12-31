package com.cdqt.netty.vess.tools.http.response;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * HTTP响应工具类
 *
 * @author LiuGangQiang Create in 2020/12/29
 */
public class FistHttpResponse {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistHttpResponse() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistHttpResponseHolder {
		/**
		 * {@link FistHttpResponse} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistHttpResponse INSTANCE = new FistHttpResponse();
	}

	/**
	 * 获取{@link SingleFistHttpResponseHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistHttpResponseHolder#INSTANCE} 静态常量实例
	 */
	public static FistHttpResponse getInstance() {
		return SingleFistHttpResponseHolder.INSTANCE;
	}

	/**
	 * 输出Json响应
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @param version http版本
	 * @param status  http状态
	 * @param content 内容
	 * @return {@link FullHttpResponse}
	 */
	public FullHttpResponse outJson(HttpVersion version, HttpResponseStatus status, ByteBuf content) {
		FullHttpResponse response = new DefaultFullHttpResponse(version, status, content);
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
		response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
		response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
		response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE");
		return response;
	}

	/**
	 * 输出Text响应
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @param version http版本
	 * @param status  http状态
	 * @param content 内容
	 * @return {@link FullHttpResponse}
	 */
	public FullHttpResponse outText(HttpVersion version, HttpResponseStatus status, ByteBuf content) {
		FullHttpResponse response = new DefaultFullHttpResponse(version, status, content);
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN);
		response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
		response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
		response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE");
		return response;
	}
}