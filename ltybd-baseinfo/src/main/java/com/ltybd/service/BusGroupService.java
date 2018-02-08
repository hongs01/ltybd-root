package com.ltybd.service;

import com.ltybd.entity.BusGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="BusGroupService", description = "班组接口")
public interface BusGroupService {
	
	@ApiOperation(value="插入班组对象")
	public int insert(BusGroup busGroup);
	
	@ApiOperation(value="修改班组对象")
	public int update(BusGroup busGroup);
	

	@ApiOperation(value="查询班组对象")
	public BusGroup findById(Integer group_id);
}
