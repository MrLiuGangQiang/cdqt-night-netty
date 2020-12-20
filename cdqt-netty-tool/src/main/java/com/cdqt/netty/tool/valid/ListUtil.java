package com.cdqt.netty.tool.valid;

import java.util.List;

/**
 * List工具类
 *
 * @author LiuGangQiang Create in 2020/03/07
 */
public class ListUtil {
	/**
	 * 校验List是否为Null或者无数据
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param list List对象
	 * @return {@link Boolean} 是否为Null或者无数据
	 */
	public static boolean isEmpty(final List<?> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * 校验List是否不为Null且存在数据
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param list List对象
	 * @return {@link Boolean} 是否不为Null且存在数据
	 */
	public static boolean isNotEmpty(final List<?> list) {
		return list != null && list.size() > 0;
	}

	/**
	 * 校验List是否不为空且元素数量大于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list   List对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isGt(final List<?> list, final int target) {
		return list != null && list.size() > target;
	}

	/**
	 * 校验List是否不为空且元素数量大于零
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list List对象
	 * @return {@link Boolean}
	 */
	public static boolean isGtZero(final List<?> list) {
		return isGt(list, 0);
	}

	/**
	 * 校验List是否不为空且元素数量大于等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list   List对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isGe(final List<?> list, final int target) {
		return list != null && list.size() >= target;
	}

	/**
	 * 校验List是否不为空且元素数量大于等于零
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list List对象
	 * @return {@link Boolean}
	 */
	public static boolean isGeZero(final List<?> list) {
		return isGe(list, 0);
	}

	/**
	 * 校验List是否不为空且元素数量小于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list   List对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isLt(final List<?> list, final int target) {
		return list != null && list.size() < target;
	}

	/**
	 * 校验List是否不为空且元素数量小于零
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list List对象
	 * @return {@link Boolean}
	 */
	public static boolean isLtZero(final List<?> list) {
		return isLt(list, 0);
	}

	/**
	 * 校验List是否不为空且元素数量小于等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list   List对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isLe(final List<?> list, final int target) {
		return list != null && list.size() <= target;
	}

	/**
	 * 校验List是否不为空且元素数量小于等于零
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list List对象
	 * @return {@link Boolean}
	 */
	public static boolean isLeZero(final List<?> list) {
		return isLe(list, 0);
	}

	/**
	 * 校验List是否不为空且元素数量等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list   List对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isEq(final List<?> list, final int target) {
		return list != null && list.size() == target;
	}

	/**
	 * 校验List是否不为空且元素数量等于零
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param list List对象
	 * @return {@link Boolean}
	 */
	public static boolean isEqZero(final List<?> list) {
		return isEq(list, 0);
	}
}
