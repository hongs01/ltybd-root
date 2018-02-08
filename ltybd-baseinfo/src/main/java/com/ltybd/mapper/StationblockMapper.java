package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Line;
import com.ltybd.entity.Stationblock;
import com.ltybd.entity.StationblockBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * StationblockMapper.java
 *
 * describe:
 * 
 * 2017年11月10日 下午6:11:59 created By chenq version 0.1
 *
 * 2017年11月10日 下午6:11:59 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="StationblockMapper", description = "站台信息Mapper")
public interface StationblockMapper extends MyMapper<Stationblock>{
	
	@ApiOperation(value="模糊查询站台信息对象列表")
	@Select("<script>"
			+ "select s.*,d.dict_name as station_type_name,"
			+ "d1.dict_name as area_name from op_Stationblock s "
			+ "left join dict_item d on d.dict_id=s.station_type "
			+ "left join dict_item d1 on d1.dict_id=s.district_id "
			+ " where 1=1"
			+ "<if test='bus_station_code != null '>"
			+ " and bus_station_code like '%${bus_station_code}%' "
			+ "</if>"
			+ "<if test='bus_station_name != null'>"
			+ " and bus_station_name like concat ('%',trim('${bus_station_name}'),'%') "
			+ "</if>"
			+ "<if test='station_type != null '>"
			+ " and station_type = #{station_type}"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<StationblockBean> findStationblockList(Stationblock Stationblock);
	
	@ApiOperation(value="模糊查询站台信息对象列表")
	@Select("<script>"
			+ "select s.*,d.dict_name as station_type_name,"
			+ "d1.dict_name as area_name from op_Stationblock s "
			+ "left join dict_item d on d.dict_id=s.station_type "
			+ "left join dict_item d1 on d1.dict_id=s.district_id "
			+ " where 1=1"
			+ " and bus_station_code = ${bus_station_code} "
			+ "</script>")
	public StationblockBean findByBusStationCode(Stationblock Stationblock);
	
	@ApiOperation(value="批量删除站台信息对象")
	@Delete("delete from op_Stationblock where bus_station_code in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
	@ApiOperation(value = "更新站台信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "UPDATE op_Stationblock "
			+ "<set>"
			+ "<if test = 'item.bus_station_name != null'>"
			+ " bus_station_name = #{item.bus_station_name},"
			+ "</if>"
			+ "<if test = 'item.station_type != null'>"
			+ " station_type = #{item.station_type},"
			+ "</if>"
			+ "<if test = 'item.status != null'>"
			+ " status = #{item.status},"
			+ "</if>"
			+ "<if test = 'item.district_id != null'>"
			+ " district_id = #{item.district_id},"
			+ "</if>"
			+ "<if test = 'item.orientation != null'>"
			+ " orientation = #{item.orientation},"
			+ "</if>"
			+ "<if test = 'item.enter_azimuth != null'>"
			+ " enter_azimuth = #{item.enter_azimuth},"
			+ "</if>"
			+ "<if test = 'item.leave_azimuth != null'>"
			+ " leave_azimuth = #{item.leave_azimuth},"
			+ "</if>"
			+ "<if test = 'item.longitude != null'>"
			+ " longitude = #{item.longitude},"
			+ "</if>"
			+ "<if test = 'item.latiude != null'>"
			+ " latiude = #{item.latiude},"
			+ "</if>"
			+ "<if test = 'item.enter_longitude != null'>"
			+ " enter_longitude = #{item.enter_longitude},"
			+ "</if>"
			+ "<if test = 'item.enter_latitude != null'>"
			+ " enter_latitude = #{item.enter_latitude},"
			+ "</if>"
			+ "<if test = 'item.leave_longitude != null'>"
			+ " leave_longitude = #{item.leave_longitude},"
			+ "</if>"
			+ "<if test = 'item.leave_latitude != null'>"
			+ " leave_latitude = #{item.leave_latitude},"
			+ "</if>"
			+ "<if test = 'item.remark != null'>"
			+ " remark = #{item.remark},"
			+ "</if>"
			+ "<if test = 'item.create_time != null'>"
			+ " create_time = #{item.create_time},"
			+ "</if>"
			+ "<if test = 'item.last_modified_time != null'>"
			+ " last_modified_time= #{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " WHERE 1=1"
			+ " AND bus_station_code = #{item.bus_station_code}"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Stationblock> list);
	
	@ApiOperation(value = "新增站台信息")
	@Insert("<script>"
			+ "INSERT INTO op_Stationblock (bus_station_code, bus_station_name, station_type, status, district_id, orientation, enter_azimuth, leave_azimuth, longitude, latiude, enter_longitude, enter_latitude, leave_longitude, leave_latitude, remark, create_time, last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('Stationblock_code'),#{item.bus_station_name},#{item.station_type},#{item.status},#{item.district_id},#{item.orientation},#{item.enter_azimuth},#{item.leave_azimuth},#{item.longitude},#{item.latiude},#{item.enter_longitude},#{item.enter_latitude},#{item.leave_longitude},#{item.leave_latitude},#{item.remark},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Stationblock> list);

	@ApiOperation(value = "查询经过站台的线路 ")
	@Select("<script>"
			+ "select * from op_line  ol "
			+ " left join op_line_station ols "
			+ " on ol.line_id=ols.line_id "
			+ " where ols.bus_station_code = ${bus_station_code}"
			+ "</script>")
	public List<Line> findLinesByBusStationCode(Stationblock stationblock);

	@ApiOperation(value = "批量查找站台信息")
	@Select("<script>"
			+ "select * from op_Stationblock  "
			+ " where 1=1 "
			+ " and bus_station_code in (${ids})"
			+ "</script>")
	public List<StationblockBean> batchfindListObj(String ids);
}
