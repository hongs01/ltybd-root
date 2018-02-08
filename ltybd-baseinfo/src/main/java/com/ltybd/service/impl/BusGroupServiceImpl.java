package com.ltybd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.BusGroup;
import com.ltybd.mapper.BusGroupMapper;
import com.ltybd.service.BusGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="BusGroupServiceImpl", description = "班组接口实现类")
@Service
public class BusGroupServiceImpl implements BusGroupService {
	@Autowired
	private BusGroupMapper busGroupDao;
	
	@ApiOperation(value="插入班组对象")
	@Override
	public int insert(BusGroup busGroup){
		return busGroupDao.insert(busGroup);
	}
	
	@ApiOperation(value="修改班组对象")
	@Override
	public int update(BusGroup busGroup){
		return busGroupDao.updateByPrimaryKeySelective(busGroup);
	}
	
	@ApiOperation(value="查询班组对象")
	@Override
	public BusGroup findById(Integer group_id){
		return busGroupDao.findById(group_id);
	};
	
}
