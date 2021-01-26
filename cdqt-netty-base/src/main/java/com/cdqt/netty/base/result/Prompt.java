package com.cdqt.netty.base.result;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 国际化资源文件操作类
 * 
 * @author LiuGangQiang Create in 2020/03/01
 */
public abstract class Prompt {
	private static final Logger LOGGER = LoggerFactory.getLogger(Prompt.class);
	/**
	 * 默认资源文件路径 默认值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private final static String FILEPATH = "i18n.message";

	/**
	 * 构造器私有化
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private Prompt() {
	}

	/**
	 * 获取无占位符的属性值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param key 键值
	 * @return {@link String} @
	 */
	public static String bundle(String key) {
		return getMessage(Locale.getDefault(), FILEPATH, key);
	}

	/**
	 * 获取无占位符的属性值
	 *
	 * @author LiuGangQiang Create in 2020/03/30
	 * @param locale 语言环境
	 * @param key    键值
	 * @return {@link String} @
	 */
	public static String bundle(Locale locale, String key) {
		return getMessage(locale, FILEPATH, key);
	}

	/**
	 * 获取有占位符的属性值并赋值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param key       键值
	 * @param arguments 参数（数组或不定参数）
	 * @return {@link String} @
	 */
	public static String bundle(String key, Object... arguments) {
		return getMessage(Locale.getDefault(), FILEPATH, key, arguments);
	}

	/**
	 * 获取有占位符的属性值并赋值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param locale    语言环境
	 * @param key       键值
	 * @param arguments 参数（数组或不定参数）
	 * @return {@link String} @
	 */
	public static String bundle(Locale locale, String key, Object... arguments) {
		return getMessage(locale, FILEPATH, key, arguments);
	}

	/**
	 * 获取无占位符的属性值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param path 文件路径
	 * @param key  键值
	 * @return {@link String} @
	 */
	public static String bundle(String path, String key) {
		return getMessage(Locale.getDefault(), path, key);
	}

	/**
	 * 获取无占位符的属性值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param locale 语言环境
	 * @param path   文件路径
	 * @param key    键值
	 * @return {@link String} @
	 */
	public static String bundle(String path, Locale locale, String key) {
		return getMessage(locale, path, key);
	}

	/**
	 * 获取有占位符的属性值并赋值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param path      文件路径
	 * @param key       键值
	 * @param arguments 参数（数组或不定参数）
	 * @return {@link String} @
	 */
	public static String bundle(String path, String key, Object... arguments) {
		return getMessage(Locale.getDefault(), path, key, arguments);
	}

	/**
	 * 获取有占位符的属性值并赋值
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param path      文件路径
	 * @param key       键值
	 * @param locale    语言环境
	 * @param arguments 参数（数组或不定参数）
	 * @return {@link String} @
	 */
	public static String bundle(String path, Locale locale, String key, Object... arguments) {
		return getMessage(locale, path, key, arguments);
	}

	/**
	 * 获取消息
	 *
	 * @author LiuGangQiang Create in 2020/04/02
	 * @param locale    语音环境
	 * @param path      文件路径
	 * @param key       键值
	 * @param arguments 参数
	 * @return {@link String}
	 */
	private static String getMessage(Locale locale, String path, String key, Object... arguments) {
		ResourceBundle bundle = ResourceBundle.getBundle(path, locale);
		try {
			String value = new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
			if (arguments != null && arguments.length > 0) {
				return MessageFormat.format(value, arguments);
			} else {
				return value;
			}
		} catch (UnsupportedEncodingException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Resource Bundle Get Bundle Message Error path={} locale={} key={} args={}", path, locale, key, arguments);
			}
		}
		return null;
	}

	/**
	 * @return the filepath
	 */
	protected static String getFilepath() {
		return FILEPATH;
	}

}
