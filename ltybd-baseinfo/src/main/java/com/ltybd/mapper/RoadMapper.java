package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.Road;
import com.ltybd.entity.Stationblock;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * RoadMapper.java
 *
 * describe:
 * 
 * 2017年11月9日 上午11:38:42 created By chenq version 0.1
 *
 * 2017年11月9日 上午11:38:42 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="RoadMapper", description = "道路Mapper")
public interface RoadMapper extends MyMapper<Road>{

	@ApiOperation(value="查询道路信息对象")
	@Select("<script>"
			+ "select c.* from op_road c "
			+ " where 1=1"
			+ " and c.road_id = ${road_id}"
			+ "</script>")
	public Road findByRoadId(Road road);

	@ApiOperation(value="批量删除道路信息对象")
	@Delete("delete from op_road where road_id in (${ids})") 
	public int deleteRoadItems(@Param("ids")String ids);

	@ApiOperation(value="模糊查询道路信息对象列表")
	@Select("<script>"
			+ "select c.* from op_road c "
			+ "where 1=1"
			+ "<if test='road_id != null'>"
			+ " and c.road_id like '%${road_id}%'"
			+ "</if>"
			+ "<if test='road_name != null '>"
			+ " and c.road_name like concat ('%',trim('${road_name}'),'%') "
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and c.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<Road> findRoadList(Road road);

	@ApiOperation(value="批量查询道路信息对象")
	@Select("<script>"
			+ "select * from op_road  "
			+ " where 1=1 "
			+ " and road_id in (${ids})"
			+ "</script>")
	public List<Road> batchfindListObj(@Param("ids")String ids);

	@ApiOperation(value="通过道路id查询站台信息")
	@Select("<script>"
			+ "select c.* from op_stationblock c "
			+ " where 1=1"
			+ " and c.road_id = ${road_id}"
			+ "</script>")
	public List<Stationblock> findStationsByRoadId(Road road);

}
