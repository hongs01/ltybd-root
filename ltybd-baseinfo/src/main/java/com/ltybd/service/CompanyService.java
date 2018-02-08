package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Company;
import com.ltybd.entity.CompanyBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyService.java
 *
 * describe:公司信息接口
 * 
 * 2017年10月17日 下午7:00:57 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:00:57 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="CompanyService", description = "公司信息接口")
public interface CompanyService {
	@ApiOperation(value="查询公司信息集合")
	public List<CompanyBean> findListObj(Company company);
	
	@ApiOperation(value="查询公司信息对象")
	public CompanyBean findByCompanyId(Company company);
	
	@ApiOperation(value="插入公司信息对象")
	public int insert(Company company);
	
	@ApiOperation(value="修改公司信息对象")
	public int update(Company company);
	
	@ApiOperation(value = "批量更新公司信息")
	public int updateList(List<Company> list);
	
	@ApiOperation(value="删除公司信息对象")
	public int delete(Company company);
	
	@ApiOperation(value="批量删除公司信息对象")
	public int deleteItems(String  ids);
	
	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String  code);
}
