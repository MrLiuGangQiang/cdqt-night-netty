package com.cdqt.netty.vess.handler.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.message.FistProtocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * FistTcpServerHandler
 *
 * @author LiuGangQiang Create in 2020/12/16
 */
public class FistTcpServerHandler extends SimpleChannelInboundHandler<FistProtocol> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistTcpServerHandler.class);

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
			LOGGER.error("tcp server happen error [{}]", cause.toString());
		}
		ctx.close();
	}

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FistProtocol protocol) throws Exception {
		System.out.println(protocol);
		// FIXME 这里处理TCP请求
	}

}
