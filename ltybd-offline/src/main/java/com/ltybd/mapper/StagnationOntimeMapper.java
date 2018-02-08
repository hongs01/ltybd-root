package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.StagnationOntime;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * StagnationOntimeMapper.java
 *
 * describe:
 * 
 * 2017年10月17日 下午3:45:20 created By chenq version 0.1
 *
 * 2017年10月17日 下午3:45:20 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "StagnationOntimeMapper", description = "准点率与滞站Mapper")
public interface StagnationOntimeMapper extends MyMapper<StagnationOntime>{
	
	@ApiOperation(value = "获取准点数据")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.punctualityrate as y_data "
			+ " from dm_stagnation_ontime_dm a "
			+ " where  a.routeId=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.lineDirection=#{direction} "
			+ " and a.timeType=#{step} "
			+ " <if test='station_id!=null '> "
			+ " and  a.opStationMainId=#{station_id} "
			+ " </if> "
			+ " order by x_data ASC "
			+ "</script>")
	public List<Map<String, Object>> getOntime(Map<String, Object> param);
	
	@ApiOperation(value = "获取滞站数据")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.stagnanttraffic as y_data "
			+ " from dm_stagnation_ontime_dm a "
			+ " where  a.routeId=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.lineDirection=#{direction} "
			+ " and a.timeType=#{step} "
			+ " <if test='station_id!=null '> "
			+ " and  a.opStationMainId=#{station_id} "
			+ " </if> "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> getStagnation(Map<String, Object> param);

}
