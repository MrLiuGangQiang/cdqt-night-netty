package com.cdrx.netty.ydsx.controller;

import java.util.List;
import java.util.Map;

import com.cdqt.netty.base.annotation.FistMapping;
import com.cdqt.netty.base.result.FistResult;
import com.cdqt.netty.base.result.FistRows;
import com.cdqt.netty.base.result.FistStatus;
import com.cdqt.netty.tool.common.ListUtil;
import com.cdrx.netty.ydsx.entity.User;
import com.cdrx.netty.ydsx.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * UserController
 *
 * @author LiuGangQiang Create in 2021/02/24
 */
public class UserController {
	private UserService userService = new UserService();

	@FistMapping("users")
	public FistResult<?> getUserList(User user) {
		Page<?> page = PageHelper.startPage(user.getPage(), user.getPageSize());
		List<Map<String, Object>> users = userService.getUserList(user);
		if (ListUtil.isEmpty(users)) {
			return new FistResult<>(FistStatus.DATA_NOT_FOUND);
		}
		return new FistResult<>(FistStatus.OK, new FistRows<>(page.getTotal(), users));
	}
}
