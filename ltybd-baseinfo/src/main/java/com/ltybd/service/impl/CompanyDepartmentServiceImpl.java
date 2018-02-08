package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.CompanyDepartment;
import com.ltybd.mapper.CompanyDepartmentMapper;
import com.ltybd.service.CompanyDepartmentService;

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
@Api(value = "CompanyDepartmentServiceImpl", description = "公司部门对应信息接口实现类")
@Service
public class CompanyDepartmentServiceImpl implements CompanyDepartmentService{
	@Autowired
	private CompanyDepartmentMapper companyDepartmentDao;
	
	@ApiOperation(value = "查询公司部门对应信息集合")
	@Override
	public List<CompanyDepartment> findListObj(CompanyDepartment companyDepartment) {
		return companyDepartmentDao.select(companyDepartment);
	}

	@ApiOperation(value = "刪除公司部门对应信息对象")
	@Override
	public int delete(CompanyDepartment companyDepartment) {
		return companyDepartmentDao.delete(companyDepartment);
	}

	@ApiOperation(value = "更新公司部门对应信息对象集合")
	@Override
	public int updateObj(CompanyDepartment companyDepartment) {
		int count = 0;
		CompanyDepartment queryVO = new CompanyDepartment();
		queryVO.setCompany_id(companyDepartment.getCompany_id());
		queryVO.setDepartment_id(companyDepartment.getDepartment_id());
		List<CompanyDepartment> queryList = this.findListObj(queryVO);
		if (null != queryList && queryList.size() > 0) {
			count = this.update(companyDepartment);
		} else {
			count = this.insert(companyDepartment);
		}
		return count;
	}

	@ApiOperation(value = "插入公司部门对应信息对象")
	private int insert(CompanyDepartment companyDepartment) {
		if(null==companyDepartment.getStatus()){
			companyDepartment.setStatus(0);
		}
		companyDepartment.setCreate_time(new Date());
		companyDepartment.setLast_modified_time(new Date());
		return companyDepartmentDao.insert(companyDepartment);
	}

	@ApiOperation(value = "修改公司部门对应信息对象")
	private int update(CompanyDepartment companyDepartment) {
		if(null==companyDepartment.getStatus()){
			companyDepartment.setStatus(0);
		}
		companyDepartment.setLast_modified_time(new Date());
		return companyDepartmentDao.update(companyDepartment);
	}
	
	@ApiOperation(value = "批量公司部门信息对象")
	@Transactional
	@Override
	public int updateList(List<CompanyDepartment> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<CompanyDepartment> insertList = new ArrayList<CompanyDepartment>();
			List<CompanyDepartment> updateList = new ArrayList<CompanyDepartment>();
			for (CompanyDepartment companyDepartment : list) {
				CompanyDepartment queryVO = new CompanyDepartment();
				queryVO.setCompany_id(companyDepartment.getCompany_id());
				queryVO.setDepartment_id(companyDepartment.getDepartment_id());
				List<CompanyDepartment> queryList = this.findListObj(queryVO);
				if (null == companyDepartment.getStatus()) {
					companyDepartment.setStatus(0);
				}
				companyDepartment.setLast_modified_time(new Date());
				if (null != queryList && !queryList.isEmpty()) {
					updateList.add(companyDepartment);
				} else {
					companyDepartment.setCreate_time(new Date());
					insertList.add(companyDepartment);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += companyDepartmentDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += companyDepartmentDao.updateList(updateList);
			}
		}
		return result;
	}
}
