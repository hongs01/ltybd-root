package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Company;
import com.ltybd.entity.CompanyBean;
import com.ltybd.mapper.CompanyMapper;
import com.ltybd.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyServiceImpl.java
 *
 * describe:公司接口实现类
 * 
 * 2017年10月17日 下午7:01:09 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:01:09 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="CompanyServiceImpl", description = "公司接口实现类")
@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyMapper companyDao;
	
	@ApiOperation(value="查询公司信息集合")
	@Override
	public List<CompanyBean> findListObj(Company company){
		return companyDao.findListObj(company);
	}
	
	@ApiOperation(value="查询公司信息对象")
	@Override
	public CompanyBean findByCompanyId(Company company){
		return companyDao.findByCompanyId(company);
	}
	
	@ApiOperation(value="插入公司信息对象")
	@Override
	public int insert(Company company){
		return companyDao.insert(company);
	}
	
	@ApiOperation(value="修改公司信息对象")
	@Override
	public int update(Company company){
		return companyDao.updateByPrimaryKeySelective(company);
	}
	
	@ApiOperation(value="删除公司对象")
	@Override
	public int delete(Company company){
		return companyDao.delete(company);
	}
	
	@ApiOperation(value="批量删除公司信息对象")
	@Override
	public int deleteItems(String  ids){
		return companyDao.deleteItems(ids);
	}
	
	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String  code){
		return companyDao.getSequence(code);
	}
	
	@ApiOperation(value = "批量更新公司信息对象")
	@Transactional
	@Override
	public int updateList(List<Company> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Company> insertList = new ArrayList<Company>();
			List<Company> updateList = new ArrayList<Company>();
			for (Company company : list) {
				if (null == company.getStatus()) {
					company.setStatus(0);
				}
				company.setLast_modified_time(new Date());
				if (null != company.getCompany_id()) {
					updateList.add(company);
				} else {
					company.setCreate_time(new Date());
					insertList.add(company);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += companyDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += companyDao.updateList(updateList);
			}
		}
		return result;
	}
}
