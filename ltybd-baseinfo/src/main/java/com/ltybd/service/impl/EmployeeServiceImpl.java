package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ltybd.entity.Employee;
import com.ltybd.entity.EmployeeBean;
import com.ltybd.mapper.EmployeeMapper;
import com.ltybd.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="EmployeeServiceImpl", description = "员工信息接口实现类")
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeMapper employeeDao;
	
	@ApiOperation(value="查询员工信息集合")
	@Override
	public List<EmployeeBean> findListObj(Employee employee){
		return employeeDao.findListObj(employee);
	}
	
	@ApiOperation(value="查询员工信息对象")
	@Override
	public EmployeeBean findByEmployeeId(Employee employee){
		return employeeDao.findByEmployeeId(employee);
	}
	
	@ApiOperation(value="插入员工信息对象")
	@Override
	public int insert(Employee employee){
		return employeeDao.insert(employee);
	}
	
	@ApiOperation(value="修改员工信息对象")
	@Override
	public int update(Employee employee){
		return employeeDao.updateByPrimaryKeySelective(employee);
	}
	
	@ApiOperation(value="删除员工对象")
	@Override
	public int delete(Employee employee){
		return employeeDao.delete(employee);
	}
	
	@ApiOperation(value="批量删除员工信息对象")
	@Override
	public int deleteItems(String  ids){
		return employeeDao.deleteItems(ids);
	}
	
	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String  code){
		return employeeDao.getSequence(code);
	}
	
	@ApiOperation(value="批量更新员工信息")
	@Transactional
	@Override
	public int updateObjList(List<Employee> employees){
		int result = 0;
		if (null != employees && !employees.isEmpty()) {
			List<Employee> insertList = new ArrayList<Employee>();
			List<Employee> updateList = new ArrayList<Employee>();
			for (Employee employee : employees) {
				Employee queryVO = new Employee();
				EmployeeBean obj =null;
				if(!StringUtils.isEmpty(employee.getEmployee_id())){
					queryVO.setEmployee_id(employee.getEmployee_id());
					obj=this.findByEmployeeId(queryVO);
				}
				if(null == employee.getStatus()){
					employee.setStatus(0);
				}
				employee.setLast_modified_time(new Date());
				if (null != obj) {
					updateList.add(employee);
				} else {
					employee.setCreate_time(new Date());
					insertList.add(employee);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += employeeDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += employeeDao.updateList(updateList);
			}
		}
		return result;
	}
	
}
