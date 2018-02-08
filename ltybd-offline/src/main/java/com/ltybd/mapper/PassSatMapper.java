package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.PassSat;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassSatMapper.java
 *
 * describe:
 * 
 * 2017年10月16日 下午6:45:13 created By chenq version 0.1
 *
 * 2017年10月16日 下午6:45:13 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassSatMapper", description = "乘客满意度信息Mapper")
public interface PassSatMapper extends MyMapper<PassSat>{

	@ApiOperation(value = "乘客满意度信息")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.upNumber as up_passenger, "
			+ " '' as capacity, "
			+ " a.downNumber as down_passenger, "
			+ " a.averageStartInterval as average_start_interval, "
			+ " a.waitingSatisfaction as wait_satisfaction_rate, "
			+ " a.comfortSatisfaction as comfortable_satisfaction_rate, "
			+ " '' as enterprise_satisfaction_rate, "
			+ " '' as bycar_satisfaction_rate  "
			+ " from dm_pass_Sat_dm a "
			+ " where  a.lineid=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.timeType=#{step} "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> getCustomerDegree(Map<String, Object> param);
	
	@ApiOperation(value = "候车满意度信息")
	@Select("<script>"
			+ " select  "
			+ " a.offtime as x_data, "
			+ " a.waitingsatisfaction as y_data "
			+ " from dm_pass_Sat_dm a "
			+ " where  a.lineid=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.lineDirection=#{direction} "
			+ " and  a.timeType=1 "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> getWaitingSatisfaction(Map<String, Object> param);
	
	@ApiOperation(value = "舒适满意度信息")
	@Select("<script>"
			+ " select  "
			+ " a.offtime as x_data, "
			+ " a.comfortSatisfaction as y_data "
			+ " from dm_pass_Sat_dm a "
			+ " where  a.lineid=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.lineDirection=#{direction} "
			+ " and  a.timeType=1 "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> getComfortSatisfaction(Map<String, Object> param);
}
