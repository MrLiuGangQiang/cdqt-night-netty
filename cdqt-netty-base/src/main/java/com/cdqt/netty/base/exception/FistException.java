package com.cdqt.netty.base.exception;

import java.text.MessageFormat;

/**
 * 自定义异常
 *
 * @author LiuGangQiang Create in 2020/10/21
 */
public class FistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2020/10/21
	 * @param message 异常消息
	 * @param args    不定参数
	 */
	public FistException(String message, Object... args) {
		super(MessageFormat.format(message, args));
	}

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2020/10/21
	 * @param message 异常消息
	 */
	public FistException(String message) {
		super(message);
	}

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2020/10/21
	 */
	public FistException() {
		super();
	}
}
