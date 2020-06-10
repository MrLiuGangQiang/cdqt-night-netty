package com.cdqt.netty.base.message;

import java.io.Serializable;
import java.util.Arrays;

import com.cdqt.netty.base.serial.FistUUID;

/**
 * FistMessage
 *
 * @author LiuGangQiang Create in 2020/06/06
 */
public class FistMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * fist message id plase use {@link FistUUID#generateShortUuid()}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private String serial;
	/**
	 * fist communication protocol message type plase use {@link FistMessageType}
	 * 
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private byte type;
	/**
	 * fist message content
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private byte[] content;

	/**
	 * constructor<br>
	 * serial default {@link FistUUID#generateShortUuid()}<br>
	 * type default {@link FistMessageType#REQUEST}<br>
	 * 
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public FistMessage(byte[] content) {
		this.content = content;
		this.serial = FistUUID.generateShortUuid();
		this.type = FistMessageType.REQUEST.getByte();
	}

	/**
	 * constructor<br>
	 * serial default {@link FistUUID#generateShortUuid()}<br>
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public FistMessage(FistMessageType type, byte[] content) {
		this.serial = FistUUID.generateShortUuid();
		this.type = type.getByte();
		this.content = content;
	}

	/**
	 * constructor<br>
	 * serial default {@link FistUUID#generateShortUuid()}<br>
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public FistMessage(String sign, FistMessageType type, byte[] content) {
		this.serial = FistUUID.generateShortUuid();
		this.type = type.getByte();
		this.content = content;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FistMessage [serial=" + serial + ", type=" + type + ", content=" + Arrays.toString(content) + "]";
	}

}
