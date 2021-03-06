package com.cdqt.netty.base;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.codec.FistCodec;
import com.cdqt.netty.base.message.FistMessage;
import com.cdqt.netty.base.message.FistMessageType;
import com.cdqt.netty.base.message.FistProtocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer {
	private final static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

	private void bind(String host, int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup);
		bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childOption(ChannelOption.TCP_NODELAY, false);
		bootstrap.childHandler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new FistCodec());
				pipeline.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
				pipeline.addLast(new SimpleChannelInboundHandler<FistProtocol>() {
					private int total = 0;

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
						byte[] data = "im's server".getBytes(Charset.forName("UTF-8"));
						ctx.writeAndFlush(new FistProtocol(new FistMessage(FistMessageType.ANSWER, data)));
					}

					@Override
					public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
						LOGGER.info("心跳计数---->{}", ++total);
						if (total >= 5) {
							LOGGER.info("超过{}个周期未接收到任何数据断开连接", total);
							ctx.close();
						}
					}
				});
			}
		});
		try {
			ChannelFuture future = bootstrap.bind(host, port).sync();
			LOGGER.info("服务端启动成功，监听地址[{}:{}]", host, port);
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new NettyServer().bind("127.0.0.1", 8888);
	}
}
