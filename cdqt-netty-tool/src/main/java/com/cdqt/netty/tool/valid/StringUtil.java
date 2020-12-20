package com.cdqt.netty.tool.valid;

/**
 * String工具类
 *
 * @author LiuGangQiang Create in 2020/03/07
 */
public class StringUtil {
	/**
	 * 空字符串
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 */
	public static final String EMPTY = "";
	/**
	 * 没找到的状态标识
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * 校验字符串是否为NULL或者长度等于0
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link Boolean} 是否为NULL或者长度等于0
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 校验字符串是否不为NULL且长度大于0
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link Boolean} 是否不为NULL且长度大于0
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtil.isEmpty(str);
	}

	/**
	 * 校验字符串是否为NULL或者是否为空字符串
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link Boolean} 是否为NULL或者是否为空字符串
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验字符串是否不为NULL且是否为空字符串
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link Boolean} 是否不为NULL且是否为空字符串
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * 去掉字符串首尾空格
	 * 
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link String} 去掉首尾空格的字符串
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 去掉字符串首尾空格若长度等于0返回NULL（永不出现 ""）
	 * 
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link String} 去掉字符串首尾空格的字符串
	 */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * 去掉字符串首尾空格若为NULL返回空字符串（永不出现NULL）
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str 字符串
	 * @return {@link String} 去掉字符串首尾空格的字符串
	 */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * 比较两个字符串是否有相等
	 * 
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, "abc")  = false
	 * StringUtils.equals("abc", null)  = false
	 * StringUtils.equals("abc", "abc") = true
	 * StringUtils.equals("abc", "ABC") = false
	 * </pre>
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return {@link Boolean} 是否相等
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * 比较两个字符串是否相等且忽略大小写
	 * 
	 * <pre>
	 * StringUtils.equalsIgnoreCase(null, null)   = true
	 * StringUtils.equalsIgnoreCase(null, "abc")  = false
	 * StringUtils.equalsIgnoreCase("abc", null)  = false
	 * StringUtils.equalsIgnoreCase("abc", "abc") = true
	 * StringUtils.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return {@link Boolean} 是否相等
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}
}
