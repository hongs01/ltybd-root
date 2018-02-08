package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.VehiclePlan;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




/**
 * VehiclePlanMapper.java
 *
 * describe:
 * 
 * 2017年10月19日 下午3:06:34 created By chenq version 0.1
 *
 * 2017年10月19日 下午3:06:34 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehiclePlanMapper", description = "配车方案Mapper")
public interface VehiclePlanMapper extends MyMapper<VehiclePlan>{
	
	@ApiOperation(value = "上行的配车方案")
	@Select("<script>"
			+ " select  "
			+ " a.modelsType as vehiclemodel, "
			+ " a.numberOperatingVehicles as workingnumber, "
			+ " a.numberMotorVehicle  as backupnumber, "
			+ " a.mannedTotal as passengernumber, "
			+ " a.numberMotorVehicle as allvehicles "
			+ "  from dm_vehicle_plan_dm  a "
			+ " where  a.cityCode=#{cityCode} "
			+ " and a.lineId=#{lineId} "
			+ " and trim(a.calculatedDate)=#{curDate} "
			+ " and a.reverseType=#{reverseType} "
			+ " and a.Direction=0 "
			+ " order by a.calculatedDate desc;  "
			+ "</script>")
	public List<Map<String, Object>> getPlanvehiclearrageUpList(Map<String, Object> param);
	
	@ApiOperation(value = "下行的配车方案")
	@Select("<script>"
			+ " select  "
			+ " a.modelsType as vehiclemodel, "
			+ " a.numberOperatingVehicles as workingnumber, "
			+ " a.numberMotorVehicle  as backupnumber, "
			+ " a.mannedTotal as passengernumber, "
			+ " a.numberMotorVehicle as allvehicles "
			+ "  from dm_vehicle_plan_dm  a "
			+ " where  a.cityCode=#{cityCode} "
			+ " and a.lineId=#{lineId} "
			+ " and trim(a.calculatedDate)=#{curDate} "
			+ " and a.reverseType=#{reverseType} "
			+ " and a.Direction=1 "
			+ " order by a.calculatedDate desc;  "
			+ "</script>")
	public List<Map<String, Object>> getPlanVehicleArrageDownList(Map<String, Object> param);


}
