package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.DrawMap;
import com.ltybd.entity.LinePoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月9日
 */
@Api(value = "LinePointService", description = "线路辅助点接口")
public interface LinePointService {

	@ApiOperation(value = "根据线路ID查询辅助点对象")
	List<LinePoint> findByLineId(LinePoint linePoint);

	@ApiOperation(value = "根据线路ID删除辅助点对象")
	int deleteByLineId(LinePoint linePoint);

	@ApiOperation(value = "插入多个线路辅助点对象")
	int insertList(List list);

}
