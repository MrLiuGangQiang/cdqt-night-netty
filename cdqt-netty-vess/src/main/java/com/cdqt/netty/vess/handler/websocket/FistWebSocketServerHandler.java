package com.cdqt.netty.vess.handler.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * FistWebSocketServerHandler
 *
 * @author LiuGangQiang Create in 2020/12/16
 */
public class FistWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// TODO Auto-generated method stub

	}

}
