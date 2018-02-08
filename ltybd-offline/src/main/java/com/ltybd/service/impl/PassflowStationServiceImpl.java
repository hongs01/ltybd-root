package com.ltybd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.mapper.PassflowStationMapper;
import com.ltybd.service.PassflowStationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowStationServiceImpl.java
 *
 * describe:
 * 
 * 2017年10月17日 下午2:16:46 created By chenq version 0.1
 *
 * 2017年10月17日 下午2:16:46 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassflowStationServiceImpl", description = "站点分时客流查询接口实现类")
@Service
public class PassflowStationServiceImpl implements PassflowStationService {

	@Autowired
	private PassflowStationMapper PassflowStationDao;
	
	@ApiOperation(value = "站点天周月客流查询接口")
	@Override
	public List<Map<String, Object>> queryByDays(Map<String, Object> param) {
		return PassflowStationDao.queryByDays(param);
	}
	
	@ApiOperation(value = "站点分时客流(按小时)接口")
	@Override
	public List<Map<String, Object>> queryByHour(Map<String, Object> param) {
		return PassflowStationDao.queryByHour(param);
	}
}
