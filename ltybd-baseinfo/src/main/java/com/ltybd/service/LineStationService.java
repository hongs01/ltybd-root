package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.LineStation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineStationService.java
 *
 * describe:线路站点信息接口
 * 
 * 2017年10月13日 上午10:51:13 created By Yancz version 0.1
 *
 * 2017年10月13日 上午10:51:13 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineStationService", description = "线路站点信息接口")
public interface LineStationService {

	@ApiOperation(value = "查询线路站点信息集合")
	public List<LineStation> findListObj(LineStation lineStation);

	@ApiOperation(value = "刪除线路站点信息对象")
	public int delete(LineStation lineStation);

	@ApiOperation(value = "更新线路站点信息对象")
	public int updateObj(LineStation lineStation);
	
	@ApiOperation(value = "批量线路站点信息对象")
	public int updateList(List<LineStation> list);

	@ApiOperation(value = "根据线路ID查询线路站点对象")
	public List<LineStation> findByLineId(LineStation lineStation);

}
