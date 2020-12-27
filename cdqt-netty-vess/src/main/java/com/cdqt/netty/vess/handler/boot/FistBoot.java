package com.cdqt.netty.vess.handler.boot;

import com.cdqt.netty.vess.handler.config.helper.VessConfigHelper;

/**
 * FistBoot
 *
 * @author LiuGangQiang Create in 2020/12/27
 */
public class FistBoot {
	// private static final Logger LOGGER = LoggerFactory.getLogger(FistBoot.class);
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
			/* 判断服务类型 */
			switch (FistServerType.fromString(svr.getType())) {
			case HTTP:
				break;
			case TCP:
				break;
			case UDP:
				break;
			case WEBSOCKET:
				break;
			default:
				break;
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
