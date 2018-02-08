package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowStationService.java
 *
 * describe:
 * 
 * 2017年10月17日 下午2:15:15 created By chenq version 0.1
 *
 * 2017年10月17日 下午2:15:15 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassflowStationService", description = "站点分时客流查询接口")
public interface PassflowStationService {
	
	@ApiOperation(value = "站点天周月客流查询接口")
	public List<Map<String,Object>> queryByDays(Map<String,Object> param);
	
	@ApiOperation(value = "站点(小时)客流查询接口")
	public List<Map<String,Object>> queryByHour(Map<String,Object> param);

}
