package com.cdqt.netty.tool.json;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON文件工具类
 *
 * @author LiuGangQiang Create in 2020/12/20
 */
public class JsonFileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileUtil.class);

	/**
	 * 读取JSON文件并返回字符串
	 *
	 * @author LiuGangQiang Create in 2020/12/20
	 * @param path 文件路径
	 * @return {@link String}
	 */
	public static String getJsonToString(final String path) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Start Load Json File [{}]", path);
			}
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			Reader reader = new InputStreamReader(in, "utf-8");
			int ch = 0;
			StringBuffer sb = new StringBuffer();
			while ((ch = reader.read()) != -1) {
				sb.append((char) ch);
			}
			reader.close();
			return sb.toString();
		} catch (Exception e) {
			LOGGER.error("Load Json File Error [{}]", path);
		}
		return null;
	}
}
