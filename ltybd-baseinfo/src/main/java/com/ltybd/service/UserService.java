package com.ltybd.service;

import com.ltybd.entity.User;
import com.ltybd.entity.UserBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value = "UserService", description = "用户接口")
public interface UserService {

	@ApiOperation(value = "根据用户id查询对象")
	public UserBean findById(User user);

}
