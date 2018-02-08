package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.CompanyDepartment;
import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.RescueConvoys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyDepartmentService.java
 *
 * describe:公司部门对应信息接口
 * 
 * 2017年10月17日 下午7:00:23 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:00:23 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "RescueConvoysService", description = "施救车队管理对应信息接口")
public interface RescueConvoysService {

	@ApiOperation(value = "查询施救车队对应信息集合")
	public List<RescueConvoys> findRescueListObj(RescueConvoys rescueConvoys);

	@ApiOperation(value = "更新施救车队信息")
	public int updateRescue(RescueConvoys rescueConvoys);

	@ApiOperation(value = "根据ID查询施救车队信息")
	public RescueConvoys findRescueById(Integer rescue_id);

	@ApiOperation(value = "根据ID删除施救车队信息")
	public int deleteRescue(Integer rescue_id);

	@ApiOperation(value = "删除施救车队列表信息集合")
	public int deleteRescueList(String rescue_ids);

	@ApiOperation(value = "新增施救车队")
	public void addRescue(RescueConvoys rescue);

	@ApiOperation(value = "获取Sequence")
	public Integer getSequence(String code);

}
