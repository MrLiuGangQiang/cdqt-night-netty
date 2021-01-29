package com.cdqt.netty.data;

import com.cdqt.netty.base.annotation.FistMapping;

/**
 * 测试类
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
@FistMapping({ "fist" })
public class FistTest1 {
	@FistMapping({ "test1" })
	public void test1() {
		System.out.println("------------->1");
	}

	@FistMapping({ "test2" })
	public void test2() {
		System.out.println("------------->2");
	}

	@FistMapping({ "test3" })
	public void test3() {
		System.out.println("------------->3");
	}

	@FistMapping({ "test4" })
	public void test4() {
		System.out.println("------------->4");
	}
}
