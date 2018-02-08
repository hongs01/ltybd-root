package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.DateType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DateTypeService.java
 *
 * describe:
 * 
 * 2017年11月6日 下午2:11:52 created By chenq version 0.1
 *
 * 2017年11月6日 下午2:11:52 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "DateTypeService", description = "日期类型信息接口")
public interface DateTypeService {
	
	@ApiOperation(value="查询日期类型信息集合")
	public List<DateType> findDateTypeList(DateType dateType);
	
	@ApiOperation(value="查询日期类型信息对象")
	public DateType findByDateTypeId(DateType dateType);
	
	@ApiOperation(value="修改日期类型信息对象")
	public int updateDateType(DateType dateType);
	
	@ApiOperation(value="增加日期类型信息对象")
	public int insertDateType(DateType dateType);
	
	@ApiOperation(value="删除日期类型信息对象")
	public int deleteDateType(DateType dateType);
	
	@ApiOperation(value="批量删除日期类型信息对象")
	public int deleteDateTypeItems(String  ids);
	
	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String  code);

	@ApiOperation(value="批量查询日期类型对象")
	public List<DateType> batchfindListObj(String ids);
	
}
