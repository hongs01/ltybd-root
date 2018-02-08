package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.District;
import com.ltybd.entity.Road;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DistrictService.java
 *
 * describe:
 * 
 * 2017年11月8日 下午1:51:36 created By chenq version 0.1
 *
 * 2017年11月8日 下午1:51:36 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "DistrictService", description = "区域信息接口")
public interface DistrictService {

	@ApiOperation(value="查询区域信息对象")
	public District findDistrictById(District district);

	@ApiOperation(value="删除区域对象")
	public int deleteDistrict(District district) ;

	@ApiOperation(value="批量删除区域对象")
	public int deleteDistrictItems(String ids);

	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String string);

	@ApiOperation(value="增加区域对象")
	public int insertDistrict(District district);

	@ApiOperation(value="修改区域对象")
	public int updateDistrict(District district);

	@ApiOperation(value="查询区域信息集合")
	public List<District> findDistrictList(District district);

	@ApiOperation(value="批量查询区域对象")
	public List<District> batchfindListObj(String ids);

	@ApiOperation(value="通过区域id查询道路信息")
	public List<Road> findRoadsByDistrictId(District district);

}
