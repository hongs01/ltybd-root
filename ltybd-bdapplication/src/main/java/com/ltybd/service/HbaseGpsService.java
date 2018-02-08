package com.ltybd.service;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;

@Api(value = "HbaseGpsService", description = "轨迹回放接口")
public interface HbaseGpsService {
	
	/**
	 * 根据rowKey查询gps轨迹数据
	 */
	public List findGpsList(Map<String,Object> params,String tableName);

}
