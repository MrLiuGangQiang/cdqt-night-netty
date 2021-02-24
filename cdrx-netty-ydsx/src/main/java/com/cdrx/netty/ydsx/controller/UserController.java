package com.cdrx.netty.ydsx.controller;

import java.util.List;
import java.util.Map;

import com.cdqt.netty.base.annotation.FistBody;
import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.result.FistResult;
import com.cdqt.netty.base.result.FistRows;
import com.cdqt.netty.base.result.FistStatus;
import com.cdrx.netty.ydsx.entity.User;
import com.cdrx.netty.ydsx.service.DplService;
import com.cdrx.netty.ydsx.service.UserService;

/**
 * UserController
 *
 * @author LiuGangQiang Create in 2021/02/24
 */
public class UserController {
	DplService dplService = new DplService();
	UserService userService = new UserService();

	@FistMapping(value = { "users" })
	public FistResult<?> getUserList(@FistBody User user) {
		dplService.getUserList(user);
		List<Map<String, Object>> users = userService.getUserList(user);
		return new FistResult<>(FistStatus.OK, new FistRows<>(users.size(), users));
	}

	@FistMapping(value = { "test" })
	public FistResult<?> test(User user) {
		return new FistResult<>(FistStatus.OK, user);
	}
	public static void main(String[] args) {
		User user=new User();
		UserService userService = new UserService();
		System.out.println(userService.getUserList(user));
		DplService dplService = new DplService();
		System.out.println(dplService.getUserList(user));
		DplService dplService1 = new DplService();
		System.out.println(dplService1.getUserList(user));
	}
}
