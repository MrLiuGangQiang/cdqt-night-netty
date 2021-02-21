package com.cdqt.netty.data.pool;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.zaxxer.hikari.HikariDataSource;

/**
 * HikariCPDataSourceFactory
 *
 * @author LiuGangQiang Create in 2021/02/21
 */
public class HikariCPDataSourceFactory extends UnpooledDataSourceFactory {
	public HikariCPDataSourceFactory() {
		this.dataSource = new HikariDataSource();
	}
}