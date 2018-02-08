package com.ltybd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.User;
import com.ltybd.entity.UserBean;
import com.ltybd.mapper.UserMapper;
import com.ltybd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value = "UserService", description = "用户接口实现类")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	@ApiOperation(value = "根据用户id查询对象")
	@Override
	public UserBean findById(User user) {
		
		return userDao.findById(user);
	}
}
