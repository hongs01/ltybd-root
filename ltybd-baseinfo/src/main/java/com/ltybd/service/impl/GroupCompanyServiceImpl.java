package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.GroupCompany;
import com.ltybd.mapper.GroupCompanyMapper;
import com.ltybd.service.GroupCompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * GroupCompanyServiceImpl.java
 *
 * describe:集团公司对应信息接口实现类
 * 
 * 2017年10月17日 下午4:27:19 created By Yancz version 0.1
 *
 * 2017年10月17日 下午4:27:19 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "GroupCompanyServiceImpl", description = "集团公司对应信息接口实现类")
@Service
public class GroupCompanyServiceImpl implements GroupCompanyService {

	@Autowired
	private GroupCompanyMapper groupCompanyDao;

	@ApiOperation(value = "查询集团公司对应信息集合")
	@Override
	public List<GroupCompany> findListObj(GroupCompany groupCompany) {
		return groupCompanyDao.select(groupCompany);
	}

	@ApiOperation(value = "更新集团公司对应信息对象")
	@Override
	public int updateObj(GroupCompany groupCompany) {
		int result = 0;
		GroupCompany queryVO = new GroupCompany();
		queryVO.setGroup_id(groupCompany.getGroup_id());
		queryVO.setCompany_id(groupCompany.getCompany_id());
		List<GroupCompany> list = this.findListObj(queryVO);
		if (null != list && list.size() > 0) {
			result += this.update(groupCompany);
		} else {
			result += this.insert(groupCompany);
		}
		return result;
	}

	@ApiOperation(value = "刪除集团公司对应信息对象")
	@Override
	public int delete(GroupCompany groupCompany) {
		return groupCompanyDao.delete(groupCompany);
	}

	@ApiOperation(value = "插入集团公司对应信息对象")
	private int insert(GroupCompany groupCompany) {
		if(null==groupCompany.getStatus()){
			groupCompany.setStatus(0);
		}
		groupCompany.setCreate_time(new Date());
		groupCompany.setLast_modified_time(new Date());
		return groupCompanyDao.insert(groupCompany);
	}

	@ApiOperation(value = "修改集团公司对应信息对象")
	private int update(GroupCompany groupCompany) {
		if(null==groupCompany.getStatus()){
			groupCompany.setStatus(0);
		}
		groupCompany.setLast_modified_time(new Date());
		return groupCompanyDao.update(groupCompany);
	}
	
	@ApiOperation(value = "批量更新集团公司对应信息对象")
	@Transactional
	@Override
	public int updateList(List<GroupCompany> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<GroupCompany> insertList = new ArrayList<GroupCompany>();
			List<GroupCompany> updateList = new ArrayList<GroupCompany>();
			for (GroupCompany groupCompany : list) {
				GroupCompany queryVO = new GroupCompany();
				queryVO.setGroup_id(groupCompany.getGroup_id());
				queryVO.setCompany_id(groupCompany.getCompany_id());
				List<GroupCompany> queryList = this.findListObj(queryVO);
				if (null == groupCompany.getStatus()) {
					groupCompany.setStatus(0);
				}
				groupCompany.setLast_modified_time(new Date());
				if (null != queryList && !queryList.isEmpty()) {
					updateList.add(groupCompany);
				} else {
					groupCompany.setCreate_time(new Date());
					insertList.add(groupCompany);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += groupCompanyDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += groupCompanyDao.updateList(updateList);
			}
		}
		return result;
	}

}
