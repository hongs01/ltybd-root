package com.ltybd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.mapper.StagnationOntimeMapper;
import com.ltybd.service.StagnationOntimeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * StagnationOntimeServiceImpl.java
 *
 * describe:
 * 
 * 2017年10月16日 下午6:49:47 created By chenq version 0.1
 *
 * 2017年10月16日 下午6:49:47 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "StagnationOntimeServiceImpl", description = "查询分时准点率接口实现类")
@Service
public class StagnationOntimeServiceImpl implements StagnationOntimeService {
	
	@Autowired
	private StagnationOntimeMapper stagnationOntimeDao;
	
	@ApiOperation(value = "获取准点数据")
	@Override
	public List<Map<String, Object>> getOntime(Map<String, Object> param) {
		return stagnationOntimeDao.getOntime(param);
	}

	@ApiOperation(value = "获取滞站数据")
	@Override
	public List<Map<String, Object>> getStagnation(Map<String, Object> param) {
		return stagnationOntimeDao.getStagnation(param);
	}

}
