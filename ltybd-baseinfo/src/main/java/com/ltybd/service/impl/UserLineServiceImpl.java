package com.ltybd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.UserLine;
import com.ltybd.mapper.UserLineMapper;
import com.ltybd.service.UserLineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserLineService", description = "用户线路接口实现类")
@Service
public class UserLineServiceImpl implements UserLineService {

	@Autowired
	private UserLineMapper userLineDao;

	@ApiOperation(value = "根据线路ID查询用户线路对象")
	@Override
	public UserLine findByLineId(UserLine userLine) {
		if(null == userLine.getStatus()){
			userLine.setStatus(0);
		}
		return userLineDao.findByLineId(userLine);
	}
}
