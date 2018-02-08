package com.ltybd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.mapper.PassflowLineMapper;
import com.ltybd.service.PassflowLineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowLineServiceImpl.java
 *
 * describe:
 * 
 * 2017年10月16日 下午8:15:46 created By chenq version 0.1
 *
 * 2017年10月16日 下午8:15:46 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassflowLineServiceImpl", description = "线路分时客流查询接口实现类")
@Service
public class PassflowLineServiceImpl implements PassflowLineService{
	@Autowired
	private PassflowLineMapper PassflowLineDao;
	
	@ApiOperation(value = "预测客流")
	@Override
	public List<Map<String, Object>> getPrePassengerFlow(Map<String, Object> param) {
		return PassflowLineDao.getPrePassengerFlow(param);
	}
	
	@ApiOperation(value = "历史客流")
	@Override
	public List<Map<String, Object>> getHistoryPassengerFlow(Map<String, Object> param) {
		return PassflowLineDao.getHistoryPassengerFlow(param);
	}
	
	@ApiOperation(value = "公司客流折线图")
	@Override
	public List<Map<String, Object>> companyPassengerFlowLineChart(Map<String, Object> param) {
		return PassflowLineDao.companyPassengerFlowLineChart(param);
	}
	
	@ApiOperation(value = "公司客流饼状图")
	@Override
	public List<Map<String, Object>> companyPassengerFlowPieChart(Map<String, Object> param) {
		return PassflowLineDao.companyPassengerFlowPieChart(param);
	}
	
	@ApiOperation(value = "线路客流折线图")
	@Override
	public List<Map<String, Object>> PassengerFlowlineChart(Map<String, Object> param) {
		return PassflowLineDao.PassengerFlowlineChart(param);
	}
	
	@ApiOperation(value = "线路客流饼状图")
	@Override
	public List<Map<String, Object>> PassengerFlowPieChart(Map<String, Object> param) {
		return PassflowLineDao.PassengerFlowPieChart(param);
	}

}
