package com.cdqt.netty.vess.handler.web;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * FistWebServerHandler 
 *
 * @author LiuGangQiang Create in 2020/12/16
 */
public class FistWebServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object) 
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
