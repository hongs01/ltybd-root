package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.District;
import com.ltybd.entity.Road;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DistrictMapper.java
 *
 * describe:
 * 
 * 2017年11月8日 下午2:37:44 created By chenq version 0.1
 *
 * 2017年11月8日 下午2:37:44 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="DistrictMapper", description = "区域Mapper")
public interface DistrictMapper extends MyMapper<District> {

	@ApiOperation(value="查询区域信息对象")
	@Select("<script>"
			+ "select c.* from op_district c "
			+ " where 1=1"
			+ " and c.district_id = ${district_id}"
			+ "</script>")
	public District findDistrictById(District district);

	@ApiOperation(value="批量删除区域对象")
	@Delete("delete from op_district where district_id in (${ids})") 
	public int deleteDistrictItems(@Param("ids")String ids);

	@ApiOperation(value="查询区域信息集合")
	@Select("<script>"
			+ "select c.* from op_district c "
			+ "where 1=1"
			+ "<if test='district_id != null'>"
			+ " and c.district_id like '%${district_id}%'"
			+ "</if>"
			+ "<if test='district_name != null '>"
			+ " and c.district_name like concat ('%',trim('${district_name}'),'%') "
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and c.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<District> findDistrictList(District district);

	@ApiOperation(value="批量查询区域对象")
	@Select("<script>"
			+ "select * from op_district  "
			+ " where 1=1 "
			+ " and district_id in (${ids})"
			+ "</script>")
	public List<District> batchfindListObj(@Param("ids") String ids);

	@ApiOperation(value="通过区域id查询道路信息")
	@Select("<script>"
			+ "select c.* from op_road c "
			+ " where 1=1"
			+ " and c.district_id = ${district_id}"
			+ "</script>")
	public List<Road> findRoadsByDistrictId(District district);

}
