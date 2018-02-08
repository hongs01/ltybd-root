package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.LineStation;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineStationMapper.java
 *
 * describe:线路站点信息Mapper
 * 
 * 2017年10月12日 下午2:45:26 created By Yancz version 0.1
 *
 * 2017年10月12日 下午2:45:26 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineStationMapper", description = "线路站点信息Mapper")
public interface LineStationMapper extends MyMapper<LineStation> {

	@ApiOperation(value = "修改线路站点信息")
	@Update("<script>"
			+ "update op_line_station "
			+ "<set>"
			+ "<if test='order_no != null'>"
			+ "order_no = #{order_no},"
			+ "</if>"
			+ "<if test='direction != null'>"
			+ "direction=#{direction},"
			+ "</if>"
			+ "<if test='station_type != null'>"
			+ "station_type=#{station_type},"
			+ "</if>"
			+ "<if test='status != null'>"
			+ "status=#{status},"
			+ "</if>"
			+ "<if test='last_modified_time != null'>"
			+ "last_modified_time=#{last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='line_id != null'>"
			+ "	and line_id=#{line_id}"
			+ "</if>"
			+ "<if test='direction != null'>"
			+ "and direction=#{direction} "
			+ "</if>"
			+ "<if test='bus_station_code != null'>"
			+ "and bus_station_code=#{bus_station_code}"
			+ "</if>"
			+ "</script>")
	public int update(LineStation lineStation);
	
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_line_station "
			+ "<set>"
			+ "<if test='item.order_no != null'>"
			+ "order_no = #{item.order_no},"
			+ "</if>"
			+ "<if test='item.direction != null'>"
			+ "direction=#{item.direction},"
			+ "</if>"
			+ "<if test='item.station_type != null'>"
			+ "station_type=#{item.station_type},"
			+ "</if>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='item.line_id != null'>"
			+ "	and line_id=#{item.line_id}"
			+ "</if>"
			+ "<if test='item.direction != null'>"
			+ "and direction=#{item.direction} "
			+ "</if>"
			+ "<if test='item.bus_station_code != null'>"
			+ "and bus_station_code=#{item.bus_station_code}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<LineStation> list);
	
	@ApiOperation(value = "修改车辆设备信息")
	@Insert("<script>"
			+ "INSERT INTO op_line_station (line_id, direction, bus_station_code, order_no, station_type, status, create_time, last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.line_id}, #{item.direction}, #{item.bus_station_code}, #{item.order_no}, #{item.station_type}, #{item.status}, #{item.create_time}, #{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<LineStation> list);

	@ApiOperation(value = "根据线路ID查询线路站点对象")
	@Select("<script>"
			+ " select * from op_line_station"
			+ " where 1 = 1 "
        	+ " and line_id = #{line_id}"
        	+ " and status = #{status} "
			+ "</script>")
	public List<LineStation> findByLineId(LineStation lineStation);
	
}
