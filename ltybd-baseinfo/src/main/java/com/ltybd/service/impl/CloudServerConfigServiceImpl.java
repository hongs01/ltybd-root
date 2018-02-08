package com.ltybd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.CloudServerConfig;
import com.ltybd.mapper.CloudServerConfigMapper;
import com.ltybd.service.CloudServerConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CloudServerConfigServiceImpl", description = "平台接入接口实现类")
@Service
public class CloudServerConfigServiceImpl implements CloudServerConfigService {
	
	@Autowired
	private CloudServerConfigMapper cloudServerConfigDao;
	
	@ApiOperation(value = "查询平台接入对象")
	@Override
	public CloudServerConfig findPlatform(CloudServerConfig cloudServerConfig) {
		
		return cloudServerConfigDao.findPlatform(cloudServerConfig);
	}

	@ApiOperation(value="更新平台接入对象")
	@Override
	public int updatePlatform(CloudServerConfig cloudServerConfig) {
	
		return cloudServerConfigDao.updatePlatform(cloudServerConfig);
	}

	@ApiOperation(value="插入平台接入对象")
	@Override
	public int insert(CloudServerConfig cloudServerConfig) {

		return cloudServerConfigDao.insert(cloudServerConfig);
	}

	@ApiOperation(value="根据ID获得平台接入对象")
	@Override
	public Object findById(CloudServerConfig cloudServerConfig) {
		
		return cloudServerConfigDao.findById(cloudServerConfig);
	}

	@Override
	public Integer getSequence(String code) {
		
		return cloudServerConfigDao.getSequence(code);
	}

}
