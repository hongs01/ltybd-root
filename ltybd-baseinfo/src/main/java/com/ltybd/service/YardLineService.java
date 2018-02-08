package com.ltybd.service;

import com.ltybd.entity.UserLine;
import com.ltybd.entity.Yard;
import com.ltybd.entity.YardLine;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value = "YardLineService", description = "车场线路接口")
public interface YardLineService {

	@ApiOperation(value = "根据线路ID查询用户线路对象")
	public YardLine findByLineId(YardLine yardLine) ;
	
}
