package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Line;
import com.ltybd.entity.LineBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineMapper.java
 *
 * describe:线路Mapper
 * 
 * 2017年10月12日 上午11:40:17 created By Yancz version 0.1
 *
 * 2017年10月12日 上午11:40:17 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineMapper", description = "线路Mapper")
public interface LineMapper extends MyMapper<Line> {
	@ApiOperation(value = "批量删除线路对象")
	@Delete("delete from op_line where line_id in (${ids})")
	public int deleteItems(@Param("ids") String ids);
	
	@ApiOperation(value = "查詢线路集合")
	@Select("<script>"
			+ "select l.*,d.dict_name as line_type_name,"
			+ "d1.dict_name as loop_type_name,"
			+ "d2.dict_name as class_system_name,"
			+ "d3.dict_name as shunting_type_name,"
			+ "(select count(1) from op_busResource b where l.line_id = b.line_id) as vechicleNum,"
     		+ "(select count(1) FROM op_employee e  LEFT join op_line_employee le ON e.employee_id = le.employee_id where le.line_id = l.line_id and e.employee_type = 0 and e.status = 0) as driverNum ,"
			+ "(select count(1) FROM op_employee e  LEFT join op_line_employee le ON e.employee_id = le.employee_id where le.line_id = l.line_id and e.employee_type = 1 and e.status = 0) as conductorNum ,"	
			+ "d4.dict_name as schedule_type_name from op_line l "
			+ "left join dict_item d on d.dict_id=l.line_type "
			+ "left join dict_item d1 on d1.dict_id=l.loop_type "
			+ "left join dict_item d2 on d2.dict_id=l.class_system "
			+ "left join dict_item d3 on d3.dict_id=l.shunting_type "
			+ "left join dict_item d4 on d4.dict_id=l.schedule_type "
			+ "where 1=1 "
			+ "<if test='line_id != null '>"
			+ " and line_id=#{line_id}"
			+ "</if>"
			+ "<if test='line_name != null'>"
			+ " and line_name like '%${line_name}%'"
			+ "</if>"
			+ "<if test='line_type != null '>"
			+ " and line_type=#{line_type}"
			+ "</if>"
			+ "<if test='loop_type != null '>"
			+ " and loop_type=#{loop_type}"
			+ "</if>"
			+ "<if test='class_system != null '>"
			+ " and class_system=#{class_system}"
			+ "</if>"
			+ "<if test='shunting_type != null '>"
			+ " and shunting_type=#{shunting_type}"
			+ "</if>"
			+ "<if test='group_id != null '>"
			+ " and group_id=#{group_id}"
			+ "</if>"
			+ "<if test='company_id != null '>"
			+ " and company_id=#{department_id}"
			+ "</if>"
			+ "<if test='department_id != null '>"
			+ " and department_id=#{department_id}"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and l.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<LineBean> findListObj(Line line);
	
	@ApiOperation(value = "查詢线路对象")
	@Select("<script>"
			+ "select l.*,d.dict_name as line_type_name,"
			+ "d1.dict_name as loop_type_name,"
			+ "d2.dict_name as class_system_name,"
			+ "d3.dict_name as shunting_type_name,"
			+ "(select count(1) from op_busResource b where l.line_id = b.line_id) as vechicleNum,"
     		+ "(select count(1) FROM op_employee e  LEFT join op_line_employee le ON e.employee_id = le.employee_id where le.line_id = l.line_id and e.employee_type = 0 and e.status = 0) as driverNum ,"
			+ "(select count(1) FROM op_employee e  LEFT join op_line_employee le ON e.employee_id = le.employee_id where le.line_id = l.line_id and e.employee_type = 1 and e.status = 0) as conductorNum ,"		
			+ "d4.dict_name as schedule_type_name from op_line l "
			+ "left join dict_item d on d.dict_id=l.line_type "
			+ "left join dict_item d1 on d1.dict_id=l.loop_type "
			+ "left join dict_item d2 on d2.dict_id=l.class_system "
			+ "left join dict_item d3 on d3.dict_id=l.shunting_type "
			+ "left join dict_item d4 on d4.dict_id=l.schedule_type "
			+ "where 1=1 "
			+ " and l.line_id=${line_id} "
			+ "</script>")
	public LineBean findByLineId(Line line);
	
	@ApiOperation(value = "批量更新线路对象")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_line "
			+ "<set>"
			+ "<if test='item.line_id != null'>"
			+ "line_id=#{item.line_id},"
			+ "</if>"
			+ "<if test='item.line_code != null'>"
			+ "line_code=#{item.line_code},"
			+ "</if>"
			+ "<if test='item.line_name != null'>"
			+ "line_name=#{item.line_name},"
			+ "</if>"
			+ "<if test='item.line_type != null'>"
			+ "line_type=#{item.line_type},"
			+ "</if>"
			+ "<if test='item.loop_type != null'>"
			+ "loop_type=#{item.loop_type},"
			+ "</if>"
			+ "<if test='item.class_system != null'>"
			+ "class_system=#{item.class_system},"
			+ "</if>"
			+ "<if test='item.shunting_type != null'>"
			+ "shunting_type=#{item.shunting_type},"
			+ "</if>"
			+ "<if test='item.schedule_type != null'>"
			+ "schedule_type=#{item.schedule_type},"
			+ "</if>"
			+ "<if test='item.group_id != null'>"
			+ "group_id=#{item.group_id},"
			+ "</if>"
			+ "<if test='item.company_id != null'>"
			+ "company_id=#{item.company_id},"
			+ "</if>"
			+ "<if test='item.department_id != null'>"
			+ "department_id=#{item.department_id},"
			+ "</if>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.first_uplink_time != null'>"
			+ "first_uplink_time=#{item.first_uplink_time},"
			+ "</if>"
			+ "<if test='item.last_uplink_time != null'>"
			+ "last_uplink_time=#{item.last_uplink_time},"
			+ "</if>"
			+ "<if test='item.first_downlink_time != null'>"
			+ "first_downlink_time=#{item.first_downlink_time},"
			+ "</if>"
			+ "<if test='item.last_downlink_time != null'>"
			+ "last_downlink_time=#{item.last_downlink_time},"
			+ "</if>"		
			+ "<if test='item.mileage != null'>"
			+ "mileage=#{item.mileage},"
			+ "</if>"			
			+ "<if test='item.rotation_cycle != null'>"
			+ "rotation_cycle=#{item.rotation_cycle},"
			+ "</if>"
			+ "<if test='item.is_big_rotation != null'>"
			+ "is_big_rotation=#{item.is_big_rotation},"
			+ "</if>"
			+ "<if test='item.last_rotation_date != null'>"
			+ "last_rotation_date=#{item.last_rotation_date},"
			+ "</if>"
			+ "<if test='item.is_artificial_ticket != null'>"
			+ "is_artificial_ticket=#{item.is_artificial_ticket},"
			+ "</if>"
			+ "<if test='item.ticket_price != null'>"
			+ "ticket_price=#{item.ticket_price},"
			+ "</if>"
			+ "<if test='item.speed != null'>"
			+ "speed=#{item.speed},"
			+ "</if>"
			+ "<if test='item.bus_type != null'>"
			+ "bus_type=#{item.bus_type},"
			+ "</if>"
			+ "<if test='item.width != null'>"
			+ "width=#{item.width},"
			+ "</if>"
			+ "<if test='item.stationColor != null'>"
			+ "stationColor=#{item.stationColor},"
			+ "</if>"
			+ "<if test='item.stationIcon != null'>"
			+ "stationIcon=#{item.stationIcon},"
			+ "</if>"
			+ "<if test='item.isShowStation != null'>"
			+ "isShowStation=#{item.isShowStation},"
			+ "</if>"
			+ "<if test='item.isShowStationName != null'>"
			+ "isShowStationName=#{item.isShowStationName},"
			+ "</if>"
			+ "<if test='item.stationNameColor != null'>"
			+ "stationNameColor=#{item.stationNameColor},"
			+ "</if>"
			+ "<if test='item.stationNamefont != null'>"
			+ "stationNamefont=#{item.stationNamefont},"
			+ "</if>"
			+ "<if test='item.isShowPoint != null'>"
			+ "isShowPoint=#{item.isShowPoint},"
			+ "</if>"
			+ "<if test='item.color != null'>"
			+ "color=#{item.color},"
			+ "</if>"
			+ "<if test='item.parent_line_id != null'>"
			+ "parent_line_id=#{item.parent_line_id},"
			+ "</if>"
			+ "<if test='item.line_open_time != null'>"
			+ "line_open_time=#{item.line_open_time},"
			+ "</if>"
			+ "<if test='item.line_off_time != null'>"
			+ "line_off_time=#{item.line_off_time},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ "where 1=1"
			+ "<if test='item.line_id != null'>"
			+ " and line_id=#{item.line_id}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Line> list);
	
	@ApiOperation(value = "批量插入线路对象")
	@Insert("<script>"
			+ "INSERT INTO op_line (line_code, line_name, line_type, loop_type, class_system, shunting_type, schedule_type, group_id, company_id, department_id, status, first_uplink_time, last_uplink_time, first_downlink_time, last_downlink_time, mileage,parent_line_id, line_open_time, line_off_time, create_time, last_modified_time,rotation_cycle,is_big_rotation,last_rotation_date,is_artificial_ticket,ticket_price,speed,bus_type,width,stationColor,stationIcon,isShowStation,isShowStationName,stationNameColor,stationNamefont,isShowPoint,color)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.line_code},#{item.line_name},#{item.line_type},#{item.loop_type},#{item.class_system},#{item.shunting_type},#{item.schedule_type},#{item.group_id},#{item.company_id},#{item.department_id},#{item.status},#{item.first_uplink_time},#{item.last_uplink_time},#{item.first_downlink_time},#{item.last_downlink_time},#{item.mileage},#{item.parent_line_id},#{item.line_open_time},#{item.line_off_time},#{item.create_time},#{item.last_modified_time},#{item.rotation_cycle},#{item.is_big_rotation},#{item.last_rotation_date},#{item.is_artificial_ticket},#{item.ticket_price},#{item.speed},#{item.bus_type},#{item.width},#{item.stationColor},#{item.stationIcon},#{item.isShowStation},#{item.isShowStationName},#{item.stationNameColor},#{item.stationNamefont},#{item.isShowPoint},#{item.color})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Line> list);

	@ApiOperation(value = "根据线路ID获得人车数量")
	@Select("<script>"
			+ "select count(b.vechicle_id) as vechicleNum ,"
			+ "sum(case when e.employee_type=0 then 1 else 0 end) as driverNum ,"
			+ "sum(case when e.employee_type=1 then 1 else 0 end) as conductorNum "		
			+ " from "
			+ "op_line l "
			+ "left join op_line_employee le on le.line_id=l.line_id "
			+ "left join op_employee e on e.employee_id=le.employee_id "
			+ "left join op_busResource b on b.line_id=l.line_id "
			+ "where 1=1 "
			+ " and l.line_id=${line_id}"
			+ " and l.status=${status}"
			+ "</script>")
	public LineBean findRateByLineId(Line line);

	@ApiOperation(value = "查询线路所有支线")
	@Select("<script>"
			+ "select line_id from op_line"
			+ " where 1=1 "
			+ " and parent_line_id=${parent_line_id}"
			+ " and status=${status}"
			+ "</script>")
	public List<LineBean> findBranchById(Line line);

	@ApiOperation(value="批量查询线路对象")
	@Select("<script>"
			+ "select * from op_line "
			+ " where 1=1"
			+ " and line_id in (${ids})"
			+ "</script>")
	public List<LineBean> batchfindListObj(@Param("ids") String ids);

	@ApiOperation(value = "根据线路编号查询线路")
	@Select("<script>"
			+ "select * from op_line"
			+ " where 1=1 "
			+ " and line_code=${line_code}"
			+ " and status=${status}"
			+ "</script>")
	public LineBean findByLineCode(Line line);

}
