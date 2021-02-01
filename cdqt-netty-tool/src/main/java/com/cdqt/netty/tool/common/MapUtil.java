package com.cdqt.netty.tool.common;

import java.util.Map;

/**
 * Map工具类
 *
 * @author LiuGangQiang Create in 2020/03/07
 */
public class MapUtil {
	/**
	 * 校验Map是否为Null或者无数据
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param map Map对象
	 * @return {@link Boolean} 是否为Null或者无数据
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	/**
	 * 校验Map是否不为Null且存在数据
	 *
	 * @author LiuGangQiang Create in 2020/03/07
	 * @param map Map对象
	 * @return {@link Boolean} 是否不为Null且存在数据
	 */
	public static boolean isNotEmpty(final Map<?, ?> map) {
		return map != null && map.size() > 0;
	}

	/**
	 * 校验Map是否不为空且元素数量大于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param map    Map对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isGt(final Map<?, ?> map, final int target) {
		return map != null && map.size() > target;
	}

	/**
	 * 校验Map是否不为空且元素数量大于等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param map    Map对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isGe(final Map<?, ?> map, final int target) {
		return map != null && map.size() >= target;
	}

	/**
	 * 校验Map是否不为空且元素数量小于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param map    Map对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isLt(final Map<?, ?> map, final int target) {
		return map != null && map.size() < target;
	}

	/**
	 * 校验Map是否不为空且元素数量小于等于某个数
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param map    Map对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isLe(final Map<?, ?> map, final int target) {
		return map != null && map.size() <= target;
	}

	/**
	 * 校验Map是否不为空且元素数量等于0
	 *
	 * @author LiuGangQiang Create in 2020/06/19
	 * @param map    Map对象
	 * @param target 目标数量
	 * @return {@link Boolean}
	 */
	public static boolean isEq(final Map<?, ?> map, final int target) {
		return map != null && map.size() == target;
	}
}
