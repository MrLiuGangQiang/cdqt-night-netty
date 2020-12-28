package com.cdqt.netty.vess.config.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cdqt.netty.tool.json.JsonFileUtil;
import com.cdqt.netty.vess.config.entity.NetConfig;
import com.cdqt.netty.vess.config.entity.SvrConfig;

/**
 * VessConfigHelper
 *
 * @author LiuGangQiang Create in 2020/12/20
 */
public class VessConfigHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(VessConfigHelper.class);
	/**
	 * 服务配置文件集合
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static Map<String, SvrConfig> svrConfigMap = new ConcurrentHashMap<String, SvrConfig>();
	/**
	 * 网络配置文件集合
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static Map<String, NetConfig> netConfigMap = new ConcurrentHashMap<String, NetConfig>();
	/**
	 * 文件目录分隔符
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String SEPARATOR = File.separator;
	/**
	 * 配置文件目录
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String CATALOG = System.getProperty("user.dir");
	/**
	 * 配置文件夹
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String FOLDER = "config";
	/**
	 * 服务配置文件
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String SVR_CONFIG_PATH = "svrconfig.json";
	/**
	 * 网络配置文件
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String NET_CONFIG_PATH = "netconfig.json";

	static {
		loadNetConfig();
		loadSvrConfig();
	}

	/**
	 * 加载服务配置文件
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static void loadSvrConfig() {
		String path = CATALOG + SEPARATOR + FOLDER + SEPARATOR + SVR_CONFIG_PATH;
		String svrConfigStr = JsonFileUtil.getJsonToString(path);
		List<SvrConfig> svrConfigList = JSONObject.parseArray(svrConfigStr, SvrConfig.class);
		svrConfigList.forEach(svr -> {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Read {} Configuration [{}]", SVR_CONFIG_PATH, svr);
			}
			if (svrConfigMap.containsKey(svr.getId())) {
				throw new RuntimeException("Server Configuration " + svr.getId() + " Repeat");
			}
			svr.setNetConfig(getNetConfig(svr.getId()));
			svrConfigMap.put(svr.getId(), svr);
		});
	}

	/**
	 * 加载网络配置文件
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static void loadNetConfig() {
		String path = CATALOG + SEPARATOR + FOLDER + SEPARATOR + NET_CONFIG_PATH;
		String netConfigStr = JsonFileUtil.getJsonToString(path);
		List<NetConfig> netConfigList = JSONObject.parseArray(netConfigStr, NetConfig.class);
		netConfigList.forEach(net -> {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Read {} Configuration [{}]", NET_CONFIG_PATH, net);
			}
			if (svrConfigMap.containsKey(net.getId())) {
				throw new RuntimeException("Server Configuration " + net.getId() + " Repeat");
			}
			netConfigMap.put(net.getId(), net);
		});
	}

	/**
	 * 获取某个服务配置
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @param id 服务ID
	 * @return {@link SvrConfig}
	 */
	public static SvrConfig getSvrConfig(String id) {
		return svrConfigMap.get(id);
	}

	/**
	 * 获取服务配置
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @return {@link Map}
	 */
	public static Map<String, SvrConfig> getSvrConfig() {
		return svrConfigMap;
	}

	/**
	 * 获取某个网络配置
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @param id 网络ID
	 * @return {@link NetConfig}
	 */
	public static NetConfig getNetConfig(String id) {
		return netConfigMap.get(id);
	}

	/**
	 * 获取网络配置
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @return {@link Map}
	 */
	public static Map<String, NetConfig> getNetConfig() {
		return netConfigMap;
	}
}
