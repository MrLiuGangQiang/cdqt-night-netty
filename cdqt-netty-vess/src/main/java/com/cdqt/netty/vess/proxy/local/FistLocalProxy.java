package com.cdqt.netty.vess.proxy.local;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.model.FistBean;
import com.cdqt.netty.base.model.FistMethod;
import com.cdqt.netty.base.result.FistResult;
import com.cdqt.netty.base.result.FistStatus;
import com.cdqt.netty.tool.clazz.ClassScanUtil;
import com.cdqt.netty.tool.valid.ListUtil;
import com.cdqt.netty.tool.valid.StringUtil;
import com.cdqt.netty.vess.config.entity.BizConfig;
import com.cdqt.netty.vess.config.helper.BizConfigHelper;
import com.cdqt.netty.vess.proxy.IFistProxy;
import com.cdqt.netty.vess.targets.FistTarget;

/**
 * 本地代理工具
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistLocalProxy implements IFistProxy<FistTarget> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FistLocalProxy.class);
	/**
	 * 分割符
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private static final String DIVISION = "::";
	/**
	 * 业务类名集合
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private static Map<String, List<String>> bizClasss = new HashMap<String, List<String>>();
	/**
	 * 业务类实例集合
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private static Map<String, List<FistBean>> bizBeans = new HashMap<String, List<FistBean>>();
	/**
	 * 业务方法集合
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 */
	private static Map<String, FistMethod> bizMethods = new HashMap<String, FistMethod>();
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
		/* 设置包路径 */
		target.setPcks(bizConfig.getPcks());
		/* 设置是否扫描子包 */
		target.setIsergodic(bizConfig.getIsergodic());
		/* 判断Jar字段是否为空 */
		String jar = bizConfig.getJar();
		if (StringUtil.isBlank(jar)) {
			LOGGER.warn("The Call Cannot Continue Because BizConfig [{}] Jar Filed Is Null", bizName);
			return new FistResult<>(FistStatus.ERROR).setKey("fist.bizconfig.jar.is.empty", bizName);
		}
		/* 拼接Jar包路径 */
		String path = CATALOG + SEPARATOR + PLUGIN + SEPARATOR + jar;
		/* 获取Jar包内所有满足条件的类全限定名 并缓存 */
		List<String> classs = bizClasss.get(target.getBizName());
		if (ListUtil.isEmpty(classs)) {
			classs = ClassScanUtil.getClassByJar(path, target.getPcks(), true);
			bizClasss.put(target.getBizName(), classs);
		}
		try {
			List<FistBean> beans = getBizBeans(path, bizName, classs);
			/* 获取目标方法 */
		} catch (Exception e) {
			LOGGER.error("Fist Load Jar Beans Error {}", e.getMessage());
		}
		return path;
	}

	/**
	 * 获取Jar包类扫描成功的类实例集合
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 * @param bizName 路径
	 * @param bizName 业务名
	 * @param classs  类名集合
	 * @return {@link List}
	 * @throws Exception
	 */
	private List<FistBean> getBizBeans(String path, String bizName, List<String> classs) throws Exception {
		/* 先从缓存内获取 */
		List<FistBean> beans = bizBeans.get(bizName);
		if (!ListUtil.isEmpty(beans)) {
			/* 如果存在则直接返回 */
			return beans;
		} else {
			/* 不存在则加载类并缓存 */
			URL url = new File(path).toURI().toURL();
			Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = add.isAccessible();
			try {
				if (!accessible)
					add.setAccessible(true);
				URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
				add.invoke(classLoader, url);
			} finally {
				add.setAccessible(accessible);
			}
			/* 循环处理每一个类 */
			beans = new ArrayList<>();
			for (String clz : classs) {
				/* 构造一个类实例 */
				FistBean bean = new FistBean();
				Class<?> clazz = Class.forName(clz);
				Object entity = clazz.newInstance();
				bean.setId(clz);
				bean.setClazz(clazz);
				bean.setEntity(entity);
				/* 获取所有方法 */
				Method[] declaredMethods = clazz.getDeclaredMethods();
				Map<String, Method> methods = new HashMap<>();
				for (Method method : declaredMethods) {
					/* 获取方法的注解 只有加了注解的方法才被扫描 */
					FistMapping mapping = method.getDeclaredAnnotation(FistMapping.class);
					if (mapping != null) {
						/* 获取所有注解value */
						String[] values = mapping.value();
						for (String value : values) {
							methods.put(value, method);
							bizMethods.put(bizName + DIVISION + value, new FistMethod(clazz, entity, method));
						}
					}
				}
				bean.setMethods(methods);
				beans.add(bean);
				bizBeans.put(bizName, beans);
			}
			return beans;
		}
	}

	/**
	 * 获取目标方法
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 * @param bizName 业务名
	 * @param value   注解值
	 * @return {@link FistMethod}
	 */
	private FistMethod getBizMethod(String bizName, String value) {
		return bizMethods.get(bizName + DIVISION + value);
	}
}
