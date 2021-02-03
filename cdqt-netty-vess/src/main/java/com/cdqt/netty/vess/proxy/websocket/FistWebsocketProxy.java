package com.cdqt.netty.vess.proxy.websocket;

import com.cdqt.netty.vess.config.entity.FistTarget;
import com.cdqt.netty.vess.proxy.IFistProxy;

/**
 * 本地代理工具
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistWebsocketProxy implements IFistProxy<FistTarget>{
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistWebsocketProxy() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistLocalProxyHolder {
		/**
		 * {@link FistWebsocketProxy} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistWebsocketProxy INSTANCE = new FistWebsocketProxy();
	}

	/**
	 * 获取{@link SingleFistLocalProxyHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistLocalProxyHolder#INSTANCE} 静态常量实例
	 */
	public static FistWebsocketProxy getInstance() {
		return SingleFistLocalProxyHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.vess.proxy.IFistProxy#call(com.cdqt.netty.vess.config.entity.FistTarget) 
	 */
	@Override
	public Object call(FistTarget target) {
		return null;
	}
}
