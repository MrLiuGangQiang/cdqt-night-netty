package com.cdqt.netty.vess.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.vess.boot.register.FistServerRegister;
import com.cdqt.netty.vess.boot.register.FistServerType;
import com.cdqt.netty.vess.config.entity.NetConfig;
import com.cdqt.netty.vess.config.helper.VessConfigHelper;

/**
 * FistBoot
 *
 * @author LiuGangQiang Create in 2020/12/27
 */
public class FistBoot {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistBoot.class);
	/**
	 * 服务注册对象
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	private FistServerRegister register;

	/**
	 * 默认构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	public FistBoot() {
		this.register = new FistServerRegister();
	}

	/**
	 * 读取配置文件并注册服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @return {@link FistBoot}
	 */
	public FistBoot load() {
		/* 遍历所有配置 */
		VessConfigHelper.getSvrConfig().forEach((id, svr) -> {
			/* 判断是否自启动 */
			if (svr.getAuto()) {
				/* 获取服务配置 */
				NetConfig net = svr.getNetConfig();
				/* 设置服务为已启动 */
				svr.setRun(true);
				/* 判断服务类型 */
				switch (FistServerType.fromString(svr.getType())) {
				case HTTP:
					svr.setGroup(register.registerHttpServer(net.getHost(), net.getPort()));
					break;
				case TCP:
					svr.setGroup(register.registerTcpServer(net.getHost(), net.getPort()));
					break;
				case UDP:
					svr.setGroup(register.registerUdpServer(net.getHost(), net.getPort()));
					break;
				case WEBSOCKET:
					svr.setGroup(register.registerWebSocketServer(net.getHost(), net.getPort()));
					break;
				case THREAD:
					break;
				default:
					if (LOGGER.isWarnEnabled()) {
						LOGGER.warn("framework server [{}] not support [{}] protocol", svr.getId(), svr.getType());
					}
					break;
				}
			}
		});
		return this;
	}

	/**
	 * 启动服务
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	public void start() {
		register.run();
	}
}
