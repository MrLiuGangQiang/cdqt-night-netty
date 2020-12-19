package com.cdqt.netty.tool.code;

import java.util.Random;

/**
 * 验证码工具类
 * 
 * @author LiuGangQiang Create in 2020/03/01
 */
public class ValidCodeUtil {

	/**
	 * 纯数字验证码池
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private final static char[] numbers = "0123456789".toCharArray();

	/**
	 * 数字字母混合验证码池（去除数字"0" "1" 和字母"O" "o" "I" 防止混淆）
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private char[] words = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

	/**
	 * 验证码最小长度 值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private final static int MIN_LEN = 4;

	/**
	 * 验证码最大长度 值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private final static int MAX_LEN = 8;

	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private ValidCodeUtil() {
	}

	/**
	 * 内部实例化工具类
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 */
	private static class SingleValidCodeUtilHolder {
		/**
		 * {@link ValidCodeUtil} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/03/01
		 */
		private static final ValidCodeUtil INSTANCE = new ValidCodeUtil();
	}

	/**
	 * 获取{@link SingleValidCodeUtilHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @return {@link SingleValidCodeUtilHolder#INSTANCE} 静态常量实例
	 */
	public static ValidCodeUtil getInstance() {
		return SingleValidCodeUtilHolder.INSTANCE;
	}

	/**
	 * 生成纯数字验证码
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param len 验证码长度
	 * @return 纯数字验证码
	 */
	public String generateNumber(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = numbers[random.nextInt(numbers.length)];
		}
		return new String(cs);
	}

	/**
	 * 生成字母数字混合验证码
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param len 验证码长度
	 * @return 字母数字混合验证码
	 */
	public String generateCode(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = words[random.nextInt(words.length)];
		}
		return new String(cs);
	}

	/**
	 * 处理验证码长度
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param len 验证码长度
	 * @return 合法的验证码长度
	 *         在{@link ValidCodeUtil#MIN_LEN}和{@link ValidCodeUtil#MAX_LEN}之间
	 */
	private int limitLen(int len) {
		if (len < MIN_LEN) {
			return MIN_LEN;
		} else if (len > MAX_LEN) {
			return MAX_LEN;
		} else {
			return len;
		}
	}
}
