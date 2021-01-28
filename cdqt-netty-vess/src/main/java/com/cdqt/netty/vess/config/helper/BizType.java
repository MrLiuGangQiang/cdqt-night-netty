package com.cdqt.netty.vess.config.helper;

/**
 * 业务类型枚举
 *
 * @author LiuGangQiang Create in 2020/12/27
 */
public enum BizType {
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
	 * LOCAL
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 */
	LOCAL("local"),
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
	 * BizType
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param type 服务类型
	 */
	private BizType(String type) {
		this.type = type;
	}

	/**
	 * 字符串转枚举 不匹配默认为未定义
	 *
	 * @author LiuGangQiang Create in 2020/12/27
	 * @param text 字符串
	 * @return {@link BizType}
	 */
	public static BizType fromString(String text) {
		for (BizType type : BizType.values()) {
			if (type.type.equalsIgnoreCase(text)) {
				return type;
			}
		}
		return UNDEFINED;
	}
}
