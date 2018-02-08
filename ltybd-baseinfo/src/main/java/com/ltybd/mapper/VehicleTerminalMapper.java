package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.VehicleTerminal;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * VehicleTerminalMapper.java
 *
 * describe:车辆设备信息Mapper
 * 
 * 2017年10月13日 下午4:06:43 created By Yancz version 0.1
 *
 * 2017年10月13日 下午4:06:43 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehicleTerminalMapper", description = "车辆设备信息Mapper")
public interface VehicleTerminalMapper extends MyMapper<VehicleTerminal> {

	@ApiOperation(value = "修改车辆设备信息")
	@Update("<script>"
			+ "update op_vehicle_terminal "
			+ "<set>"
			+ "<if test='status != null'>"
			+ "status=#{status},"
			+ "</if>"
			+ "<if test='last_modified_time != null'>"
			+ "last_modified_time=#{last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='vehicle_id != null'>"
			+ "	and vehicle_id=#{vehicle_id}"
			+ "</if>"
			+ "<if test='terminal_id != null'>"
			+ "and terminal_id=#{terminal_id} "
			+ "</if>"
			+ "</script>")
	public int update(VehicleTerminal vehicleTerminal);
	
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_vehicle_terminal "
			+ "<set>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='item.vehicle_id != null'>"
			+ "	and vehicle_id=#{item.vehicle_id}"
			+ "</if>"
			+ "<if test='item.terminal_id != null'>"
			+ "and terminal_id=#{item.terminal_id} "
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<VehicleTerminal> list);
	
	@ApiOperation(value = "修改车辆设备信息")
	@Insert("<script>"
			+ "insert into op_vehicle_terminal(vehicle_id,terminal_id,status,create_time,last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.vehicle_id},#{item.terminal_id},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<VehicleTerminal> list);
	
}
