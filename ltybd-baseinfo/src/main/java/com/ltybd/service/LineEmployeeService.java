package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.LineEmployee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * LineEmployeeService.java
 *
 * describe:线路员工对应信息接口
 * 
 * 2017年10月12日 下午4:10:46 created By Yancz version 0.1
 *
 * 2017年10月12日 下午4:10:46 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineEmployeeService", description = "线路员工对应信息接口")
public interface LineEmployeeService {

	@ApiOperation(value = "查询线路员工对应信息集合")
	public List<LineEmployee> findListObj(LineEmployee lineEmployee);

	@ApiOperation(value = "刪除线路员工对应信息对象")
	public int delete(LineEmployee lineEmployee);
	
	@ApiOperation(value = "更新线路员工对应信息对象")
	public int updateObj(LineEmployee lineEmployee);

	@ApiOperation(value = "批量线路员工对应信息对象")
	public int updateList(List<LineEmployee> list);

	@ApiOperation(value = "根据线路ID查询员工对应信息对象")
	public List<LineEmployee> findbyLineId(LineEmployee lineEmployee);
}
