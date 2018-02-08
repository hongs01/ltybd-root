package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Road;
import com.ltybd.entity.Stationblock;
import com.ltybd.mapper.RoadMapper;
import com.ltybd.service.RoadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * RoadServiceImpl.java
 *
 * describe:
 * 
 * 2017年11月9日 上午10:59:29 created By chenq version 0.1
 *
 * 2017年11月9日 上午10:59:29 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="RoadServiceImpl", description = "道路接口实现类")
@Service
public class RoadServiceImpl implements RoadService {

	@Autowired
	private RoadMapper roadMapperDao;
	
	@ApiOperation(value = "查询道路信息对象  ")
	@Override
	public Road findByRoadId(Road road) {
		return roadMapperDao.findByRoadId(road);
	}

	@ApiOperation(value = "删除道路信息对象 ")
	@Override
	public int deleteRoad(Road road) {
		return roadMapperDao.delete(road);
	}

	@ApiOperation(value = "更新道路信息对象")
	@Override
	public int updateRoad(Road road) {
		return roadMapperDao.updateByPrimaryKeySelective(road);
	}

	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String code) {
		return roadMapperDao.getSequence(code);
	}

	@ApiOperation(value = "增加道路信息对象")
	@Override
	public int insertRoad(Road road) {
		return roadMapperDao.insert(road);
	}

	@ApiOperation(value = "批量删除道路信息对象")
	@Override
	public int deleteRoadItems(String ids) {
		return roadMapperDao.deleteRoadItems(ids);
	}

	@ApiOperation(value = "查询道路集合")
	@Override
	public List<Road> findRoadList(Road road) {
		return roadMapperDao.findRoadList(road);
	}

	@ApiOperation(value = "批量查询道路对象 ")
	@Override
	public List<Road> batchfindListObj(String ids) {
		return roadMapperDao.batchfindListObj(ids);
	}
	
	@ApiOperation(value = "通过道路id查询站台信息")
	@Override
	public List<Stationblock> findStationsByRoadId(Road road) {
		return roadMapperDao.findStationsByRoadId(road);
	}
}
