package com.cdqt.netty.vess.handler.http;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdqt.netty.base.result.FistResult;
import com.cdqt.netty.base.result.FistStatus;
import com.cdqt.netty.vess.proxy.FistCall;
import com.cdqt.netty.vess.targets.FistTarget;
import com.cdqt.netty.vess.tools.http.request.FistHttpRequest;
import com.cdqt.netty.vess.tools.http.response.FistHttpResponse;
import com.cdqt.netty.vess.tools.http.uri.FistHttpUri;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * FistHttpServerHandler
 *
 * @author LiuGangQiang Create in 2020/12/16
 */
public class FistHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistHttpServerHandler.class);

	/**
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/**
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		StringWriter sw = new StringWriter();
		cause.printStackTrace(new PrintWriter(sw, true));
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error("Http Server Happen Error [{}]", sw.getBuffer().toString());
		}
		/* 出现异常统一处理并输出 */
		FistResult<?> result = new FistResult<>(FistStatus.ERROR).setMsg(cause.toString());
		String resultStr = JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
		FullHttpResponse response = FistHttpResponse.getInstance().outJson(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(resultStr.getBytes()));
		ctx.write(response).addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		/* 识别请求地址和请求方法并封装 */
		String uri = URLDecoder.decode(request.uri().split("\\?")[0], "UTF-8");
		/* 判断URL地址是否合法 */
		if (!FistHttpUri.getInstance().judgeUrl(uri)) {
			/* 地址不合法 */
			FistResult<?> result = new FistResult<>(FistStatus.ERROR).setKey("fist.http.request.uri.error", uri);
			String resultStr = JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
			FullHttpResponse response = FistHttpResponse.getInstance().outJson(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(resultStr.getBytes()));
			ctx.write(response).addListener(ChannelFutureListener.CLOSE);
		}
		// FIXME 这里需要校验服务是否一致
		/* 获取目标服务和方法 */
		String[] uris = uri.substring(1).split("/");
		String[] targets = uris[1].split("[.]");
		String bizName = targets[0];
		String funName = targets[1];
		/* 构造请求参数 设置业务名和方法名 */
		FistTarget target = new FistTarget(bizName, funName);
		/* 设置方法类型 */
		target.setMethod(request.method());
		/* 设置头部参数 头部参数直接可采用KV都可以采用字符串格式 */
		target.setHeaderParams(FistHttpRequest.getInstance().getHeaderParams(request));
		/* 设置PATH参数 所有方法都可能带有PATH参数 */
		target.setPathParams(FistHttpRequest.getInstance().getPathParams(request));
		/* 设置URL参数 所有方法都可能带有URL参数 */
		target.setQueryParams(FistHttpRequest.getInstance().getQueryParams(request));
		/* 设置Body参数 Body参数原则上所有方法都支持，但是大部分框架或工具都过滤掉了GET/DELETE方法的Body参数 所以在这建议只在POST/PUT方法中使用Body传参 */
		target.setBodyParams(FistHttpRequest.getInstance().getBodyParams(request));
		/* 反射调用方法并处理结果 */
		Object result = FistCall.getInstance().callBiz(target);
		String resultStr = JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		/* 返回结果给调用者 */
		FullHttpResponse response = FistHttpResponse.getInstance().outJson(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(resultStr.getBytes()));
		ctx.write(response).addListener(ChannelFutureListener.CLOSE);
	}

}
