package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Line;
import com.ltybd.entity.LineBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineService.java
 *
 * describe:线路接口
 * 
 * 2017年10月12日 上午11:53:56 created By Yancz version 0.1
 *
 * 2017年10月12日 上午11:53:56 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineService", description = "线路接口")
public interface LineService {

	@ApiOperation(value = "查询线路集合")
	public List<LineBean> findListObj(Line line);

	@ApiOperation(value = "查询线路对象")
	public LineBean findByLineId(Line line);

	@ApiOperation(value = "插入线路对象")
	public int insert(Line line);

	@ApiOperation(value = "修改线路对象")
	public int update(Line line);

	@ApiOperation(value = "删除线路对象")
	public int delete(Line line);

	@ApiOperation(value = "批量删除线路对象")
	public int deleteItems(String ids);

	@ApiOperation(value = "获取Sequence")
	public int getSequence(String code);
	 
	@ApiOperation(value = "批量更新线路对象")
	public int updateList(List<Line> list);
	
	@ApiOperation(value = "根据线路ID获得人车数量")
	public LineBean findRateByLineId(Line line);

	@ApiOperation(value = "查询线路所有支线")
	public List<LineBean> findBranchById(Line line);

	@ApiOperation(value = "根据ID集合查询线路")
	public List<LineBean> batchfindListObj(String ids);

	@ApiOperation(value = "根据线路编号查询线路")
	public LineBean findByLineCode(Line line);

}
