package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.VehicleTerminal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * VehicleTerminalService.java
 *
 * describe:车辆设备信息接口
 * 
 * 2017年10月13日 下午4:12:27 created By Yancz version 0.1
 *
 * 2017年10月13日 下午4:12:27 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehicleTerminalService", description = "车辆设备信息接口")
public interface VehicleTerminalService {

	@ApiOperation(value = "查询车辆设备信息集合")
	public List<VehicleTerminal> findListObj(VehicleTerminal vehicleTerminal);

	@ApiOperation(value = "刪除车辆设备信息对象")
	public int delete(VehicleTerminal vehicleTerminal);

	@ApiOperation(value = "更新车辆设备信息对象")
	public int updateObj(VehicleTerminal vehicleTerminal);
	
	@ApiOperation(value = "批量更新车辆设备信息对象")
	public int updateList(List<VehicleTerminal> list);

}
