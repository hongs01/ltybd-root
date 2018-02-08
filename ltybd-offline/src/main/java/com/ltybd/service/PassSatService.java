package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * PassSatService.java
 *
 * describe:
 * 
 * 2017年10月16日 下午8:14:49 created By chenq version 0.1
 *
 * 2017年10月16日 下午8:14:49 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "PassSatService", description = "乘客满意度")
public interface PassSatService {
	
	@ApiOperation(value = "乘客满意度查询")
	public List<Map<String,Object>> getCustomerDegree(Map<String,Object> param);
	
	@ApiOperation(value = "侯车满意度查询")
	public List<Map<String,Object>> getWaitingSatisfaction(Map<String,Object> param);
	
	@ApiOperation(value = "舒适满意度查询")
	public List<Map<String,Object>> getComfortSatisfaction(Map<String,Object> param);

}
