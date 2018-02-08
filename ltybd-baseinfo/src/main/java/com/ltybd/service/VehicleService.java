package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Vehicle;
import com.ltybd.entity.VehicleBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * VehicleService.java
 *
 * describe:车辆信息接口
 * 
 * 2017年10月13日 下午3:20:05 created By Yancz version 0.1
 *
 * 2017年10月13日 下午3:20:05 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehicleService", description = "车辆信息接口")
public interface VehicleService {

	@ApiOperation(value = "查询车辆信息集合")
	public List<VehicleBean> findListObj(Vehicle vehicle);

	@ApiOperation(value = "查询车辆信息对象")
	public VehicleBean findByVehicleId(Vehicle vehicle);

	@ApiOperation(value = "插入车辆信息对象")
	public int insert(Vehicle vehicle);

	@ApiOperation(value = "修改车辆信息对象")
	public int update(Vehicle vehicle);
	
	@ApiOperation(value = "批量修改车辆信息对象")
	public int updateList(List<Vehicle> list);

	@ApiOperation(value = "删除车辆信息对象")
	public int delete(Vehicle vehicle);

	@ApiOperation(value = "批量删除车辆信息对象")
	public int deleteItems(String ids);

	@ApiOperation(value = "获取Sequence")
	public int getSequence(String code);

}
