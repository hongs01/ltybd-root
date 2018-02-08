package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Classes;
import com.ltybd.entity.Shift;
import com.ltybd.entity.ShiftClasses;
import com.ltybd.mapper.ShiftMapper;
import com.ltybd.service.ShiftService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ShiftServiceImpl", description = "班制接口实现类")
@Service
public class ShiftServiceImpl implements ShiftService {
	@Autowired
	private ShiftMapper shiftDao;
	
	@ApiOperation(value="插入班制对象")
	@Override
	public int insert(Shift shift){
		return shiftDao.insert(shift);
	}
	
	@ApiOperation(value="修改班制对象")
	@Override
	public int update(Shift shift){
		return shiftDao.updateByPrimaryKeySelective(shift);
	}
	
	@ApiOperation(value="查询班制对象")
	@Override
	public Shift findById(Integer shift_id){
		return shiftDao.findById(shift_id);
	}
	
	@ApiOperation(value="查询班制列表")
	@Override
	public List<Shift> findShiftList(Shift shift){
		return shiftDao.findShiftList(shift);
	}
	
	@ApiOperation(value="删除班制")
	@Override
	public int deleteItems(String ids){
		return shiftDao.deleteItems(ids);
	}
	
	@ApiOperation(value="插入班制与班次对应对象")
	@Override
	public int insertShiftClasses(ShiftClasses shiftClasses){
		return shiftDao.insertShiftClasses(shiftClasses);
	}
	
	@ApiOperation(value="修改班制与班次对应对象")
	@Override
	public int updateShiftClasses(Integer classes_id,Integer seq_id){
		return shiftDao.updateShiftClasses(classes_id,seq_id);
	}
	
	@ApiOperation(value="查询班制下的班次列表")
	@Override
	public List<Classes> selectShiftClassesList(Integer shift_id){
		return shiftDao.selectShiftClassesList(shift_id);
	}
	
	@ApiOperation(value="查询班制与班次对应对象")
	@Override
	public ShiftClasses selectShiftClasse(Integer seq_id){
		return shiftDao.selectShiftClasse(seq_id);
	}
	
}
