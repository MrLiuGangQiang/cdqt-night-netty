package com.cdqt.netty.base;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.codec.FistCodec;
import com.cdqt.netty.base.message.FistMessage;
import com.cdqt.netty.base.message.FistMessageType;
import com.cdqt.netty.base.message.FistProtocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClient {
	private final static Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);

	private void bind(String host, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new FistCodec());
					pipeline.addLast(new IdleStateHandler(0, 10, 0, TimeUnit.SECONDS));
					pipeline.addLast(new SimpleChannelInboundHandler<FistProtocol>() {

						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							byte[] data = "im's client".getBytes(Charset.forName("UTF-8"));
							ctx.writeAndFlush(new FistProtocol(new FistMessage(FistMessageType.REQUEST, data)));
						}

						@Override
						protected void channelRead0(ChannelHandlerContext ctx, FistProtocol protocol) throws Exception {
							FistMessage message = protocol.get(FistMessage.class);
							switch (message.getType()) {
							case FistMessageType.DEFAULT_TYPE_REQUEST:
								LOGGER.info("接收到请求消息：{}", message);
								break;
							case FistMessageType.DEFAULT_TYPE_ANSWER:
								LOGGER.info("接收到应答消息：{}", message);
								break;
							case FistMessageType.DEFAULT_TYPE_HEARTBEAT:
								LOGGER.info("接收到心跳消息：{}", message);
								break;
							}
							// byte[] data = "im's server".getBytes(Charset.forName("UTF-8"));
							// ctx.writeAndFlush(new FistProtocol(new
							// FistMessage(FistMessageType.REQUEST, data)));
						}

						@Override
						public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
							ctx.writeAndFlush(new FistProtocol(new FistMessage(FistMessageType.HEARTBEAT, null)));
						}
					});
				}
			});
			ChannelFuture futrue = bootstrap.connect(new InetSocketAddress(host, port)).sync();
			LOGGER.info("客户端启动成功，请求地址[{}:{}]", host, port);
			futrue.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws IOException {
		new NettyClient().bind("127.0.0.1", 8888);
	}
}
