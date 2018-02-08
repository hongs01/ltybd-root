package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Department;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * DepartmentService.java
 *
 * describe:部门信息接口
 * 
 * 2017年10月17日 下午5:48:38 created By Yancz version 0.1
 *
 * 2017年10月17日 下午5:48:38 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "DepartmentService", description = "部门信息接口")
public interface DepartmentService {

	@ApiOperation(value = "查询部门信息集合")
	public List<Department> findListObj(Department department);

	@ApiOperation(value = "查询部门信息对象")
	public Department findByDepartmentId(Department department);

	@ApiOperation(value = "插入部门信息对象")
	public int insert(Department department);

	@ApiOperation(value = "修改部门信息对象")
	public int update(Department department);

	@ApiOperation(value = "删除部门信息对象")
	public int delete(Department department);

	@ApiOperation(value = "批量删除部门信息对象")
	public int deleteItems(String ids);

	@ApiOperation(value = "获取Sequence")
	public int getSequence(String code);
	
	@ApiOperation(value = "批量更新部门信息对象")
	public int updateList(List<Department> list);

}
