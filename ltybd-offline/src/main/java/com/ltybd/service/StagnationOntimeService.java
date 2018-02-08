package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * StagnationOntimeService.java
 *
 * describe:
 * 
 * 2017年10月16日 下午5:21:24 created By chenq version 0.1
 *
 * 2017年10月16日 下午5:21:24 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "StagnationOntimeService", description = "准点与滞站客流信息接口")
public interface StagnationOntimeService {
	
	@ApiOperation(value = "获取准点数据")
	public List<Map<String,Object>> getOntime(Map<String,Object> param);
	
	@ApiOperation(value = "获取滞站数据")
	public List<Map<String,Object>> getStagnation(Map<String,Object> param);

}
