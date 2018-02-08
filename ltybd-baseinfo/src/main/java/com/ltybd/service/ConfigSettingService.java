package com.ltybd.service;

import com.ltybd.entity.ConfigSetting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ConfigSettingService", description = "配置接口")
public interface ConfigSettingService {

	@ApiOperation(value="查询配置对象")
	public ConfigSetting findById(ConfigSetting configSetting);
	
	@ApiOperation(value="更新配置对象")
	public int update(ConfigSetting configSetting);
	
	@ApiOperation(value="更新配置对象")
	public int  insert(ConfigSetting configSetting);

	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String code);
}
