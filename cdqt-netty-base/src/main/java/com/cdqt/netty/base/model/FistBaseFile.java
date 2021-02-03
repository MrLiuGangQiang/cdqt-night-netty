package com.cdqt.netty.base.model;

/**
 * 框架文件类
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
public class FistBaseFile {
	/**
	 * 文件全名
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String fullName;

	/**
	 * 文件名
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String name;
	/**
	 * 文件后缀
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String suffix;
	/**
	 * 文件大小
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private Long size;
	/**
	 * 文件类型
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private String type;
	/**
	 * 文件内容
	 *
	 * @author LiuGangQiang Create in 2021/01/28
	 */
	private byte[] content;

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "fullName=" + fullName + ", name=" + name + ", suffix=" + suffix + ", size=" + size + ", type=" + type;
	}

}
