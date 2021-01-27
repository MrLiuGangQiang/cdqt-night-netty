package com.cdqt.netty.vess.tools.http.request;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdqt.netty.tool.valid.StringUtil;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;

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
		System.out.println(contentType);
		if (contentType != null && StringUtil.containsIgnoreCase(contentType, HttpHeaderValues.APPLICATION_JSON)) {
			/* application/json 参数在body内是一个json格式的字符串 */
			String jsonStr = request.content().toString(Charset.forName("UTF-8"));
			System.out.println(jsonStr);
		} else if (contentType != null && StringUtil.containsIgnoreCase(contentType, HttpHeaderValues.MULTIPART_FORM_DATA)) {
			/* multipart/form-data 类型 参数包含文件 */
			HttpPostMultipartRequestDecoder decoder = new HttpPostMultipartRequestDecoder(new DefaultHttpDataFactory(false), request);
			List<InterfaceHttpData> datas = decoder.getBodyHttpDatas();
			for (InterfaceHttpData data : datas) {
				if (data.getHttpDataType() == HttpDataType.Attribute) {
					Attribute attribute = (Attribute) data;
					System.out.println(attribute.getName() + "=" + attribute.getValue());
				} else if (data.getHttpDataType() == HttpDataType.FileUpload) {
					FileUpload upload = (FileUpload) data;
					System.out.println(upload.getFilename());
				}
			}
		} else if (contentType == null || StringUtil.containsIgnoreCase(contentType, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED)) {
			/* application/x-www-form-urlencoded类型最原始的传参方式 */
		}else {
			/* 其他类型暂时当做字符串处理 */
		}
		return null;
	}
}
