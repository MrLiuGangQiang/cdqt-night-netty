package com.cdqt.netty.vess.proxy;

import com.cdqt.netty.vess.targets.FistTarget;

/**
 * 本地代理工具
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistUdpProxy implements IFistProxy<FistTarget>{
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistUdpProxy() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistLocalProxyHolder {
		/**
		 * {@link FistUdpProxy} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistUdpProxy INSTANCE = new FistUdpProxy();
	}

	/**
	 * 获取{@link SingleFistLocalProxyHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistLocalProxyHolder#INSTANCE} 静态常量实例
	 */
	public static FistUdpProxy getInstance() {
		return SingleFistLocalProxyHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.vess.proxy.IFistProxy#call(com.cdqt.netty.vess.targets.FistTarget) 
	 */
	@Override
	public Object call(FistTarget target) {
		// TODO Auto-generated method stub
		return null;
	}
}
