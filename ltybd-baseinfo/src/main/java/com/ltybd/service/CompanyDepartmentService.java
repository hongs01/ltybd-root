package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.CompanyDepartment;

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
@Api(value = "CompanyDepartmentService", description = "公司部门对应信息接口")
public interface CompanyDepartmentService {
	@ApiOperation(value = "查询公司部门对应信息集合")
	public List<CompanyDepartment> findListObj(CompanyDepartment companyDepartment);

	@ApiOperation(value = "刪除公司部门对应信息对象")
	public int delete(CompanyDepartment companyDepartment);
	
	@ApiOperation(value = "更新公司部门对应信息对象")
	public int updateObj(CompanyDepartment companyDepartment);

	@ApiOperation(value = "批量公司部门对应信息对象")
	public int updateList(List<CompanyDepartment> list);
}
