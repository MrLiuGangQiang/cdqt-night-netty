package com.cdqt.netty.vess.proxy;

import com.cdqt.netty.vess.config.entity.FistTarget;

/**
 * 代理接口
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
public interface IFistProxy<T extends FistTarget> {
	/**
	 * 代理调用
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 * @param target 目标对象
	 * @return {@link Object}
	 * @throws Exception
	 */
	public Object call(T target) throws Exception;
}
