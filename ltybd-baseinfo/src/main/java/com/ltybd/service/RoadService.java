package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Road;
import com.ltybd.entity.Stationblock;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * RoadService.java
 *
 * describe:
 * 
 * 2017年11月9日 上午10:48:53 created By chenq version 0.1
 *
 * 2017年11月9日 上午10:48:53 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "RoadService", description = "道路信息接口")
public interface RoadService {

	@ApiOperation(value = "查询道路信息对象  ")
	public Road findByRoadId(Road road);

	@ApiOperation(value = "删除道路信息对象 ")
	public int deleteRoad(Road road);

	@ApiOperation(value = "更新道路信息对象")
	public int updateRoad(Road road);

	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String code);

	@ApiOperation(value = "增加道路信息对象")
	public int insertRoad(Road road);

	@ApiOperation(value = "批量删除道路信息对象")
	public int deleteRoadItems(String ids);

	@ApiOperation(value = "查询道路集合")
	public List<Road> findRoadList(Road road);

	@ApiOperation(value = "批量查询道路对象 ")
	public List<Road> batchfindListObj(String ids);

	@ApiOperation(value = "通过道路id查询站台信息 ")
	public List<Stationblock> findStationsByRoadId(Road road);

}
