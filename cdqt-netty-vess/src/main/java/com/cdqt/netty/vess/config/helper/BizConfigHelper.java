package com.cdqt.netty.vess.config.helper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cdqt.netty.tool.json.JsonFileUtil;
import com.cdqt.netty.vess.config.entity.BizConfig;
import com.cdqt.netty.vess.config.entity.SvrConfig;

/**
 * BizConfigHelper
 *
 * @author LiuGangQiang Create in 2020/12/20
 */
public class BizConfigHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(BizConfigHelper.class);
	/**
	 * 业务配置文件集合
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static Map<String, BizConfig> bizConfigMap = new ConcurrentHashMap<String, BizConfig>();
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
	 * 业务配置文件
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static final String BIZ_CONFIG_PATH = "bizconfig.json";

	static {
		loadBizConfig();
	}

	/**
	 * 加载业务配置文件
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 */
	private static void loadBizConfig() {
		String path = CATALOG + SEPARATOR + FOLDER + SEPARATOR + BIZ_CONFIG_PATH;
		String bizConfigStr = JsonFileUtil.getJsonToString(path);
		List<BizConfig> bizConfigList = JSONObject.parseArray(bizConfigStr, BizConfig.class);
		bizConfigList.forEach(biz -> {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Read {} Configuration [{}]", BIZ_CONFIG_PATH, biz);
			}
			if (bizConfigMap.containsKey(biz.getId())) {
				throw new RuntimeException("Server Configuration " + biz.getId() + " Repeat");
			}
			bizConfigMap.put(biz.getId(), biz);
		});
	}

	/**
	 * 获取某个业务配置
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @param id 业务ID
	 * @return {@link SvrConfig}
	 */
	public static BizConfig getBizConfig(String id) {
		return bizConfigMap.get(id);
	}

	/**
	 * 获取业务配置
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @return {@link Map}
	 */
	public static Map<String, BizConfig> getBizConfig() {
		return bizConfigMap;
	}
}
