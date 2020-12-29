package com.cdqt.netty.vess.boot.register;

/**
 * 服务类型枚举
 *
 * @author LiuGangQiang Create in 2020/12/27
 */
public enum FistServerType {
	/**
	 * HTTP
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	HTTP("http"),
	/**
	 * TCP
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	TCP("tcp"),
	/**
	 * UDP
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	UDP("udp"),
	/**
	 * WEBSOCKET
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	WEBSOCKET("websocket"),
	/**
	 * THREAD
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	THREAD("thread"),
	/**
	 * UNDEFINED
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	UNDEFINED("undefined");

	private String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * FistServerType
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param type 服务类型
	 */
	private FistServerType(String type) {
		this.type = type;
	}

	/**
	 * 字符串转枚举 不匹配默认为未定义
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param text 字符串
	 * @return {@link FistServerType}
	 */
	public static FistServerType fromString(String text) {
		for (FistServerType type : FistServerType.values()) {
			if (type.type.equalsIgnoreCase(text)) {
				return type;
			}
		}
		return UNDEFINED;
	}
}
