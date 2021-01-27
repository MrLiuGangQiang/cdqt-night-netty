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
		FistResult<?> result = new FistResult<>(FistStatus.ERROR).setMsg(cause.getMessage());
		String resultStr = JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
		FullHttpResponse response = FistHttpResponse.getInstance().outJson(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(resultStr.getBytes()));
		ctx.write(response).addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		/* 第一步 识别请求地址和请求方法并封装 */
		String uri = URLDecoder.decode(request.uri().split("\\?")[0], "UTF-8");
		/* 判断URL地址是否合法 */
		if (!FistHttpUri.getInstance().judgeUrl(uri)) {
			/* 地址不合法 */
			FistResult<?> result = new FistResult<>(FistStatus.ERROR).setKey("fist.http.request.uri.error",uri);
			String resultStr = JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
			FullHttpResponse response = FistHttpResponse.getInstance().outJson(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(resultStr.getBytes()));
			ctx.write(response).addListener(ChannelFutureListener.CLOSE);
		}
		//String[] uris = uri.substring(1).split("/");
		/* 第二步 根据请求方法类型处理参数并封装 */
		/* 第三步 匹配目标Jar和目标方法并动态加载 */
		/* 第四步 获取目标方法参数和注解等并按需封装参数 */
		/* 第五步 反射调用方法并处理结果 */
		/* 第六步 返回统一格式结果给调用者 */
		FullHttpResponse response = FistHttpResponse.getInstance().outJson(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
				Unpooled.wrappedBuffer(JSONObject.toJSONString(new FistResult<>(FistStatus.OK), SerializerFeature.WriteMapNullValue).getBytes()));
		ctx.write(response).addListener(ChannelFutureListener.CLOSE);
	}

}
