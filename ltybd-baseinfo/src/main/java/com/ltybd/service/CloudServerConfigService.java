package com.ltybd.service;

import com.ltybd.entity.CloudServerConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="CloudServerConfigService", description = "平台接入接口")
public interface CloudServerConfigService {
	
	@ApiOperation(value="查询平台接入对象")
	public CloudServerConfig findPlatform(CloudServerConfig cloudServerConfig);
		
	@ApiOperation(value="更新平台接入对象")
	public int updatePlatform(CloudServerConfig cloudServerConfig);

	@ApiOperation(value="插入平台接入对象")
	public int insert(CloudServerConfig cloudServerConfig);

	@ApiOperation(value="根据ID获得平台接入对象")
	public Object findById(CloudServerConfig cloudServerConfig);

	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String code);
}
