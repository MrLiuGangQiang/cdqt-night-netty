package com.cdqt.netty.vess.tools.http.uri;

/**
 * HTTP请求URI处理工具
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistHttpUri {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistHttpUri() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistHttpUriHolder {
		/**
		 * {@link FistHttpUri} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistHttpUri INSTANCE = new FistHttpUri();
	}

	/**
	 * 获取{@link SingleFistHttpUriHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistHttpUriHolder#INSTANCE} 静态常量实例
	 */
	public static FistHttpUri getInstance() {
		return SingleFistHttpUriHolder.INSTANCE;
	}

}
