package com.cdqt.netty.tool.common;

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

	/**
	 * 判断字符串是否包含另一个字符串并忽略大小写
	 *
	 * @author LiuGangQiang Create in 2021/01/27
	 * @param str       源字符串
	 * @param searchStr 目标字符串
	 * @return {@link Boolean}
	 */
	public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		final int len = searchStr.length();
		final int max = str.length() - len;
		for (int i = 0; i <= max; i++) {
			if (regionMatches(str, true, i, searchStr, 0, len)) {
				return true;
			}
		}
		return false;
	}

	private static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart, final CharSequence substring, final int start, final int length) {
		if (cs instanceof String && substring instanceof String) {
			return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
		}
		int index1 = thisStart;
		int index2 = start;
		int tmpLen = length;

		// Extract these first so we detect NPEs the same as the java.lang.String version
		final int srcLen = cs.length() - thisStart;
		final int otherLen = substring.length() - start;

		// Check for invalid parameters
		if (thisStart < 0 || start < 0 || length < 0) {
			return false;
		}

		// Check that the regions are long enough
		if (srcLen < length || otherLen < length) {
			return false;
		}

		while (tmpLen-- > 0) {
			final char c1 = cs.charAt(index1++);
			final char c2 = substring.charAt(index2++);

			if (c1 == c2) {
				continue;
			}

			if (!ignoreCase) {
				return false;
			}

			// The real same check as in String.regionMatches():
			final char u1 = Character.toUpperCase(c1);
			final char u2 = Character.toUpperCase(c2);
			if (u1 != u2 && Character.toLowerCase(u1) != Character.toLowerCase(u2)) {
				return false;
			}
		}

		return true;
	}
}
