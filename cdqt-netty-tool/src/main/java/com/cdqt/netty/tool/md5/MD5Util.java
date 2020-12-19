package com.cdqt.netty.tool.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5工具类
 * 
 * @author LiuGangQiang Create in 2020/03/01
 */
public class MD5Util {

	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private MD5Util() {
	}

	/**
	 * 内部实例化工具类
	 *
	 * @author LiuGangQiang Create in 2020/03/02
	 */
	private static class SingleMD5UtilHolder {
		/**
		 * {@link MD5Util} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/03/01
		 */
		private static final MD5Util INSTANCE = new MD5Util();
	}

	/**
	 * 获取{@link SingleMD5UtilHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @return {@link SingleMD5UtilHolder#INSTANCE} 静态常量实例
	 */
	public static MD5Util getInstance() {
		return SingleMD5UtilHolder.INSTANCE;
	}

	/**
	 * MD5加密
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param text 原文
	 * @return 密文
	 * @throws NoSuchAlgorithmException @{@link NoSuchAlgorithmException}
	 */
	public String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("get md5 message digest error msg : " + e.toString());
		}
		char[] charArray = text.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * MD5随机加盐密文
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param text 原文
	 * @return 随机加盐密文
	 * @throws NoSuchAlgorithmException @{@link NoSuchAlgorithmException}
	 */
	public String encryptSalt(String text) throws NoSuchAlgorithmException {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		text = encrypt(text + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = text.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = text.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);
	}

	/**
	 * 比较原文和随机加盐密文
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param text   原文
	 * @param cipher 密文
	 * @return 是否相同
	 * @throws NoSuchAlgorithmException @{@link NoSuchAlgorithmException}
	 */
	public boolean compareSalt(String text, String cipher) throws NoSuchAlgorithmException {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = cipher.charAt(i);
			cs1[i / 3 * 2 + 1] = cipher.charAt(i + 2);
			cs2[i / 3] = cipher.charAt(i + 1);
		}
		String salt = new String(cs2);
		return encrypt(text + salt).equals(new String(cs1));
	}

	/**
	 * encryptBytes 获取数组MD5编码
	 *
	 * @author LiuGangQiang Create in 2020/01/27
	 * @param bytes 字节数组
	 * @return {@link String}
	 */
	public String encryptBytes(byte[] bytes) {
		StringBuffer md5 = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte[] mdbytes = md.digest();
			for (int i = 0; i < mdbytes.length; i++) {
				md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5.toString();
	}

}
