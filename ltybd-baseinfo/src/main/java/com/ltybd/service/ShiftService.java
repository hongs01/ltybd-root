package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Classes;
import com.ltybd.entity.Shift;
import com.ltybd.entity.ShiftClasses;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ShiftService", description = "班制接口")
public interface ShiftService {

	@ApiOperation(value="插入班制对象")
	public int insert(Shift shift);
	
	@ApiOperation(value="修改班制对象")
	public int update(Shift shift);
	
	@ApiOperation(value="查询班制对象")
	public Shift findById(Integer shift_id);
	
	@ApiOperation(value="查询班制列表")
	public List<Shift> findShiftList(Shift shift);
	
	@ApiOperation(value="删除班制")
	public int deleteItems(String ids);
	
	@ApiOperation(value="插入班制与班次对应对象")
	public int insertShiftClasses(ShiftClasses shiftClasses);
	
	@ApiOperation(value="修改班制与班次对应对象")
	public int updateShiftClasses(Integer classes_id,Integer seq_id);
	
	@ApiOperation(value="查询班制下的班次列表")
	public List<Classes> selectShiftClassesList(Integer shift_id);
	
	@ApiOperation(value="查询班制与班次对应对象")
	public ShiftClasses selectShiftClasse(Integer seq_id);
}
