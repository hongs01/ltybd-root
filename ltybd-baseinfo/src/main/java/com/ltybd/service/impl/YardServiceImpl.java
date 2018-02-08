package com.ltybd.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.LineBean;
import com.ltybd.entity.Yard;
import com.ltybd.mapper.YardMapper;
import com.ltybd.service.YardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyDepartmentServiceImpl.java
 *
 * describe:公司部门对应信息接口实现类
 * 
 * 2017年10月17日 下午7:17:15 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:17:15 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "YardServiceImpl", description = "车场对应信息接口实现类")
@Service
public class YardServiceImpl implements YardService{
	@Autowired
	private YardMapper yardMapper;
	
	@ApiOperation(value = "查询车场对应信息集合")
	@Override
	public List<Yard> findYardList(Yard yard) {
		return yardMapper.findYardList(yard);
	}
	
	@ApiOperation(value = "删除车场列表信息集合")
	@Override
	public int deleteYardList(String yard_ids) {
		return yardMapper.deleteYardList(yard_ids);
	}

	@ApiOperation(value = "更新车场信息")
	@Override
	public void updateYard(Yard yard) {
		yardMapper.updateYard(yard);
	}

	@ApiOperation(value = "根据ID查询车场信息")
	@Override
	public Object findYardById(Integer yard_id) {
		return yardMapper.selectByPrimaryKey(yard_id);
	}

	@ApiOperation(value = "根据ID删除车场信息")
	@Override
	public int deleteYard(Integer yard_id) {
		return yardMapper.deleteByPrimaryKey(yard_id);
	}

	@ApiOperation(value = "查询车场对应调度屏信息列表")
	@Override
	public List<Dispatch_Screen> findScreenByYardId(Yard yard) {
		return yardMapper.findScreenByYardId(yard.getYard_id());
	}

	@ApiOperation(value = "新增车场信息")
	@Override
	public void addYard(Yard yard) {
		yard.setCreate_date(new Date());
		yard.setYard_id(yardMapper.getSequence("yard_code"));
		yard.setStatus(0);
		yardMapper.insertYard(yard);
	}
	
	@ApiOperation(value = "获取Sequence")
	@Override
	public Integer getSequence(String  code){
		return yardMapper.getSequence(code);
	}

	@ApiOperation(value = "获取线路")
	@Override
	public List<String> findLineById(Integer yard_id) {
		List<String> lineIds = yardMapper.findLineIds(yard_id);
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<lineIds.size();i++){
			if(i==(lineIds.size()-1)){
				sb.append(lineIds.get(i));
			}else{
			sb.append(lineIds.get(i));
			sb.append(",");
			}
		}
		String ids = sb.toString();
		return yardMapper.findLineById(ids);
	}

	
	@ApiOperation(value = "导出excle,通过ID获取车场信息集合")
	@Override
	public List<Yard> findYardListByIds(String yard_ids) {
		return yardMapper.findYardListByIds(yard_ids);
	}

	@ApiOperation(value = "导入excle,批量添加车场信息")
	@Override
	public void addYardList(List<Yard> list) {
		yardMapper.addYardList(list);
	}
	
	@ApiOperation(value = "查询车场对应线路信息列表")
	@Override
	public List<Line> findLinesByYardId(Yard yard) {
		return yardMapper.findLinesByYardId(yard.getYard_id());
	}

	@ApiOperation(value = "根据ID查询车场对象")
	@Override
	public Yard getYardById(Yard yard) {
		return yardMapper.getYardById(yard);
	}
}
