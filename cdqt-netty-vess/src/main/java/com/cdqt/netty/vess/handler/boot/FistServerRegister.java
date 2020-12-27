package com.cdqt.netty.vess.handler.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.codec.FistCodec;
import com.cdqt.netty.vess.handler.http.FistHttpServerHandler;
import com.cdqt.netty.vess.handler.tcp.FistTcpServerHandler;
import com.cdqt.netty.vess.handler.udp.FistUdpServerHandler;
import com.cdqt.netty.vess.handler.websocket.FistWebSocketServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 服务注册类
 *
 * @author LiuGangQiang Create in 2020/12/27
 */
public class FistServerRegister {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistServerRegister.class);
	/**
	 * 通道组
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	private ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 注册Http服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param host 主机地址
	 * @param port 主机端口
	 * @return {@link EventLoopGroup}
	 */
	public EventLoopGroup registerHttpServer(final String host, final int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			if (port <= 0 || port > 65535) {
				throw new IllegalArgumentException("register port should [0-65535] but this is " + port);
			}
			ServerBootstrap bootstrap = new ServerBootstrap();
			/* 设置Reactor线程 */
			bootstrap.group(bossGroup, workerGroup);
			/* 设置Nio类型的Channel */
			bootstrap.channel(NioServerSocketChannel.class);
			/* 设置通道参数 */
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			/* 设置子通道参数 */
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new FistHttpServerHandler());
				}
			});
			Channel channel = bootstrap.bind(host, port).sync().channel();
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Register Http Server [{}:{}]", host, port);
			}
			channels.add(channel);
			return bootstrap.config().group();
		} catch (Exception e) {
			LOGGER.error("Register Http Server Error Message {}", e.toString());
		}
		return null;
	}

	/**
	 * 注册Tcp服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param host 主机地址
	 * @param port 主机端口
	 * @return {@link EventLoopGroup}
	 */
	public EventLoopGroup registerTcpServer(final String host, final int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			if (port <= 0 || port > 65535) {
				throw new IllegalArgumentException("register port should [0-65535] but this is " + port);
			}
			ServerBootstrap bootstrap = new ServerBootstrap();
			/* 设置Reactor线程 */
			bootstrap.group(bossGroup, workerGroup);
			/* 设置Nio类型的Channel */
			bootstrap.channel(NioServerSocketChannel.class);
			/* 设置通道参数 */
			bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new FistCodec());
					pipeline.addLast(new FistTcpServerHandler());
				}
			});
			Channel channel = bootstrap.bind(host, port).sync().channel();
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Register Tcp Server [{}:{}]", host, port);
			}
			channels.add(channel);
			return bootstrap.config().group();
		} catch (Exception e) {
			LOGGER.error("Register Tcp Server Error Message {}", e.toString());
		}
		return null;
	}

	/**
	 * 注册WebSocket服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param host 主机地址
	 * @param port 主机端口
	 * @return {@link EventLoopGroup}
	 */
	public EventLoopGroup registerWebSocketServer(final String host, final int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			if (port <= 0 || port > 65535) {
				throw new IllegalArgumentException("register port should [0-65535] but this is " + port);
			}
			ServerBootstrap bootstrap = new ServerBootstrap();
			/* 设置Reactor线程 */
			bootstrap.group(bossGroup, workerGroup);
			/* 设置Nio类型的Channel */
			bootstrap.channel(NioServerSocketChannel.class);
			/* 设置通道参数 */
			bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			bootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
					pipeline.addLast(new HttpContentCompressor());
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
					pipeline.addLast(new FistWebSocketServerHandler());
				}
			});
			Channel channel = bootstrap.bind(host, port).sync().channel();
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Register WebSocket Server [{}:{}]", host, port);
			}
			channels.add(channel);
			return bootstrap.config().group();
		} catch (Exception e) {
			LOGGER.error("Register WebSocket Server Error Message {}", e.toString());
		}
		return null;
	}

	/**
	 * 注册Udp服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param host 主机地址
	 * @param port 主机端口
	 * @return {@link EventLoopGroup}
	 */
	public EventLoopGroup registerUdpServer(final String host, final int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			if (port <= 0 || port > 65535)
				throw new IllegalArgumentException("register port should [0-65535] but this is " + port);
			Bootstrap bootstrap = new Bootstrap();
			/* 设置Reactor线程 */
			bootstrap.group(group);
			/* 设置Nio类型的Channel */
			bootstrap.channel(NioDatagramChannel.class);
			/* 设置通道参数 */
			bootstrap.option(ChannelOption.SO_BROADCAST, true);
			bootstrap.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new FistCodec());
					pipeline.addLast(new FistUdpServerHandler());
				}
			});
			Channel channel = bootstrap.bind(host, port).sync().channel();
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Register Udp Server [{}:{}]", host, port);
			}
			channels.add(channel);
			return bootstrap.config().group();
		} catch (Exception e) {
			LOGGER.error("Register Udp Server Error Message {}", e.toString());
		}
		return null;
	}

	/**
	 * 启动服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	public void run() {
		for (Channel ch : this.channels) {
			try {
				ch.closeFuture().sync();
			} catch (InterruptedException e) {
				LOGGER.error("Fist Vess Start UP Error {}", e.toString());
			} finally {
				/* 这里释放资源 */
			}

		}
	}
}
