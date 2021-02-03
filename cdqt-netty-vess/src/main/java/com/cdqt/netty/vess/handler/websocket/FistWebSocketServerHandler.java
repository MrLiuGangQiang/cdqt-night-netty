package com.cdqt.netty.vess.handler.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.vess.handler.udp.FistUdpServerHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * FistWebSocketServerHandler
 *
 * @author LiuGangQiang Create in 2020/12/16
 */
public class FistWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistUdpServerHandler.class);

	/**
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		/* 连接完成关闭通道 */
		ctx.close();
	}

	/**
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		/* 发生异常 打印日志并关闭连接 */
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error("web socket server happen error [{}]", cause.toString());
		}
		ctx.close();
	}

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
		System.out.println(frame);
		// FIXME 这里处理WebSocket请求
	}

}
