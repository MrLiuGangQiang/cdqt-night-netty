package com.cdqt.netty.vess.proxy;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.result.FistResult;
import com.cdqt.netty.base.result.FistStatus;
import com.cdqt.netty.tool.valid.StringUtil;
import com.cdqt.netty.vess.config.entity.BizConfig;
import com.cdqt.netty.vess.config.helper.BizConfigHelper;
import com.cdqt.netty.vess.targets.FistTarget;

/**
 * 本地代理工具
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistLocalProxy implements IFistProxy<FistTarget> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistLocalProxy.class);
	/**
	 * 文件目录分隔符
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String SEPARATOR = File.separator;
	/**
	 * 插件根目录
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String CATALOG = System.getProperty("user.dir");
	/**
	 * 插件文件夹
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String PLUGIN = "plugin";

	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistLocalProxy() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistLocalProxyHolder {
		/**
		 * {@link FistLocalProxy} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistLocalProxy INSTANCE = new FistLocalProxy();
	}

	/**
	 * 获取{@link SingleFistLocalProxyHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistLocalProxyHolder#INSTANCE} 静态常量实例
	 */
	public static FistLocalProxy getInstance() {
		return SingleFistLocalProxyHolder.INSTANCE;
	}

	/**
	 * @see com.cdqt.netty.vess.proxy.IFistProxy#call(com.cdqt.netty.vess.targets.FistTarget)
	 */
	@Override
	public Object call(FistTarget target) {
		/* 获取对应业务配置 */
		String bizName = target.getBizName();
		BizConfig bizConfig = BizConfigHelper.getBizConfig(bizName);
		/* 判断配置是否为空 为空则提示 */
		if (bizConfig == null) {
			LOGGER.warn("The Call Cannot Continue Because BizConfig [{}] Is Null", bizName);
			return new FistResult<>(FistStatus.ERROR).setKey("fist.bizconfig.is.null", bizName);
		}
		/* 判断Jar字段是否为空 */
		String jar = bizConfig.getJar();
		if (StringUtil.isBlank(jar)) {
			LOGGER.warn("The Call Cannot Continue Because BizConfig [{}] Jar Filed Is Null", bizName);
			return new FistResult<>(FistStatus.ERROR).setKey("fist.bizconfig.jar.is.empty", bizName);
		}
		/* 获取jar包 */
		String path = CATALOG + SEPARATOR + PLUGIN + SEPARATOR + jar;
		return path;
	}
}
