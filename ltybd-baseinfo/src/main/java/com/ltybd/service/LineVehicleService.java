package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.LineVehicle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * LineVehicleService.java
 *
 * describe:线路车辆信息接口
 * 
 * 2017年10月13日 下午2:27:04 created By Yancz version 0.1
 *
 * 2017年10月13日 下午2:27:04 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineVehicleService", description = "线路车辆信息接口")
public interface LineVehicleService {

	@ApiOperation(value = "查询线路车辆信息集合")
	public List<LineVehicle> findListObj(LineVehicle lineVehicle);

	@ApiOperation(value = "刪除线路车辆信息对象")
	public int delete(LineVehicle lineVehicle);

	@ApiOperation(value = "更新线路车辆信息对象")
	public int updateObj(LineVehicle lineVehicle);
	
	@ApiOperation(value = "批量线路车辆信息对象")
	public int updateList(List<LineVehicle> list);

	@ApiOperation(value = "根据线路ID查询车辆信息对象")
	public List<LineVehicle> findbyLineId(LineVehicle lineVehicle);

}
