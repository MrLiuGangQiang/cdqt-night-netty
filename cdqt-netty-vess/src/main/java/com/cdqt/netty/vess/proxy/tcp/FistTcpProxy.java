package com.cdqt.netty.vess.proxy.tcp;

import com.cdqt.netty.vess.proxy.IFistProxy;
import com.cdqt.netty.vess.targets.FistTarget;

/**
 * TCP代理工具
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistTcpProxy implements IFistProxy<FistTarget> {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistTcpProxy() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistTcpProxyHolder {
		/**
		 * {@link FistTcpProxy} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistTcpProxy INSTANCE = new FistTcpProxy();
	}

	/**
	 * 获取{@link SingleFistTcpProxyHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistTcpProxyHolder#INSTANCE} 静态常量实例
	 */
	public static FistTcpProxy getInstance() {
		return SingleFistTcpProxyHolder.INSTANCE;
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
