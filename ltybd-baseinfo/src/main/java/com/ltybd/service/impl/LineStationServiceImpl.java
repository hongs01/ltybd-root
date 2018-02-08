package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.LineStation;
import com.ltybd.mapper.LineStationMapper;
import com.ltybd.service.LineStationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineStationServiceImpl.java
 *
 * describe:线路站点信息接口实现类
 * 
 * 2017年10月13日 上午10:50:13 created By Yancz version 0.1
 *
 * 2017年10月13日 上午10:50:13 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineStationServiceImpl", description = "线路站点信息接口实现类")
@Service
public class LineStationServiceImpl implements LineStationService {

	@Autowired
	private LineStationMapper lineStationDao;

	@ApiOperation(value = "查询线路站点信息集合")
	@Override
	public List<LineStation> findListObj(LineStation lineStation) {
		return lineStationDao.select(lineStation);
	}

	@ApiOperation(value = "更新线路站点信息对象")
	@Override
	public int updateObj(LineStation lineStation) {
		int result = 0;
		LineStation queryVO = new LineStation();
		queryVO.setLine_id(lineStation.getLine_id());
		queryVO.setBus_station_code(lineStation.getBus_station_code());
		queryVO.setDirection(lineStation.getDirection());
		List<LineStation> list = this.findListObj(queryVO);
		if (null != list && list.size() > 0) {
			result += this.update(lineStation);
		} else {
			result += this.insert(lineStation);
		}
		return result;
	}

	@ApiOperation(value = "刪除线路站点信息对象")
	@Override
	public int delete(LineStation lineStation) {
		return lineStationDao.delete(lineStation);
	}

	@ApiOperation(value = "插入线路站点信息对象")
	private int insert(LineStation lineStation) {
		if(null==lineStation.getStatus()){
			lineStation.setStatus(0);
		}
		lineStation.setCreate_time(new Date());
		lineStation.setLast_modified_time(new Date());
		return lineStationDao.insert(lineStation);
	}

	@ApiOperation(value = "修改线路站点信息对象")
	private int update(LineStation lineStation) {
		if(null==lineStation.getStatus()){
			lineStation.setStatus(0);
		}
		lineStation.setLast_modified_time(new Date());
		return lineStationDao.update(lineStation);
	}
	
	@ApiOperation(value = "批量线路站点信息对象")
	@Transactional
	@Override
	public int updateList(List<LineStation> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<LineStation> insertList = new ArrayList<LineStation>();
			List<LineStation> updateList = new ArrayList<LineStation>();
			for (LineStation lineStation : list) {
				LineStation queryVO = new LineStation();
				queryVO.setLine_id(lineStation.getLine_id());
				queryVO.setDirection(lineStation.getDirection());
				queryVO.setBus_station_code(lineStation.getBus_station_code());
				List<LineStation> queryList = this.findListObj(queryVO);
				if (null == lineStation.getStatus()) {
					lineStation.setStatus(0);
				}
				lineStation.setLast_modified_time(new Date());
				if (null != queryList && !queryList.isEmpty()) {
					updateList.add(lineStation);
				} else {
					lineStation.setCreate_time(new Date());
					insertList.add(lineStation);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += lineStationDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += lineStationDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "根据线路ID查询线路站点对象")
	@Override
	public List<LineStation> findByLineId(LineStation lineStation) {
		
		return lineStationDao.findByLineId(lineStation);
	}

}
