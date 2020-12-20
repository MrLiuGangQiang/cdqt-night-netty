package com.cdqt.netty.tool.valid;

/**
 * 数组工具类
 *
 * @author LiuGangQiang Create in 2020/03/07
 */
public class ArrayUtil {
	/**
	 * 校验数组是否为Null或者无数据
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param arrays 数组对象
	 * @return {@link Boolean} 是否为Null或者无数据
	 */
	public static <T> boolean isEmpty(final T[] arrays) {
		return arrays == null || arrays.length == 0;
	}

	/**
	 * 校验数组是否不为Null且存在数据
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param arrays 数组对象
	 * @return {@link Boolean} 是否不为Null且存在数据
	 */
	public static <T> boolean isNotEmpty(final T[] arrays) {
		return arrays != null && arrays.length > 0;
	}

	/**
	 * 校验数组是否不为空且元素数量大于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param arrays 数组对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static <T> boolean isGt(final T[] arrays, final int target) {
		return arrays != null && arrays.length > target;
	}

	/**
	 * 校验数组是否不为空且元素数量大于等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param arrays 数组对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static <T> boolean isGe(final T[] arrays, final int target) {
		return arrays != null && arrays.length >= target;
	}

	/**
	 * 校验数组是否不为空且元素数量小于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param arrays 数组对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static <T> boolean isLt(final T[] arrays, final int target) {
		return arrays != null && arrays.length < target;
	}

	/**
	 * 校验数组是否不为空且元素数量小于等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param arrays 数组对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static <T> boolean isLe(final T[] arrays, final int target) {
		return arrays != null && arrays.length <= target;
	}

	/**
	 * 校验数组是否不为空且元素数量等于0
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param arrays 数组对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static <T> boolean isEq(final T[] arrays, final int target) {
		return arrays != null && arrays.length == target;
	}
}
