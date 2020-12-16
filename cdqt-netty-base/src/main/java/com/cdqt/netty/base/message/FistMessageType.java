package com.cdqt.netty.base.message;

/**
 * FistMessageType
 *
 * @author LiuGangQiang Create in 2020/06/06
 */
public enum FistMessageType {
	/**
	 * fist communication protocol obligate message value is {@link #DEFAULT_TYPE_OBLIGATE}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	OBLIGATE(FistMessageType.DEFAULT_TYPE_OBLIGATE),
	/**
	 * fist communication protocol request message value is {@link #DEFAULT_TYPE_REQUEST}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	REQUEST(FistMessageType.DEFAULT_TYPE_REQUEST),
	/**
	 * fist communication protocol answer message value is {@link #DEFAULT_TYPE_ANSWER}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	ANSWER(FistMessageType.DEFAULT_TYPE_ANSWER),
	/**
	 * fist communication protocol heartbeat message value is {@link #DEFAULT_TYPE_HEARTBEAT}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	HEARTBEAT(FistMessageType.DEFAULT_TYPE_HEARTBEAT);

	/**
	 * fist communication protocol obligate message type value is {@value}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final byte DEFAULT_TYPE_OBLIGATE = 0x0;
	/**
	 * fist communication protocol request message type value is {@value}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final byte DEFAULT_TYPE_REQUEST = 0x1;
	/**
	 * fist communication protocol answer message type value is {@value}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final byte DEFAULT_TYPE_ANSWER = 0x2;
	/**
	 * fist communication protocol heartbeat message type value is {@value}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final byte DEFAULT_TYPE_HEARTBEAT = 0x3;
	/**
	 * fist communication protocol message type
	 *
	 * @author LiuGangQiang Create in 2020/12/16
	 */
	private byte type;

	/**
	 * FistMessageType
	 *
	 * @author LiuGangQiang Create in 2020/12/16
	 * @param type message type
	 */
	private FistMessageType(byte type) {
		this.type = type;
	}

	/**
	 * getByte
	 *
	 * @author LiuGangQiang Create in 2020/12/16
	 * @return {@link Byte}
	 */
	public byte getByte() {
		return type;
	}
}
