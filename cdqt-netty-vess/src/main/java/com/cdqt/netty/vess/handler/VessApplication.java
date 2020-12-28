package com.cdqt.netty.vess.handler;

import com.cdqt.netty.vess.boot.FistBoot;

/**
 * 容器主函数
 *
 * @author LiuGangQiang Create in 2020/12/27
 */
public class VessApplication {
	public static void main(String[] args) {
		new FistBoot().load().start();
	}
}
