package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.LineVehicle;
import com.ltybd.mapper.LineVehicleMapper;
import com.ltybd.service.LineVehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 
 * LineVehicleServiceImpl.java
 *
 * describe:线路车辆信息接口实现类
 * 
 * 2017年10月13日 下午2:29:01 created By Yancz version 0.1
 *
 * 2017年10月13日 下午2:29:01 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineVehicleServiceImpl", description = "线路车辆信息接口实现类")
@Service
public class LineVehicleServiceImpl implements LineVehicleService {

	@Autowired
	private LineVehicleMapper lineVehicleDao;

	@ApiOperation(value = "查询线路车辆信息集合")
	@Override
	public List<LineVehicle> findListObj(LineVehicle lineVehicle) {
		return lineVehicleDao.select(lineVehicle);
	}

	@ApiOperation(value = "更新线路车辆信息对象")
	@Override
	public int updateObj(LineVehicle lineVehicle) {
		int result = 0;
		LineVehicle queryVO = new LineVehicle();
		queryVO.setLine_id(lineVehicle.getLine_id());
		queryVO.setVehicle_id(lineVehicle.getVehicle_id());
		List<LineVehicle> list = this.findListObj(queryVO);
		if (null != list && list.size() > 0) {
			result += this.update(lineVehicle);
		} else {
			result += this.insert(lineVehicle);
		}
		return result;
	}

	@ApiOperation(value = "刪除线路车辆信息对象")
	@Override
	public int delete(LineVehicle lineVehicle) {
		return lineVehicleDao.delete(lineVehicle);
	}

	@ApiOperation(value = "插入线路车辆信息对象")
	private int insert(LineVehicle lineVehicle) {
		if(null==lineVehicle.getStatus()){
			lineVehicle.setStatus(0);
		}
		lineVehicle.setCreate_time(new Date());
		lineVehicle.setLast_modified_time(new Date());
		return lineVehicleDao.insert(lineVehicle);
	}

	@ApiOperation(value = "修改线路车辆信息对象")
	private int update(LineVehicle lineVehicle) {
		if(null==lineVehicle.getStatus()){
			lineVehicle.setStatus(0);
		}
		lineVehicle.setLast_modified_time(new Date());
		return lineVehicleDao.update(lineVehicle);
	}
	
	@ApiOperation(value = "批量更新线路车辆信息对象")
	@Transactional
	@Override
	public int updateList(List<LineVehicle> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<LineVehicle> insertList = new ArrayList<LineVehicle>();
			List<LineVehicle> updateList = new ArrayList<LineVehicle>();
			for (LineVehicle lineVehicle : list) {
				LineVehicle queryVO = new LineVehicle();
				queryVO.setLine_id(lineVehicle.getLine_id());
				queryVO.setVehicle_id(lineVehicle.getVehicle_id());
				List<LineVehicle> queryList = this.findListObj(queryVO);
				if (null == lineVehicle.getStatus()) {
					lineVehicle.setStatus(0);
				}
				lineVehicle.setLast_modified_time(new Date());
				if (null != queryList && !queryList.isEmpty()) {
					updateList.add(lineVehicle);
				} else {
					lineVehicle.setCreate_time(new Date());
					insertList.add(lineVehicle);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += lineVehicleDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += lineVehicleDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "根据线路ID查询车辆信息对象")
	@Override
	public List<LineVehicle> findbyLineId(LineVehicle lineVehicle) {
		
		return lineVehicleDao.findbyLineId(lineVehicle);
	}

}
