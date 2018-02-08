package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.DrivingRules;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * DrivingRulesMapper.java
 *
 * describe:
 * 
 * 2017年10月19日 上午10:45:20 created By chenq version 0.1
 *
 * 2017年10月19日 上午10:45:20 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "DrivingRulesMapper", description = "行车规则Mapper")
public interface DrivingRulesMapper extends MyMapper<DrivingRules>{
	
	
	@ApiOperation(value = "上行行车规则")
	@Select("<script>"
			+ " select  "
			+ " a.starttime, "
			+ " a.endtime, "
			+ " a.departureInterval as ainterval, "
			+ " a.predictedSpeed as speed, "
			+ " a.operationDuration as worktimelength, "
			+ " a.parkingTime as resttime, "
			+ " a.smallestBus as minvehicles, "
			+ " a.lineSigns as mark, "
			+ " a.isInterDay  as spanday "
			+ " from dm_driving_rule_dm a "
			+ " where trim(a.calculatedDate)=#{curDate} "
			+ " and a.cityCode=#{cityCode} "
			+ " and a.lineId=#{lineId} "
			+ " and a.reverseType=#{reverseType} "
			+ " and a.Direction=0 "
			+ " order by a.starttime desc; "
			+ "</script>")
	public List<Map<String, Object>> getTimeArrageUp(Map<String, Object> param);
	
	@ApiOperation(value = "下行行车规则")
	@Select("<script>"
			+ " select  "
			+ " a.starttime, "
			+ " a.endtime, "
			+ " a.departureInterval as ainterval, "
			+ " a.predictedSpeed as speed, "
			+ " a.operationDuration as worktimelength, "
			+ " a.parkingTime as resttime, "
			+ " a.smallestBus as minvehicles, "
			+ " a.lineSigns as mark, "
			+ " a.isInterDay  as spanday "
			+ " from dm_driving_rule_dm a "
			+ " where trim(a.calculatedDate)=#{curDate} "
			+ " and a.cityCode=#{cityCode} "
			+ " and a.lineId=#{lineId} "
			+ " and a.reverseType=#{reverseType} "
			+ " and a.Direction=1 "
			+ " order by a.starttime desc; "
			+ "</script>")
	public List<Map<String, Object>> getTimeArrageDwon(Map<String, Object> param);


}
