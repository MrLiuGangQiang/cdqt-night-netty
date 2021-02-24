package com.cdqt.netty.base.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * FistBaseEntity 
 *
 * @author LiuGangQiang Create in 2020/06/06
 */
@SuppressWarnings("serial")
public class FistBaseEntity implements Serializable {

	/**
	 * 默认页数 默认值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	private static final int DEFAULT_PAGE = 1;
	/**
	 * 默认每页记录数 默认值为 {@value}
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	private static final int DEFAULT_PAGESIZE = 10;

	/**
	 * 当前页 默认值为 {@value #DEFAULT_PAGE}
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	transient Integer page;

	/**
	 * 每页记录数 默认值为 {@value #DEFAULT_PAGESIZE}
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	transient Integer pageSize;
	/**
	 * 排序集合 支持多字段组合排序
	 *
	 * @author LiuGangQiang Create in 2020/02/29
	 */
	transient List<Sort> sorts;

	/**
	 * @return the page
	 */
	@Transient
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		if (page != null && page > 0) {
			this.page = page;
		} else {
			this.page = DEFAULT_PAGE;
		}
	}

	/**
	 * @return the pageSize
	 */
	@Transient
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = DEFAULT_PAGESIZE;
		}
	}

	/**
	 * @return the sorts
	 */
	@Transient
	public List<Sort> getSorts() {
		return sorts;
	}

	/**
	 * @param sorts the sorts to set
	 */
	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}


}
