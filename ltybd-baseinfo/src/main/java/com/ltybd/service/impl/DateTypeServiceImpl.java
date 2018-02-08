package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.DateType;
import com.ltybd.mapper.DateTypeMapper;
import com.ltybd.service.DateTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DateTypeServiceImpl.java
 *
 * describe:
 * 
 * 2017年11月6日 下午5:32:49 created By chenq version 0.1
 *
 * 2017年11月6日 下午5:32:49 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="DateTypeServiceImpl", description = "日期类型接口实现类")
@Service
public class DateTypeServiceImpl implements DateTypeService{

	@Autowired
	private DateTypeMapper dateTypeMapperDao;
	
	
	@ApiOperation(value = "查询日期类型集合")
	@Override
	public List<DateType> findDateTypeList(DateType dateType) {
		return dateTypeMapperDao.findDateTypeList(dateType);
	}

	@ApiOperation(value = "查询公司部门对应信息对象")
	@Override
	public DateType findByDateTypeId(DateType dateType) {
		return dateTypeMapperDao.findByDateTypeId(dateType);
	}

	@ApiOperation(value = "修改日期类型信息对象")
	@Override
	public int updateDateType(DateType dateType) {
		return dateTypeMapperDao.updateByPrimaryKeySelective(dateType);
	}

	@ApiOperation(value = "增加日期类型信息对象")
	@Override
	public int insertDateType(DateType dateType) {
		return dateTypeMapperDao.insert(dateType);
	}

	@ApiOperation(value = "删除日期类型信息对象")
	@Override
	public int deleteDateType(DateType dateType) {
		return dateTypeMapperDao.delete(dateType);
	}

	@ApiOperation(value = "批量删除日期类型信息对象")
	@Override
	public int deleteDateTypeItems(String ids) {
		return dateTypeMapperDao.deleteDateTypeItems(ids);
	}

	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String code) {
		return dateTypeMapperDao.getSequence(code);
	}

	@ApiOperation(value="批量查询日期类型信息对象")
	@Override
	public List<DateType> batchfindListObj(String ids) {
		return dateTypeMapperDao.batchfindListObj(ids);
	}

}
