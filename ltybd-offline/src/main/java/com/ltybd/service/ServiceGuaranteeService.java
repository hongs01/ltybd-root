package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ServiceGuaranteeService.java
 *
 * describe:
 * 
 * 2017年10月17日 上午11:40:54 created By chenq version 0.1
 *
 * 2017年10月17日 上午11:40:54 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "ServiceGuaranteeService", description = "服务保障能")
public interface ServiceGuaranteeService {
	
	@ApiOperation(value = "客流详情查询")
	public List<Map<String,Object>> passengerFlowDetail(Map<String,Object> param);

}
