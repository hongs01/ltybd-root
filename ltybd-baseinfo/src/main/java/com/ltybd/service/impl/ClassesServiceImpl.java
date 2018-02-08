package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Classes;
import com.ltybd.mapper.ClassesMapper;
import com.ltybd.service.ClassesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ClassesServiceImpl", description = "班次接口实现类")
@Service
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	private ClassesMapper classesDao;
	
	@ApiOperation(value="查询班次列表")
	@Override
	public List<Classes> findClassesList(Classes classes){
		return classesDao.findClassesList(classes);
	};
	
	@ApiOperation(value="查询班次对象")
	@Override
	public Classes findById(Integer classes_id){
		return classesDao.findById(classes_id);
	};
	
	@ApiOperation(value="插入班次")
	@Override
	public int insert(Classes classes){
		return classesDao.insert(classes);
	};
	
	@ApiOperation(value="修改班次")
	@Override
	public int update(Classes classes){
		return classesDao.updateByPrimaryKeySelective(classes);
	};
	
	@ApiOperation(value="删除班次")
	@Override
	public int deleteItems(String  ids){
		return classesDao.deleteItems(ids);
	}
	
}
