package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.PassflowStation;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowStationMapper.java
 *
 * describe:
 * 
 * 2017年10月17日 下午2:17:50 created By chenq version 0.1
 *
 * 2017年10月17日 下午2:17:50 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassflowStationMapper", description = "站点分时客流查询Mapper")
public interface PassflowStationMapper extends MyMapper<PassflowStation> {
	
	@ApiOperation(value = "站点天周月客流")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ "  a.passengerFlow as y_data_passenger_flow "
			+ " from dm_passflow_station_dm a "
			+ " where a.lineid=#{line_id} "
			+ " and  a.citycode=#{city_code} "
			+ " and  a.direction =#{direction} "
			+ " and  a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and  a.bus_station_code=#{station_id} "
			+ " and  a.timeType=#{step} "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> queryByDays(Map<String, Object> param);
	
	@ApiOperation(value = "站点分时客流(按小时)")
	@Select("<script>"
			+ " select "
			+ "  a.offtime as x_data, "
			+ "  a.stationName as station_name, "
			+ "  a.passengerFlow  as passenger_flow, "
			+ "  '' as capacity "
			+ " from dm_passflow_station_dm a "
			+ " where  a.lineid=#{line_id} "
			+ " and  a.citycode=#{city_code} "
			+ " and  a.direction =#{direction} "
			+ " and  a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and  a.timeType=1 "
			+ " order by offtime ASC; "
			+ "</script>")
	public List<Map<String, Object>> queryByHour(Map<String, Object> param);

}
