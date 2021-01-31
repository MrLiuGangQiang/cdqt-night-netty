package com.cdqt.netty.vess.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.vess.config.entity.BizConfig;
import com.cdqt.netty.vess.config.helper.BizConfigHelper;
import com.cdqt.netty.vess.config.helper.BizType;
import com.cdqt.netty.vess.proxy.http.FistHttpProxy;
import com.cdqt.netty.vess.proxy.local.FistLocalProxy;
import com.cdqt.netty.vess.proxy.tcp.FistTcpProxy;
import com.cdqt.netty.vess.proxy.udp.FistUdpProxy;
import com.cdqt.netty.vess.proxy.websocket.FistWebsocketProxy;
import com.cdqt.netty.vess.targets.FistTarget;

/**
 * 代理调用工具类
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistCall {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistCall.class);
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistCall() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistCallHolder {
		/**
		 * {@link FistCall} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistCall INSTANCE = new FistCall();
	}

	/**
	 * 获取{@link SingleFistCallHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistCallHolder#INSTANCE} 静态常量实例
	 */
	public static FistCall getInstance() {
		return SingleFistCallHolder.INSTANCE;
	}

	/**
	 * 调用代理方法
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @param <T>    {@link FistTarget}及其子类
	 * @param target 目标对象
	 * @return {@link Object}
	 * @throws Exception 
	 */
	public <T extends FistTarget> Object callBiz(T target) throws Exception {
		/* 获取通用的业务名称 判断是何种类型决定用何种方式进行调用 */
		BizConfig bizConfig = BizConfigHelper.getBizConfig(target.getBizName());
		switch (BizType.fromString(bizConfig==null?null:bizConfig.getType())) {
		case LOCAL:
			/* 本地反射调用 */
			return FistLocalProxy.getInstance().call(target);
		case TCP:
			/* TCP调用 */
			return FistTcpProxy.getInstance().call(target);
		case UDP:
			/* UDP调用 */
			return FistUdpProxy.getInstance().call(target);
		case HTTP:
			/* HTTP调用 */
			return FistHttpProxy.getInstance().call(target);
		case WEBSOCKET:
			/* WEBSOCKET调用 */
			return FistWebsocketProxy.getInstance().call(target);
		default:
			/* 未定义的调用方式 */
			LOGGER.warn("The Call Cannot Continue Because BizConfig [{}] Config Error", target.getBizName());
			return null;
		}
	}
}
