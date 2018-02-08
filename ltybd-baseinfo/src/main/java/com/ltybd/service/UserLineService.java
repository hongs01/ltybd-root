package com.ltybd.service;

import com.ltybd.entity.UserLine;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value="UserLineService", description = "用户 线路接口")
public interface UserLineService {

	@ApiOperation(value = "根据线路ID查询用户线路对象")
	public UserLine findByLineId(UserLine userLine) ;
	

}
