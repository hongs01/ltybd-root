package com.ltybd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.mapper.DrivingRulesMapper;
import com.ltybd.mapper.VehiclePlanMapper;
import com.ltybd.service.VehiclePlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * VehiclePlanServiceImpl.java
 *
 * describe:
 * 
 * 2017年10月19日 下午3:06:51 created By chenq version 0.1
 *
 * 2017年10月19日 下午3:06:51 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehiclePlanServiceImpl", description = "行车规则接口实现类")
@Service
public class VehiclePlanServiceImpl implements VehiclePlanService {

	@Autowired
	private VehiclePlanMapper VehiclePlanDao;
	
	@Autowired
	private DrivingRulesMapper DrivingRulesDao;
	
	
	@ApiOperation(value = "上行的配车方案")
	@Override
	public List<Map<String, Object>> getPlanvehiclearrageUpList(Map<String, Object> param) {
		return VehiclePlanDao.getPlanvehiclearrageUpList(param);
	}

	@ApiOperation(value = "下行的配车方案")
	@Override
	public List<Map<String, Object>> getPlanVehicleArrageDownList(Map<String, Object> param) {
		return VehiclePlanDao.getPlanVehicleArrageDownList(param);
	}

	@ApiOperation(value = "上行行车规则")
	@Override
	public List<Map<String, Object>> getTimeArrageUp(Map<String, Object> param) {
		return DrivingRulesDao.getTimeArrageUp(param);
	}

	@ApiOperation(value = "下行行车规则")
	@Override
	public List<Map<String, Object>> getTimeArrageDwon(Map<String, Object> param) {
		return DrivingRulesDao.getTimeArrageDwon(param);
	}

}
