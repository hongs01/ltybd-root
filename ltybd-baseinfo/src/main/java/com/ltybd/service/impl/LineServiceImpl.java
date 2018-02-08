package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Line;
import com.ltybd.entity.LineBean;
import com.ltybd.mapper.LineMapper;
import com.ltybd.service.LineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineServiceImpl.java
 *
 * describe:线路接口实现类
 * 
 * 2017年10月12日 上午11:53:23 created By Yancz version 0.1
 *
 * 2017年10月12日 上午11:53:23 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineServiceImpl", description = "线路接口实现类")
@Service
public class LineServiceImpl implements LineService {

	@Autowired
	private LineMapper lineDao;

	@ApiOperation(value = "查询线路集合")
	@Override
	public List<LineBean> findListObj(Line line) {
		if(null == line.getStatus()){
			line.setStatus(0);
		}
		return lineDao.findListObj(line);
	}

	@ApiOperation(value = "查询线路对象")
	@Override
	public LineBean findByLineId(Line line) {
		return lineDao.findByLineId(line);
	}

	@ApiOperation(value = "插入线路对象")
	@Override
	public int insert(Line line) {
		if(null==line.getStatus()){
			line.setStatus(0);
		}
		line.setCreate_time(new Date());
		line.setLast_modified_time(new Date());
		return lineDao.insert(line);
	}

	@ApiOperation(value = "修改线路对象")
	@Override
	public int update(Line line) {
		if(null==line.getStatus()){
			line.setStatus(0);
		}
		line.setLast_modified_time(new Date());
		return lineDao.updateByPrimaryKeySelective(line);
	}

	@ApiOperation(value = "删除线路对象")
	@Override
	public int delete(Line line) {
		return lineDao.delete(line);
	}

	@ApiOperation(value = "批量删除线路对象")
	@Override
	public int deleteItems(String ids) {
		return lineDao.deleteItems(ids);
	}

	@ApiOperation(value = "获取Sequence")
	@Override
	public int getSequence(String code) {
		return lineDao.getSequence(code);
	}
	
	@ApiOperation(value = "批量线路站点信息对象")
	@Transactional
	@Override
	public int updateList(List<Line> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Line> insertList = new ArrayList<Line>();
			List<Line> updateList = new ArrayList<Line>();
			for (Line line : list) {
				if (null == line.getStatus()) {
					line.setStatus(0);
				}
				line.setLast_modified_time(new Date());
				if (null != line.getLine_id()) {
					updateList.add(line);
				} else {
					line.setCreate_time(new Date());
					insertList.add(line);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += lineDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += lineDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "根据线路ID获得人车数量")
	@Override
	public LineBean findRateByLineId(Line line) {
		if(null == line.getStatus()){
			line.setStatus(0);
		}
		return lineDao.findRateByLineId(line);
	}

	@ApiOperation(value = "查询线路所有支线")
	@Override
	public List<LineBean> findBranchById(Line line) {
		if(null == line.getStatus()){
			line.setStatus(0);
		}
		return lineDao.findBranchById(line);
	}

	@ApiOperation(value = "根据ID集合查询线路")
	@Override
	public List<LineBean> batchfindListObj(String ids) {
		
		return lineDao.batchfindListObj(ids);
	}

	@ApiOperation(value = "根据线路编号查询线路")
	@Override
	public LineBean findByLineCode(Line line) {
		if(null==line.getStatus()){
			line.setStatus(0);
		}
		return lineDao.findByLineCode(line);
	}

}
