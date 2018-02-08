package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.CompanyDepartment;
import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.LineBean;
import com.ltybd.entity.Yard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyDepartmentService.java
 *
 * describe:车场对应信息接口
 * 
 * 2017年10月17日 下午7:00:23 created By wugj version 0.1
 *
 * 2017年10月17日 下午7:00:23 modifyed By wugj version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "YardService", description = "车场管理对应信息接口")
public interface YardService {
	@ApiOperation(value = "查询车场对应信息集合")
	public List<Yard> findYardList(Yard yard);

	@ApiOperation(value = "删除车场列表信息集合")
	public int deleteYardList(String yard_ids);

	@ApiOperation(value = "更新车场信息")
	public void updateYard(Yard yard);

	@ApiOperation(value = "根据ID查询车场信息")
	public Object findYardById(Integer yard_id);

	@ApiOperation(value = "根据ID删除车场信息")
	public int deleteYard(Integer yard_id);

	@ApiOperation(value = "查询车场对应线路信息列表")
	public List<Line> findLinesByYardId(Yard yard);

	@ApiOperation(value = "查询车场对应调度屏信息列表")
	public List<Dispatch_Screen> findScreenByYardId(Yard yard);

	@ApiOperation(value = "根据ID查询车场对象")
	public Yard getYardById(Yard yard);

	@ApiOperation(value = "新增车场信息")
	public void addYard(Yard yard);

	@ApiOperation(value = "获取Sequence")
	public Integer getSequence(String code);

	@ApiOperation(value = "获取线路")
	public List<String> findLineById(Integer yard_id);

	@ApiOperation(value = "导出excle,通过ID获取车场信息集合")
	public List<Yard> findYardListByIds(String yard_ids);

	@ApiOperation(value = "导入excle,批量添加车场信息")
	public void addYardList(List<Yard> list);

}
