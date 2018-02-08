package com.ltybd.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.RescueConvoys;
import com.ltybd.mapper.RescueConvoysMapper;
import com.ltybd.service.RescueConvoysService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyDepartmentServiceImpl.java
 *
 * describe:公司部门对应信息接口实现类
 * 
 * 2017年10月17日 下午7:17:15 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:17:15 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "RescueConvoysServiceImpl", description = "车场对应信息接口实现类")
@Service
public class RescueConvoysServiceImpl implements RescueConvoysService {
	@Autowired
	private RescueConvoysMapper rescueMapper;

	@ApiOperation(value = "查询施救车队对应信息集合")
	@Override
	public List<RescueConvoys> findRescueListObj(RescueConvoys rescueConvoys) {
		return rescueMapper.findRescueListObj(rescueConvoys);
	}

	@ApiOperation(value = "更新施救车队信息")
	@Override
	public int updateRescue(RescueConvoys rescueConvoys) {
		return rescueMapper.updateRescue(rescueConvoys);
	}

	@ApiOperation(value = "根据ID查询施救车队信息")
	@Override
	public RescueConvoys findRescueById(Integer rescue_id) {
		return rescueMapper.selectByPrimaryKey(rescue_id);
	}

	@ApiOperation(value = "根据ID删除施救车队信息")
	@Override
	public int deleteRescue(Integer rescue_id) {
		return rescueMapper.deleteByPrimaryKey(rescue_id);
	}

	@ApiOperation(value = "删除施救车队列表信息集合")
	@Override
	public int deleteRescueList(String rescue_ids) {
		return rescueMapper.deleteRescueList(rescue_ids);
	}

	@ApiOperation(value = "新增施救车队")
	@Override
	public void addRescue(RescueConvoys rescue) {
		rescue.setCreate_date(new Date());
		rescue.setService_date(new Date());
		rescue.setStatus(0);
		rescue.setRescue_id(rescueMapper.getSequence("rescue_code"));
		rescueMapper.insertRescue(rescue);
	}

	@ApiOperation(value = "获取Sequence")
	@Override
	public Integer getSequence(String code) {
		return rescueMapper.getSequence(code);
	}
}
