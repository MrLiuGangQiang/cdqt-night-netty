package com.cdqt.netty.vess.tools.http.request;

/**
 * HTTP请求工具类
 *
 * @author LiuGangQiang Create in 2020/12/31
 */
public class FistHttpRequest {
	/**
	 * 私有化构造器
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private FistHttpRequest() {
	}

	/**
	 * 静态内部类
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 */
	private static class SingleFistHttpRequestHolder {
		/**
		 * {@link FistHttpRequest} 静态常量实例
		 *
		 * @author LiuGangQiang Create in 2020/12/29
		 */
		private static final FistHttpRequest INSTANCE = new FistHttpRequest();
	}

	/**
	 * 获取{@link SingleFistHttpRequestHolder#INSTANCE} 静态常量实例
	 *
	 * @author LiuGangQiang Create in 2020/12/29
	 * @return {@link SingleFistHttpRequestHolder#INSTANCE} 静态常量实例
	 */
	public static FistHttpRequest getInstance() {
		return SingleFistHttpRequestHolder.INSTANCE;
	}
}
