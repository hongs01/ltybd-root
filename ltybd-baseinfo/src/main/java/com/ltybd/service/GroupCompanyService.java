package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.GroupCompany;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * GroupCompanyService.java
 *
 * describe:集团公司对应信息接口
 * 
 * 2017年10月17日 下午4:17:12 created By Yancz version 0.1
 *
 * 2017年10月17日 下午4:17:12 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "GroupCompanyService", description = "集团公司对应信息接口")
public interface GroupCompanyService {

	@ApiOperation(value = "查询集团公司对应信息集合")
	public List<GroupCompany> findListObj(GroupCompany groupCompany);

	@ApiOperation(value = "刪除集团公司对应信息对象")
	public int delete(GroupCompany groupCompany);

	@ApiOperation(value = "更新集团公司对应信息对象")
	public int updateObj(GroupCompany groupCompany);

	@ApiOperation(value = "批量集团公司对应信息对象")
	public int updateList(List<GroupCompany> list);

}
