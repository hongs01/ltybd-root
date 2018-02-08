package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Classes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ClassesService", description = "班次接口")
public interface ClassesService {

	@ApiOperation(value="查询班次列表")
	public List<Classes> findClassesList(Classes classes);
	
	@ApiOperation(value="查询班次对象")
	public Classes findById(Integer classes_id);
	
	@ApiOperation(value="插入班次对象")
	public int insert(Classes classes);
	
	@ApiOperation(value="修改班次对象")
	public int update(Classes classes);
	
	@ApiOperation(value="删除班次对象")
	public int deleteItems(String  ids);
}
