package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.DrawMap;
import com.ltybd.entity.LinePoint;
import com.ltybd.mapper.LineMapper;
import com.ltybd.mapper.LinePointMapper;
import com.ltybd.service.LinePointService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月9日
 */
@Api(value = "LinePointServiceImpl", description = "线路辅助点接口实现类")
@Service
public class LinePointServiceImpl implements LinePointService {

	@Autowired
	private LinePointMapper linePointDao;
	
	@ApiOperation(value = "根据线路ID查询辅助点对象")
	@Override
	public List<LinePoint> findByLineId(LinePoint linePoint) {
		
		return linePointDao.findByLineId(linePoint);
	}

	@ApiOperation(value = "根据线路ID删除辅助点对象")
	@Override
	public int deleteByLineId(LinePoint linePoint) {
		
		return linePointDao.deleteByLineId(linePoint);
	}
	
	@ApiOperation(value = "插入多个线路辅助点对象")
	@Override
	public int insertList(List list) {
		
		return linePointDao.insertList(list);
	}

}
