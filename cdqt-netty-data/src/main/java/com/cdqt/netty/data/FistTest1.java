package com.cdqt.netty.data;

import java.util.HashMap;
import java.util.Map;

import com.cdqt.netty.base.annotation.FistMapping;

/**
 * 测试类
 *
 * @author LiuGangQiang Create in 2021/01/28
 */
@FistMapping({ "fist" })
public class FistTest1 {
	@FistMapping({ "test1" })
	public String test1(String name,Long time) {
		return "Hello Word";
	}

	@FistMapping({ "test2" })
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", 100);
		map.put("k3", true);
		return map;
	}
}
