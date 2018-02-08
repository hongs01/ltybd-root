package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.District;
import com.ltybd.entity.Road;
import com.ltybd.mapper.DistrictMapper;
import com.ltybd.service.DistrictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DistrictServiceImpl.java
 *
 * describe:
 * 
 * 2017年11月8日 下午3:01:01 created By chenq version 0.1
 *
 * 2017年11月8日 下午3:01:01 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="DistrictServiceImpl", description = "区域接口实现类")
@Service
public class DistrictServiceImpl implements DistrictService{
	@Autowired
	private DistrictMapper districtMapperDao;
	
	@ApiOperation(value="查询区域信息对象")
	@Override
	public District findDistrictById(District district) {
		return districtMapperDao.findDistrictById(district);
	}

	@ApiOperation(value="删除区域对象")
	@Override
	public int deleteDistrict(District district) {
		return districtMapperDao.delete(district);
	}

	@ApiOperation(value="批量删除区域对象")
	@Override
	public int deleteDistrictItems(String ids) {
		return districtMapperDao.deleteDistrictItems(ids);
	}

	@Override
	public Integer getSequence(String code) {
		return districtMapperDao.getSequence(code);
	}

	@ApiOperation(value="增加区域对象")
	@Override
	public int insertDistrict(District district) {
		return districtMapperDao.insert(district);
		
	}

	@ApiOperation(value="修改区域对象")
	@Override
	public int updateDistrict(District district) {
		return districtMapperDao.updateByPrimaryKeySelective(district);
		
	}

	@ApiOperation(value="查询区域信息集合")
	@Override
	public List<District> findDistrictList(District district) {
		return districtMapperDao.findDistrictList(district);
	}

	@ApiOperation(value="批量查询区域对象")
	@Override
	public List<District> batchfindListObj(String ids) {
		return districtMapperDao.batchfindListObj(ids);
	}

	@ApiOperation(value="通过区域id查询道路信息")
	@Override
	public List<Road> findRoadsByDistrictId(District district) {
		return districtMapperDao.findRoadsByDistrictId(district);
	}

}
