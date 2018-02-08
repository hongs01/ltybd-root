package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * VehiclePlanService.java
 *
 * describe:
 * 
 * 2017年10月19日 下午3:06:44 created By chenq version 0.1
 *
 * 2017年10月19日 下午3:06:44 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehiclePlanService", description = "行车规则接口")
public interface VehiclePlanService {
	
	@ApiOperation(value = "上行的配车方案 ")
	public List<Map<String,Object>> getPlanvehiclearrageUpList(Map<String,Object> param);
	
	@ApiOperation(value = "下行的配车方案 ")
	public List<Map<String,Object>> getPlanVehicleArrageDownList(Map<String,Object> param);
	
	@ApiOperation(value = "上行行车规则 ")
	public List<Map<String,Object>> getTimeArrageUp(Map<String,Object> param);
	
	@ApiOperation(value = "下行的配车方案 ")
	public List<Map<String,Object>> getTimeArrageDwon(Map<String,Object> param);

}
