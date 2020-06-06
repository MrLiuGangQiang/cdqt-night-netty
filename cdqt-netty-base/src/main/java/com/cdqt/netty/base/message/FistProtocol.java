package com.cdqt.netty.base.message;

import java.io.Serializable;

import com.cdqt.netty.base.serial.FistSerializable;

/**
 * FistProtocol This Communication Protocol Contain Following Information<br>
 * <code> 
 * +-----------+-------------+------------------+<br>
 * |head(4byte)|length(4byte)|content(unlimited)|<br>
 * +-----------+-------------+------------------+<br>
 * </code>
 *
 * @author LiuGangQiang Create in 2020/06/06
 */
public class FistProtocol implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * fist communication protocol default head value default is {@value}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final int HEAD_DEFAULT = 0x133ED5B;
	/**
	 * fist communication protocol base data length value is {@value} include <br>
	 * <code>
	 * +--------------------+------------------------------+<br>
	 * |protocol head(4byte)|protocol content length(4byte)|<br>
	 * +--------------------+------------------------------+<br>
	 *</code>
	 * 
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static final int BASE_HEAD_LENGTH = 8;
	/**
	 * fist communication protocol head
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private int head;

	/**
	 * fist communication protocol content length
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private int length;

	/**
	 * fist communication protocol content byte[]
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private byte[] content;

	/**
	 * head default {@link #HEAD_DEFAULT}<br>
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param length  message length
	 * @param content message body
	 */
	public FistProtocol(int length, byte[] content) {
		this.head = HEAD_DEFAULT;
		this.length = length;
		this.content = content;
	}

	/**
	 * head default {@link #HEAD_DEFAULT}<br>
	 * 
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param obj entity
	 */
	public <T> FistProtocol(T obj) {
		this.head = HEAD_DEFAULT;
		byte[] buf = FistSerializable.getInstance().serialize(obj);
		this.length = buf.length;
		this.content = buf;
	}

	/**
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param head    message head currently only supported {@link #HEAD_DEFAULT}
	 * @param length  message length
	 * @param content message body
	 */
	public FistProtocol(int head, int length, byte[] content) {
		this.head = head;
		this.length = length;
		this.content = content;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * get fist protocol content to entity
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param clazz target class
	 * @return {@link T}
	 */
	public <T> T get(Class<T> clazz) {
		return FistSerializable.getInstance().deserialize(content, clazz);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("FistProtocol=[");
		sb.append("head=").append(this.head).append(",");
		sb.append("length=").append(this.length).append(",");
		sb.append("content=").append(this.content).append("]");
		return sb.toString();
	}
}
