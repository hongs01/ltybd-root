package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Employee;
import com.ltybd.entity.EmployeeBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="EmployeeService", description = "员工信息接口")
public interface EmployeeService {
	@ApiOperation(value="查询员工信息集合")
	public List<EmployeeBean> findListObj(Employee employee);
	
	@ApiOperation(value="查询员工信息对象")
	public EmployeeBean findByEmployeeId(Employee employee);
	
	@ApiOperation(value="插入员工信息对象")
	public int insert(Employee employee);
	
	@ApiOperation(value="更新员工信息对象")
	public int update(Employee employee);
	
	@ApiOperation(value="删除员工信息对象")
	public int delete(Employee employee);
	
	@ApiOperation(value="批量删除员工信息对象")
	public int deleteItems(String  ids);
	
	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String  code);
	
	@ApiOperation(value="批量更新员工信息对象")
	public int updateObjList(List<Employee> employees);
}
