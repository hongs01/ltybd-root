package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.BusGroup;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="BusGroupMapper", description = "班组Mapper")
public interface BusGroupMapper extends MyMapper<BusGroup> {
	
	/*@ApiOperation(value="查询班组列表")
	@Select("<script></script>")
	public List<BusGroup> selectList(BusGroup busGroup);*/

	@ApiOperation(value="查询班组对象")
	@Select("<script>"
			+ "select g.*,a.algorithm_name as bus_driver_algorithm_name,a1.algorithm_name as bus_algorithm_name,l.line_name,s.shift_name  from op_bus_group g "
			+ "left join (select * from op_algorithm where type=2 ) a on a.algorithm_id=g.bus_driver_algorithm_id "
			+ "left join (select * from op_algorithm where type=1 ) a1 on a1.algorithm_id=g.bus_algorithm_id "
			+ "left join op_line l on l.line_id=g.line_id "
			+ "left join op_shift s on s.shift_id=g.shift_id where g.group_id=#{group_id} "
			+ "</script>")
	public BusGroup findById(Integer group_id);
	
}
