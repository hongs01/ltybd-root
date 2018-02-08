package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Vehicle;
import com.ltybd.entity.VehicleBean;
import com.ltybd.mapper.VehicleMapper;
import com.ltybd.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * VehicleServiceImpl.java
 *
 * describe:车辆信息接口实现类
 * 
 * 2017年10月13日 下午3:22:18 created By Yancz version 0.1
 *
 * 2017年10月13日 下午3:22:18 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehicleServiceImpl", description = "车辆信息接口实现类")
@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleMapper vehicleDao;

	@ApiOperation(value = "查询车辆信息集合")
	@Override
	public List<VehicleBean> findListObj(Vehicle vehicle) {
		return vehicleDao.findListObj(vehicle);

	}

	@ApiOperation(value = "查询车辆信息对象")
	@Override
	public VehicleBean findByVehicleId(Vehicle vehicle) {
		return vehicleDao.findByVehicleId(vehicle);
	}

	@ApiOperation(value = "插入车辆信息对象")
	@Override
	public int insert(Vehicle vehicle) {
		if(null==vehicle.getStatus()){
			vehicle.setStatus(0);
		}
		vehicle.setCreate_time(new Date());
		vehicle.setLast_modified_time(new Date());
		return vehicleDao.insert(vehicle);
	}

	@ApiOperation(value = "修改车辆信息对象")
	@Override
	public int update(Vehicle vehicle) {
		if(null==vehicle.getStatus()){
			vehicle.setStatus(0);
		}
		vehicle.setLast_modified_time(new Date());
		return vehicleDao.updateByPrimaryKeySelective(vehicle);
	}
	
	@ApiOperation(value = "批量修改车辆信息对象")
	@Transactional
	@Override
	public int updateList(List<Vehicle> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Vehicle> insertList = new ArrayList<Vehicle>();
			List<Vehicle> updateList = new ArrayList<Vehicle>();
			for (Vehicle vehicle : list) {
				if (null == vehicle.getStatus()) {
					vehicle.setStatus(0);
				}
				vehicle.setLast_modified_time(new Date());
				if (null != vehicle.getVechicle_id()) {
					updateList.add(vehicle);
				} else {
					vehicle.setCreate_time(new Date());
					insertList.add(vehicle);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += vehicleDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += vehicleDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "删除车辆信息对象")
	@Override
	public int delete(Vehicle vehicle) {
		return vehicleDao.delete(vehicle);
	}

	@ApiOperation(value = "批量删除车辆信息对象")
	@Override
	public int deleteItems(String ids) {
		return vehicleDao.deleteItems(ids);
	}

	@ApiOperation(value = "获取Sequence")
	@Override
	public int getSequence(String code) {
		return vehicleDao.getSequence(code);
	}

}
