package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.ClassesTime;
import com.ltybd.entity.ClassesTimeBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ClassesTimeService", description = "班次时间接口")
public interface ClassesTimeService {
	@ApiOperation(value="增加班次时间信息")
	public int insertClassesTime(ClassesTime classesTime);
	
	@ApiOperation(value="修改班次时间信息")
	public int updateClassesTime(ClassesTime classesTime);
	
	@ApiOperation(value="删除班次时间信息")
	public int deleteClassesTime(ClassesTime classesTime);
	
	@ApiOperation(value="查询班次时间信息")
	public ClassesTime findById(ClassesTime classesTime);
	
	@ApiOperation(value="查询班次下的时间列表")
	public List<ClassesTimeBean> findTimeList(Integer classes_id);
	
}
