package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Department;
import com.ltybd.mapper.DepartmentMapper;
import com.ltybd.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * DepartmentServiceImpl.java
 *
 * describe:部门信息接口实现类
 * 
 * 2017年10月17日 下午5:49:52 created By Yancz version 0.1
 *
 * 2017年10月17日 下午5:49:52 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "DepartmentServiceImpl", description = "部门信息接口实现类")
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentDao;

	@ApiOperation(value = "查询部门信息集合")
	@Override
	public List<Department> findListObj(Department department) {
			return departmentDao.findListObj(department);
	}

	@ApiOperation(value = "查询部门信息对象")
	@Override
	public Department findByDepartmentId(Department department) {
		return departmentDao.selectByPrimaryKey(department);
	}

	@ApiOperation(value = "插入部门信息对象")
	@Override
	public int insert(Department department) {
		if(null==department.getStatus()){
			department.setStatus(0);
		}
		department.setCreate_time(new Date());
		department.setLast_modified_time(new Date());
		return departmentDao.insert(department);
	}

	@ApiOperation(value = "修改部门信息对象")
	@Override
	public int update(Department department) {
		if(null==department.getStatus()){
			department.setStatus(0);
		}
		department.setLast_modified_time(new Date());
		return departmentDao.updateByPrimaryKeySelective(department);
	}

	@ApiOperation(value = "删除部门信息对象")
	@Override
	public int delete(Department department) {
		return departmentDao.delete(department);
	}

	@ApiOperation(value = "批量删除部门信息对象")
	@Override
	public int deleteItems(String ids) {
		return departmentDao.deleteItems(ids);
	}

	@ApiOperation(value = "获取Sequence")
	@Override
	public int getSequence(String code) {
		return departmentDao.getSequence(code);
	}
	
	@ApiOperation(value = "批量更新部门信息对象")
	@Transactional
	@Override
	public int updateList(List<Department> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Department> insertList = new ArrayList<Department>();
			List<Department> updateList = new ArrayList<Department>();
			for (Department department : list) {
				if (null == department.getStatus()) {
					department.setStatus(0);
				}
				department.setLast_modified_time(new Date());
				if (null != department.getDepartment_id()) {
					updateList.add(department);
				} else {
					department.setCreate_time(new Date());
					insertList.add(department);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += departmentDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += departmentDao.updateList(updateList);
			}
		}
		return result;
	}

}
