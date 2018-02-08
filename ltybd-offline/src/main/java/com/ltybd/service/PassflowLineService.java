package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowLineService.java
 *
 * describe:
 * 
 * 2017年10月16日 下午8:14:49 created By chenq version 0.1
 *
 * 2017年10月16日 下午8:14:49 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassflowLineService", description = "线路分时客流查询接口")
public interface PassflowLineService {
	
	@ApiOperation(value = "预测客流")
	public List<Map<String,Object>> getPrePassengerFlow(Map<String,Object> param);
	
	
	@ApiOperation(value = "历史客流")
	public List<Map<String,Object>> getHistoryPassengerFlow(Map<String,Object> param);
	
	@ApiOperation(value = "公司客流折线图")
	public List<Map<String,Object>> companyPassengerFlowLineChart(Map<String,Object> param);
	
	@ApiOperation(value = "公司客流饼状图")
	public List<Map<String,Object>> companyPassengerFlowPieChart(Map<String,Object> param);
	
	@ApiOperation(value = "线路客流折线图")
	public List<Map<String,Object>> PassengerFlowlineChart(Map<String,Object> param);
	
	@ApiOperation(value = "线路客流饼状图")
	public List<Map<String,Object>> PassengerFlowPieChart(Map<String,Object> param);

}
