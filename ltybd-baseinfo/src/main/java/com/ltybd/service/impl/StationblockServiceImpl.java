package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Line;
import com.ltybd.entity.Stationblock;
import com.ltybd.entity.StationblockBean;
import com.ltybd.mapper.StationblockMapper;
import com.ltybd.service.StationblockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * StationblockServiceImpl.java
 *
 * describe:
 * 
 * 2017年11月10日 下午6:13:19 created By chenq version 0.1
 *
 * 2017年11月10日 下午6:13:19 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="StationblockServiceImpl", description = "站台信息接口实现类")
@Service
public class StationblockServiceImpl implements StationblockService{
	
	@Autowired
	private StationblockMapper StationblockDao;
	
	@ApiOperation(value="查询站台信息集合")
	@Override
	public List<StationblockBean> findStationblockList(Stationblock Stationblock){
		return StationblockDao.findStationblockList(Stationblock);
	}
	
	@ApiOperation(value="查询站台信息对象")
	@Override
	public StationblockBean findByBusStationCode(Stationblock Stationblock){
		return StationblockDao.findByBusStationCode(Stationblock);
	}
	
	@ApiOperation(value="插入站台信息对象")
	@Override
	public int insertStationblock(Stationblock Stationblock){
		return StationblockDao.insert(Stationblock);
	}
	
	@ApiOperation(value="修改站台信息对象")
	@Override
	public int updateStationblock(Stationblock Stationblock){
		return StationblockDao.updateByPrimaryKeySelective(Stationblock);
	}
	
	@ApiOperation(value="删除站台对象")
	@Override
	public int delectStationblock(Stationblock Stationblock){
		return StationblockDao.delete(Stationblock);
	}
	
	@ApiOperation(value="批量删除站台信息对象")
	@Override
	public int deleteItems(String  ids){
		return StationblockDao.deleteItems(ids);
	}
	
	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String  code){
		return StationblockDao.getSequence(code);
	}
	
	@ApiOperation(value = "批量更新站台信息对象")
	@Transactional
	@Override
	public int updateList(List<Stationblock> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Stationblock> insertList = new ArrayList<Stationblock>();
			List<Stationblock> updateList = new ArrayList<Stationblock>();
			for (Stationblock Stationblock : list) {
				if (null == Stationblock.getStatus()) {
					Stationblock.setStatus(0);
				}
				Stationblock.setLast_modified_time(new Date());
				if (null != Stationblock.getBus_station_code()) {
					updateList.add(Stationblock);
				} else {
					Stationblock.setCreate_time(new Date());
					insertList.add(Stationblock);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += StationblockDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += StationblockDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "查询经过站台的线路 ")
	@Override
	public List<Line> findLinesByBusStationCode(Stationblock stationblock) {
		return StationblockDao.findLinesByBusStationCode(stationblock);
	}

	@ApiOperation(value = "批量查找站台信息")
	@Override
	public List<StationblockBean> batchfindListObj(String ids) {
		// TODO Auto-generated method stub
		return StationblockDao.batchfindListObj(ids);
	}
}
