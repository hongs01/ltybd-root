package com.ltybd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.mapper.PassSatMapper;
import com.ltybd.service.PassSatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassSatServiceImpl.java
 *
 * describe:
 * 
 * 2017年10月16日 下午6:49:40 created By chenq version 0.1
 *
 * 2017年10月16日 下午6:49:40 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassSatServiceImpl", description = "乘客满意度信息接口实现类")
@Service
public class PassSatServiceImpl implements PassSatService{

	@Autowired
	private PassSatMapper passSatDao;
	
	@ApiOperation(value = "乘客满意度信息")
	@Override
	public List<Map<String, Object>> getCustomerDegree(Map<String, Object> param) {
		return passSatDao.getCustomerDegree(param);
	}
	
	@ApiOperation(value = "候车满意度信息")
	@Override
	public List<Map<String, Object>> getWaitingSatisfaction(Map<String, Object> param) {
		return passSatDao.getWaitingSatisfaction(param);
	}
	
	@ApiOperation(value = "舒适满意度信息")
	@Override
	public List<Map<String, Object>> getComfortSatisfaction(Map<String, Object> param) {
		return passSatDao.getComfortSatisfaction(param);
	}

}
