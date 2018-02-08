package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Line;
import com.ltybd.entity.Stationblock;
import com.ltybd.entity.StationblockBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * StationblockService.java
 *
 * describe:
 * 
 * 2017年11月10日 下午6:12:50 created By chenq version 0.1
 *
 * 2017年11月10日 下午6:12:50 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="StationblockService", description = "站台信息接口")
public interface StationblockService {
	@ApiOperation(value="查询站台信息集合")
	public List<StationblockBean> findStationblockList(Stationblock Stationblock);
	
	@ApiOperation(value="查询站台信息对象")
	public StationblockBean findByBusStationCode(Stationblock Stationblock);
	
	@ApiOperation(value="插入站台信息对象")
	public int insertStationblock(Stationblock Stationblock);
	
	@ApiOperation(value="修改站台信息对象")
	public int updateStationblock(Stationblock Stationblock);
	
	@ApiOperation(value="删除站台信息对象")
	public int delectStationblock(Stationblock Stationblock);
	
	@ApiOperation(value="批量删除站台信息对象")
	public int deleteItems(String  ids);
	
	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String  code);
	
	@ApiOperation(value = "批量更新站台信息对象")
	public int updateList(List<Stationblock> list);

	@ApiOperation(value = "查询经过站台的线路 ")
	public List<Line> findLinesByBusStationCode(Stationblock stationblock);

	@ApiOperation(value = "批量查找站台信息")
	public List<StationblockBean> batchfindListObj(String ids);
}
