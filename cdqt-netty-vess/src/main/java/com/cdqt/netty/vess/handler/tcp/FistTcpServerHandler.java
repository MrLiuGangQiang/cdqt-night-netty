package com.cdqt.netty.vess.handler.tcp;

import com.cdqt.netty.base.message.FistProtocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * FistTcpServerHandler 
 *
 * @author LiuGangQiang Create in 2020/12/16
 */
public class FistTcpServerHandler extends SimpleChannelInboundHandler<FistProtocol>{

	/**
	 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object) 
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FistProtocol msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
