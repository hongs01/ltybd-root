package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.PassflowLine;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowLineMapper.java
 *
 * describe:
 * 
 * 2017年10月17日 上午9:22:22 created By chenq version 0.1
 *
 * 2017年10月17日 上午9:22:22 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassflowLineMapper", description = "线路分时客流Mapper")
public interface PassflowLineMapper extends MyMapper<PassflowLine> {
	
	@ApiOperation(value = "预测客流")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.predictionPassengerNum AS y_data "
			+ " from "
			+ " dm_passflow_line_dm a "
			+ " where  a.lineid=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.direction=#{direction} "
			+ " and a.timeType=#{step} "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> getPrePassengerFlow(Map<String, Object> param);
	
	
	@ApiOperation(value = "历史客流")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.historyPassengerNum AS y_data "
			+ " from "
			+ " dm_passflow_line_dm a "
			+ " where  a.lineid=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.direction=#{direction} "
			+ " and a.timeType=#{step} "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> getHistoryPassengerFlow(Map<String, Object> param);
	
	@ApiOperation(value = "公司客流折线图")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.departmentid as son_name, "
			+ " sum(a.historyPassengerNum) AS y_data_passenger_flow "
			+ " from dm_passflow_line_dm a "
			+ " where  a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.timeType=#{step} "
			+ " group by departmentid,x_data "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> companyPassengerFlowLineChart(Map<String, Object> param);
	
	@ApiOperation(value = "公司客流饼状图")
	@Select("<script>"
			+ " select  "
			+ " a.departmentid AS son_name, "
			+ " sum(a.historyPassengerNum) as total_passenger_flow "
			+ " from dm_passflow_line_dm a "
			+ " where  a.citycode=#{city_code} "
			+ "  and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.timeType=#{step} "
			+ " group by departmentid; "
			+ "</script>")
	public List<Map<String, Object>> companyPassengerFlowPieChart(Map<String, Object> param);
	
	@ApiOperation(value = "线路客流折线图")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ "  a.lineid  as son_name, "
			+ " sum(historyPassengerNum) as y_data_passenger_flow "
			+ " from dm_passflow_line_dm a "
			+ " where  a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.departmentid=#{company_id} "
			+ " and a.timeType=#{step} "
			+ " group by lineid,x_data "
			+ " order by x_data ASC; "
			+ "</script>")
	public List<Map<String, Object>> PassengerFlowlineChart(Map<String, Object> param);
	
	@ApiOperation(value = "线路客流饼状图")
	@Select("<script>"
			+ " select  "
			+ " a.lineid as son_name, "
			+ " sum(historyPassengerNum) as passenger_flow "
			+ "  from dm_passflow_line_dm  a "
			+ " where  a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.timeType=#{step} "
			+ "  and a.departmentid=#{company_id} "
			+ " group by lineid; "
			+ "</script>")
	public List<Map<String, Object>> PassengerFlowPieChart(Map<String, Object> param);

}
