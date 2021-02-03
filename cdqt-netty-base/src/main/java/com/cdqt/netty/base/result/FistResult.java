package com.cdqt.netty.base.result;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Locale;

/**
 * 请求响应通用对象
 * 
 * @author LiuGangQiang Create in 2020/03/01
 */
public class FistResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 默认资源文件路径
	 *
	 * @author LiuGangQiang Create in 2020/04/02
	 */
	private static final String DEFAULT_PATH = "i18n.core";

	/**
	 * 状态码
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private Integer code;

	/**
	 * 数据
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private T data;

	/**
	 * 提示消息
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private String msg;
	/**
	 * 语言环境
	 *
	 * @author LiuGangQiang Create in 2020/04/22
	 */
	transient private Locale locale;
	/**
	 * 资源文件路径
	 *
	 * @author LiuGangQiang Create in 2020/04/22
	 */
	transient private String path;
	/**
	 * 资源文件键值
	 *
	 * @author LiuGangQiang Create in 2020/04/22
	 */
	transient private String key;
	/**
	 * 参数数组
	 *
	 * @author LiuGangQiang Create in 2020/04/02
	 */
	transient private Object[] args;

	/**
	 * 是否使用默认资源
	 *
	 * @author LiuGangQiang Create in 2020/04/02
	 */
	transient private Boolean isUseDefault;
	/**
	 * 是否设置自定义消息
	 *
	 * @author LiuGangQiang Create in 2020/06/28
	 */
	transient private Boolean isUseMsg;

	/**
	 * 无参构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	public FistResult() {
	}

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param code 状态枚举
	 */
	public FistResult(FistStatus apiStatus) {
		this.isUseDefault = true;
		this.isUseMsg = false;
		this.code = apiStatus.getValue();
		this.key = apiStatus.getKey();
	}

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param code 状态枚举
	 * @param data 数据
	 */
	public FistResult(FistStatus apiStatus, T data) {
		this.isUseDefault = true;
		this.isUseMsg = false;
		this.data = data;
		this.code = apiStatus.getValue();
		this.key = apiStatus.getKey();
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setCode(Integer code) {
		this.code = code;
		return this;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setData(T data) {
		this.data = data;
		return this;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		if (this.isUseMsg) {
			return msg;
		} else {
			if (this.isUseDefault) {
				return FistPrompt.bundle(DEFAULT_PATH, getLocale(), this.key);
			} else {
				return FistPrompt.bundle(getPath(), getLocale(), this.key, this.args);
			}
		}
	}

	/**
	 * @param msg the msg to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setMsg(String msg) {
		this.isUseDefault = false;
		this.isUseMsg = true;
		this.msg = msg;
		return this;
	}

	/**
	 * @return the locale
	 */
	@Transient
	public Locale getLocale() {
		return locale == null ? Locale.getDefault() : locale;
	}

	/**
	 * @param locale the locale to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setLocale(Locale locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * @return the path
	 */
	@Transient
	public String getPath() {
		return path == null ? FistPrompt.getFilepath() : path;
	}

	/**
	 * @param path the path to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setPath(String path) {
		this.path = path;
		return this;
	}

	/**
	 * @param key the key to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setKey(String key) {
		this.isUseDefault = false;
		this.isUseMsg = false;
		this.key = key;
		return this;
	}

	/**
	 * @param key  the key to set
	 * @param args the args to set
	 * @return {@link FistResult}
	 */
	public FistResult<T> setKey(String key, Object... args) {
		this.isUseDefault = false;
		this.isUseMsg = false;
		this.args = args;
		this.key = key;
		return this;
	}

	/**
	 * @return the isUseMsg
	 */
	@Transient
	public Boolean getIsUseMsg() {
		return isUseMsg;
	}

	/**
	 * 比较两个状态码是否一致
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param code 状态枚举
	 * @return 是否一致
	 */
	public boolean compare(FistStatus code) {
		return getCode() == code.getValue();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[code=" + code + ", data=" + data + ", msg=" + msg + "]";
	}

}
