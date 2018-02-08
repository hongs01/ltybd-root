package com.ltybd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.ConfigSetting;
import com.ltybd.mapper.ConfigSettingMapper;
import com.ltybd.service.ConfigSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CloudServerConfigServiceImpl", description = "配置接口实现类")
@Service
public class ConfigSettingServiceImpl implements ConfigSettingService {

	@Autowired
	private ConfigSettingMapper configSettingDao;
	
	@ApiOperation(value="查询平台接入对象")
	@Override
	public ConfigSetting findById(ConfigSetting configSetting) {

		return configSettingDao.findById(configSetting);
	}

	@ApiOperation(value="更新平台接入对象")
	@Override
	public int update(ConfigSetting configSetting) {
	
		return configSettingDao.updateByPrimaryKeySelective(configSetting);
	}

	@ApiOperation(value="插入平台接入对象")
	@Override
	public int insert(ConfigSetting configSetting) {

		return configSettingDao.insert(configSetting);
	}

	@Override
	public Integer getSequence(String code) {
		
		return configSettingDao.getSequence(code);
	}

}
