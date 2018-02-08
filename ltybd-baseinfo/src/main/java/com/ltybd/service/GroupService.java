package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * GroupService.java
 *
 * describe:集团信息接口
 * 
 * 2017年10月17日 下午2:46:43 created By Yancz version 0.1
 *
 * 2017年10月17日 下午2:46:43 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "GroupService", description = "集团信息接口")
public interface GroupService {

	@ApiOperation(value = "查询集团信息集合")
	public List<Group> findListObj(Group group);

	@ApiOperation(value = "查询集团信息对象")
	public Group findByGroupId(Group group);

	@ApiOperation(value = "插入集团信息对象")
	public int insert(Group group);

	@ApiOperation(value = "修改集团信息对象")
	public int update(Group group);

	@ApiOperation(value = "删除集团信息对象")
	public int delete(Group group);

	@ApiOperation(value = "批量删除集团信息对象")
	public int deleteItems(String ids);

	@ApiOperation(value = "获取Sequence")
	public int getSequence(String code);
	
	@ApiOperation(value = "批量更新集团信息对象")
	public int updateList(List<Group> list);

}
