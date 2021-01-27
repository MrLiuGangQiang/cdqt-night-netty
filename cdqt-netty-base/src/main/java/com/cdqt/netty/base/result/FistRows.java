package com.cdqt.netty.base.result;

import java.io.Serializable;
import java.util.List;

/**
 * 多行数据对象
 * 
 * @author LiuGangQiang Create in 2020/03/01
 */
public class FistRows<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private Long total;

	/**
	 * 数据结果集
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	private List<T> rows;

	/**
	 * 无参构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 */
	public FistRows() {
	}

	/**
	 * 构造器
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param total 总记录数
	 * @param rows  数据结果集
	 */
	public FistRows(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * 设置记录总数
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param total 记录总数
	 * @return {@link FistRows}
	 */
	public FistRows<T> setTotal(Long total) {
		this.total = total;
		return this;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 设置多行记录
	 *
	 * @author LiuGangQiang Create in 2020/03/01
	 * @param rows 多行记录
	 * @return {@link FistRows}
	 */
	public FistRows<T> setRows(List<T> rows) {
		this.rows = rows;
		return this;
	}
}
