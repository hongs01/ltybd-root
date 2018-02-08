package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.VehicleTerminal;
import com.ltybd.mapper.VehicleTerminalMapper;
import com.ltybd.service.VehicleTerminalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * VehicleTerminalServiceImpl.java
 *
 * describe:车辆设备信息接口实现类
 * 
 * 2017年10月13日 下午4:16:33 created By Yancz version 0.1
 *
 * 2017年10月13日 下午4:16:33 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehicleTerminalServiceImpl", description = "车辆设备信息接口实现类")
@Service
public class VehicleTerminalServiceImpl implements VehicleTerminalService {

	@Autowired
	private VehicleTerminalMapper vehicleTerminalDao;

	@ApiOperation(value = "查询车辆设备信息集合")
	@Override
	public List<VehicleTerminal> findListObj(VehicleTerminal vehicleTerminal) {
		return vehicleTerminalDao.select(vehicleTerminal);
	}

	@ApiOperation(value = "更新车辆设备信息对象")
	@Override
	public int updateObj(VehicleTerminal vehicleTerminal) {
		int result = 0;
		VehicleTerminal queryVO = new VehicleTerminal();
		queryVO.setTerminal_id(vehicleTerminal.getTerminal_id());
		queryVO.setVehicle_id(vehicleTerminal.getVehicle_id());
		List<VehicleTerminal> list = this.findListObj(queryVO);
		if (null != list && !list.isEmpty()) {
			result += this.update(vehicleTerminal);
		} else {
			result += this.insert(vehicleTerminal);
		}
		return result;
	}

	@ApiOperation(value = "批量更新车辆设备信息对象")
	@Transactional
	@Override
	public int updateList(List<VehicleTerminal> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<VehicleTerminal> insertList = new ArrayList<VehicleTerminal>();
			List<VehicleTerminal> updateList = new ArrayList<VehicleTerminal>();
			for (VehicleTerminal vehicleTerminal : list) {
				VehicleTerminal queryVO = new VehicleTerminal();
				queryVO.setTerminal_id(vehicleTerminal.getTerminal_id());
				queryVO.setVehicle_id(vehicleTerminal.getVehicle_id());
				List<VehicleTerminal> queryList = this.findListObj(queryVO);
				if (null == vehicleTerminal.getStatus()) {
					vehicleTerminal.setStatus(0);
				}
				vehicleTerminal.setLast_modified_time(new Date());
				if (null != queryList && !queryList.isEmpty()) {
					updateList.add(vehicleTerminal);
				} else {
					vehicleTerminal.setCreate_time(new Date());
					insertList.add(vehicleTerminal);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += vehicleTerminalDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += vehicleTerminalDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "刪除车辆设备信息对象")
	@Override
	public int delete(VehicleTerminal vehicleTerminal) {
		return vehicleTerminalDao.delete(vehicleTerminal);
	}

	@ApiOperation(value = "插入车辆设备信息对象")
	private int insert(VehicleTerminal vehicleTerminal) {
		if (null == vehicleTerminal.getStatus()) {
			vehicleTerminal.setStatus(0);
		}
		vehicleTerminal.setCreate_time(new Date());
		vehicleTerminal.setLast_modified_time(new Date());
		return vehicleTerminalDao.insert(vehicleTerminal);
	}

	@ApiOperation(value = "修改车辆设备信息对象")
	private int update(VehicleTerminal vehicleTerminal) {
		if (null == vehicleTerminal.getStatus()) {
			vehicleTerminal.setStatus(0);
		}
		vehicleTerminal.setLast_modified_time(new Date());
		return vehicleTerminalDao.update(vehicleTerminal);
	}

}
