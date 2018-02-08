package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.LineEmployee;
import com.ltybd.mapper.LineEmployeeMapper;
import com.ltybd.service.LineEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineEmployeeServiceImpl.java
 *
 * describe:线路员工对应信息接口实现类
 * 
 * 2017年10月12日 下午4:14:19 created By Yancz version 0.1
 *
 * 2017年10月12日 下午4:14:19 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineEmployeeServiceImpl", description = "线路员工对应信息接口实现类")
@Service
public class LineEmployeeServiceImpl implements LineEmployeeService {

	@Autowired
	private LineEmployeeMapper lineEmployeeDao;

	@ApiOperation(value = "查询线路员工对应信息集合")
	@Override
	public List<LineEmployee> findListObj(LineEmployee lineEmployee) {
		return lineEmployeeDao.select(lineEmployee);
	}

	@ApiOperation(value = "刪除线路员工对应信息对象")
	@Override
	public int delete(LineEmployee lineEmployee) {
		return lineEmployeeDao.delete(lineEmployee);
	}

	@ApiOperation(value = "更新线路员工对应信息对象集合")
	@Override
	public int updateObj(LineEmployee lineEmployee) {
		int count = 0;
		LineEmployee queryVO = new LineEmployee();
		queryVO.setEmployee_id(lineEmployee.getEmployee_id());
		queryVO.setLine_id(lineEmployee.getLine_id());
		List<LineEmployee> queryList = this.findListObj(queryVO);
		if (null != queryList && queryList.size() > 0) {
			count = this.update(lineEmployee);
		} else {
			count = this.insert(lineEmployee);
		}
		return count;
	}

	@ApiOperation(value = "插入线路员工对应信息对象")
	private int insert(LineEmployee lineEmployee) {
		if(null==lineEmployee.getStatus()){
			lineEmployee.setStatus(0);
		}
		lineEmployee.setCreate_time(new Date());
		lineEmployee.setLast_modified_time(new Date());
		return lineEmployeeDao.insert(lineEmployee);
	}

	@ApiOperation(value = "修改线路员工对应信息对象")
	private int update(LineEmployee lineEmployee) {
		if(null==lineEmployee.getStatus()){
			lineEmployee.setStatus(0);
		}
		lineEmployee.setLast_modified_time(new Date());
		return lineEmployeeDao.update(lineEmployee);
	}
	
	@ApiOperation(value = "批量线路站点信息对象")
	@Transactional
	@Override
	public int updateList(List<LineEmployee> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<LineEmployee> insertList = new ArrayList<LineEmployee>();
			List<LineEmployee> updateList = new ArrayList<LineEmployee>();
			for (LineEmployee lineEmployee : list) {
				LineEmployee queryVO = new LineEmployee();
				queryVO.setLine_id(lineEmployee.getLine_id());
				queryVO.setEmployee_id(lineEmployee.getEmployee_id());
				List<LineEmployee> queryList = this.findListObj(queryVO);
				if (null == lineEmployee.getStatus()) {
					lineEmployee.setStatus(0);
				}
				lineEmployee.setLast_modified_time(new Date());
				if (null != queryList && !queryList.isEmpty()) {
					updateList.add(lineEmployee);
				} else {
					lineEmployee.setCreate_time(new Date());
					insertList.add(lineEmployee);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += lineEmployeeDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += lineEmployeeDao.updateList(updateList);
			}
		}
		return result;
	}

	@ApiOperation(value = "根据线路ID查询员工对应信息对象")
	@Override
	public List<LineEmployee> findbyLineId(LineEmployee lineEmployee) {

		return lineEmployeeDao.findbyLineId(lineEmployee);
	}

}
