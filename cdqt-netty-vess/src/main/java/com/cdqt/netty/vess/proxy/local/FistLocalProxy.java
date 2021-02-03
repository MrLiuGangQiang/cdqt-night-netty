package com.cdqt.netty.vess.proxy.local;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdqt.netty.base.annotation.FistBody;
import com.cdqt.netty.base.annotation.FistFile;
import com.cdqt.netty.base.annotation.FistHeader;
import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.annotation.FistQuery;
import com.cdqt.netty.base.exception.FistException;
import com.cdqt.netty.base.model.FistBaseEntity;
import com.cdqt.netty.base.model.FistMethod;
import com.cdqt.netty.base.result.FistResult;
import com.cdqt.netty.base.result.FistStatus;
import com.cdqt.netty.tool.common.ArrayUtil;
import com.cdqt.netty.tool.common.IntegerUtil;
import com.cdqt.netty.tool.common.ListUtil;
import com.cdqt.netty.tool.common.StringUtil;
import com.cdqt.netty.vess.config.entity.BizConfig;
import com.cdqt.netty.vess.config.entity.FistTarget;
import com.cdqt.netty.vess.config.helper.BizConfigHelper;
import com.cdqt.netty.vess.converters.FistConverterFactory;
import com.cdqt.netty.vess.proxy.IFistProxy;
import com.cdqt.netty.vess.tools.clazz.ClassScanUtil;

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
	 * @throws Exception
	 * @see com.cdqt.netty.vess.proxy.IFistProxy#call(com.cdqt.netty.vess.config.entity.FistTarget)
	 */
	@Override
	public Object call(FistTarget target) throws Exception {
		/* 获取对应业务配置 */
		String bizName = target.getBizName();
		BizConfig bizConfig = BizConfigHelper.getBizConfig(bizName);
		/* 判断配置是否为空 为空则提示 */
		if (bizConfig == null) {
			LOGGER.warn("the call cannot continue because bizconfig [{}] is null", bizName);
			return new FistResult<>(FistStatus.ERROR).setKey("fist.bizconfig.is.null", bizName);
		}
		/* 判断Jar字段是否为空 */
		String jar = bizConfig.getJar();
		if (StringUtil.isBlank(jar)) {
			LOGGER.warn("the call cannot continue because bizconfig [{}] jar filed is null", bizName);
			return new FistResult<>(FistStatus.ERROR).setKey("fist.bizconfig.jar.is.empty", bizName);
		}
		/* 设置包路径和需要扫描的包 */
		target.setPcks(bizConfig.getPcks());
		target.setJar(jar);
		/* 设置是否扫描子包 */
		target.setIsergodic(bizConfig.getIsergodic());
		/* 获取目标方法 */
		FistMethod fistMethod = getBizMethod(target);
		Object entity = fistMethod.getEntity();
		Method method = fistMethod.getMethod();
		/* 获取方法参数 */
		Parameter[] parameters = method.getParameters();
		/* 判断参数是否为空 */
		if (ArrayUtil.isNotEmpty(parameters)) {
			/* 定义变量存储参数 */
			List<Object> params = new ArrayList<>();
			/* 循环遍历目标方法参数 */
			for (Parameter parameter : parameters) {
				/* 获取目标参数类型 */
				Type targetType = parameter.getParameterizedType();
				/* 判断参数是否是Header参数 */
				FistHeader header = parameter.getDeclaredAnnotation(FistHeader.class);
				if (header != null) {
					/* 注解不等于NULL表示参数是Header参数 */
					String headerParam = target.getHeaderParams().get(header.value());
					if (StringUtil.isBlank(headerParam)) {
						throw new FistException("framework encapsulation header parameter [{0}] error", header.value());
					} else {
						/* 设置参数 */
						params.add(FistConverterFactory.getConverter(targetType, headerParam.getClass()).convert(headerParam, targetType));
						continue;
					}
				}
				/* 判断参数是否是Query参数 */
				FistQuery query = parameter.getDeclaredAnnotation(FistQuery.class);
				if (query != null) {
					/* 注解不等于NULL表示参数是Query参数 */
					Object queryParam = target.getQueryParams().get(query.value());
					if (queryParam == null) {
						throw new FistException("framework encapsulation query parameter [{0}] error", query.value());
					} else {
						/* 设置参数 */
						params.add(FistConverterFactory.getConverter(targetType, queryParam.getClass()).convert(queryParam, targetType));
						continue;
					}
				}
				/* 判断参数是否是Body参数 */
				FistBody body = parameter.getDeclaredAnnotation(FistBody.class);
				if (body != null) {
					/* 注解不等于NULL表示参数是Body参数 */
					Object bodyParam = target.getBodyParams().get(StringUtil.isBlank(body.value()) ? null : body.value());
					if (bodyParam == null) {
						throw new FistException("framework encapsulation body parameter [{0}] error", body.value());
					} else {
						/* 设置参数 */
						params.add(FistConverterFactory.getConverter(targetType, bodyParam.getClass()).convert(bodyParam, targetType));
						continue;
					}
				}
				/* 判断参数是否是File参数 */
				FistFile file = parameter.getDeclaredAnnotation(FistFile.class);
				if (file != null) {
					/* 注解不等于NULL表示参数是File参数 */
					Object fileParam = target.getBodyParams().get(file.value());
					if (fileParam == null) {
						throw new FistException("framework encapsulation file [{0}] parameter error", file.value());
					} else {
						/* 设置参数 */
						params.add(FistConverterFactory.getConverter(targetType, fileParam.getClass()).convert(fileParam, targetType));
						continue;
					}
				}
				/* 如果都没有参数注解则考虑普通对象参数封装 */
				if (FistBaseEntity.class.isAssignableFrom((Class<?>) targetType)) {
					/* 参数是实体对象 */
					Object queryParam = target.getQueryParams();
					if (queryParam == null) {
						throw new FistException("framework encapsulation entity parameter error");
					} else {
						/* 设置参数 */
						params.add(FistConverterFactory.getConverter(targetType, queryParam.getClass()).convert(queryParam, targetType));
						continue;
					}
				}
			}
			if (IntegerUtil.isEq(parameters.length, params.size())) {
				return method.invoke(entity, params.toArray());
			} else {
				throw new FistException("framework converter {0}:{1} error because parameter number error", entity.getClass().getTypeName(), method.getName());
			}
		} else {
			/* 参数为空直接反射调用即可 */
			return method.invoke(entity);
		}
	}

	/**
	 * getBizMethod
	 *
	 * @author LiuGangQiang Create in 2021/01/31
	 * @param target 目标对象
	 * @return {@link FistMethod}
	 * @throws Exception
	 */
	private FistMethod getBizMethod(FistTarget target) throws Exception {
		String bizName = target.getBizName();
		String funName = target.getFunName();
		/* 先从缓存获取 */
		FistMethod fistMethod = bizMethods.get(bizName + DIVISION + funName);
		if (fistMethod != null) {
			/* 不为空直接返回 */
			return fistMethod;
		} else {
			/* 为空则扫描对应的包并加载到缓存 */
			String path = CATALOG + SEPARATOR + PLUGIN + SEPARATOR + target.getJar();
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
			/* 获取Jar包内所有满足条件的类全限定名 并缓存 */
			List<String> classs = bizClasss.get(target.getBizName());
			if (ListUtil.isEmpty(classs)) {
				classs = ClassScanUtil.getClassByJar(path, target.getPcks(), target.getIsergodic());
				bizClasss.put(target.getBizName(), classs);
			}
			for (String clz : classs) {
				/* 构造一个类实例 */
				Class<?> clazz = Class.forName(clz);
				Object entity = clazz.newInstance();
				/* 获取所有方法 */
				Method[] declaredMethods = clazz.getDeclaredMethods();
				for (Method method : declaredMethods) {
					/* 获取方法的注解 只有加了注解的方法才被扫描 */
					FistMapping mapping = method.getDeclaredAnnotation(FistMapping.class);
					if (mapping != null) {
						/* 获取所有注解value */
						String[] values = mapping.value();
						for (String value : values) {
							bizMethods.put(target.getBizName() + DIVISION + value, new FistMethod(clazz, entity, method));
						}
					}
				}
			}
			return bizMethods.get(bizName + DIVISION + funName);
		}
	}
}
