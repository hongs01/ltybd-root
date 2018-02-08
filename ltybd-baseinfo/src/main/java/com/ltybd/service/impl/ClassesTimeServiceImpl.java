package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.ClassesTime;
import com.ltybd.entity.ClassesTimeBean;
import com.ltybd.mapper.ClassesTimeMapper;
import com.ltybd.service.ClassesTimeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ClassesTimeServiceImpl", description = "班次时间接口实现类")
@Service
public class ClassesTimeServiceImpl implements ClassesTimeService {
	@Autowired
	private ClassesTimeMapper classesTimeDao;
	
	@ApiOperation(value="增加班次时间信息")
	@Override
	public int insertClassesTime(ClassesTime classesTime){
		return classesTimeDao.insertClassesTime(classesTime);
	}
	
	@ApiOperation(value="修改班次时间信息")
	@Override
	public int updateClassesTime(ClassesTime classesTime){
		return classesTimeDao.updateClassesTime(classesTime);
	}
	
	@ApiOperation(value="删除班次时间信息")
	@Override
	public int deleteClassesTime(ClassesTime classesTime){
		return classesTimeDao.delete(classesTime);
	}
	
	@ApiOperation(value="查询班次时间信息")
	@Override
	public ClassesTime findById(ClassesTime classesTime){
		return classesTimeDao.findById(classesTime);
	}
	
	@ApiOperation(value="查询班次下的时间列表")
	@Override
	public List<ClassesTimeBean> findTimeList(Integer classes_id){
		return classesTimeDao.findTimeList(classes_id);
	}
	
}
