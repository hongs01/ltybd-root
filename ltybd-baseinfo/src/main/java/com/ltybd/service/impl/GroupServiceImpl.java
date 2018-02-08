package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Group;
import com.ltybd.mapper.GroupMapper;
import com.ltybd.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * GroupServiceImpl.java
 *
 * describe:集团信息接口实现类
 * 
 * 2017年10月17日 下午2:49:54 created By Yancz version 0.1
 *
 * 2017年10月17日 下午2:49:54 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "GroupServiceImpl", description = "集团信息接口实现类")
@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupDao;

	@ApiOperation(value = "查询集团信息集合")
	@Override
	public List<Group> findListObj(Group group) {
			return groupDao.findListObj(group);
	}

	@ApiOperation(value = "查询集团信息对象")
	@Override
	public Group findByGroupId(Group group) {
		return groupDao.selectByPrimaryKey(group);
	}

	@ApiOperation(value = "插入集团信息对象")
	@Override
	public int insert(Group group) {
		if(null==group.getStatus()){
			group.setStatus(0);
		}
		group.setCreate_time(new Date());
		group.setLast_modified_time(new Date());
		return groupDao.insert(group);
	}

	@ApiOperation(value = "修改集团信息对象")
	@Override
	public int update(Group group) {
		if(null==group.getStatus()){
			group.setStatus(0);
		}
		group.setLast_modified_time(new Date());
		return groupDao.updateByPrimaryKeySelective(group);
	}

	@ApiOperation(value = "删除集团信息对象")
	@Override
	public int delete(Group group) {
		return groupDao.delete(group);
	}

	@ApiOperation(value = "批量删除集团信息对象")
	@Override
	public int deleteItems(String ids) {
		return groupDao.deleteItems(ids);
	}

	@ApiOperation(value = "获取Sequence")
	@Override
	public int getSequence(String code) {
		return groupDao.getSequence(code);
	}
	
	@ApiOperation(value = "批量更新集团信息对象")
	@Transactional
	@Override
	public int updateList(List<Group> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Group> insertList = new ArrayList<Group>();
			List<Group> updateList = new ArrayList<Group>();
			for (Group group : list) {
				if (null == group.getStatus()) {
					group.setStatus(0);
				}
				group.setLast_modified_time(new Date());
				if (null != group.getGroup_id()) {
					updateList.add(group);
				} else {
					group.setCreate_time(new Date());
					insertList.add(group);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += groupDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += groupDao.updateList(updateList);
			}
		}
		return result;
	}

}
