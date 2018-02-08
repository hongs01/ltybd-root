package com.ltybd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.mapper.ServiceGuaranteeMapper;
import com.ltybd.service.ServiceGuaranteeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ServiceGuaranteeServiceImpl.java
 *
 * describe:
 * 
 * 2017年10月17日 上午11:48:29 created By chenq version 0.1
 *
 * 2017年10月17日 上午11:48:29 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "ServiceGuaranteeServiceImpl", description = "服务保障能接口实现类")
@Service
public class ServiceGuaranteeServiceImpl implements ServiceGuaranteeService {
	
	@Autowired
	private ServiceGuaranteeMapper ServiceGuaranteeDao;
	@ApiOperation(value = "服务保障能客流详情")
	@Override
	public List<Map<String, Object>> passengerFlowDetail(Map<String, Object> param) {
		return ServiceGuaranteeDao.passengerFlowDetail(param);
	}

}
