package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.LineEmployee;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LineEmployeeMapper.java
 *
 * describe:线路员工对应信息
 * 
 * 2017年10月12日 下午2:39:00 created By Yancz version 0.1
 *
 * 2017年10月12日 下午2:39:00 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "LineEmployeeMapper", description = "线路员工对应信息Mapper")
public interface LineEmployeeMapper extends MyMapper<LineEmployee> {

	@ApiOperation(value = "修改线路员工对应信息")
	@Update("<script>"
			+ "update op_line_employee "
			+ "<set>"
			+ "<if test='status != null'>"
			+ "status = #{status},"
			+ "</if>"
			+ "<if test='last_modified_time != null'>"
			+ "last_modified_time=#{last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='line_id != null'>"
			+ "	and line_id=#{line_id}"
			+ "</if>"
			+ "<if test='employee_id != null'>"
			+ "and employee_id=#{employee_id} "
			+ "</if>"
			+ "</script>")
	public int update(LineEmployee lineEmployee);
	
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_line_employee "
			+ "<set>"
			+ "<if test='item.status != null'>"
			+ "status = #{item.status},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='item.line_id != null'>"
			+ "	and line_id=#{item.line_id}"
			+ "</if>"
			+ "<if test='item.employee_id != null'>"
			+ "and employee_id=#{item.employee_id} "
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<LineEmployee> list);
	
	@ApiOperation(value = "修改车辆设备信息")
	@Insert("<script>"
			+ "INSERT INTO op_line_employee (line_id, employee_id, status, create_time, last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.line_id},#{item.employee_id},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<LineEmployee> list);

	@ApiOperation(value = "根据线路ID查询员工对应信息对象")
	@Select("<script>"
			+ " select * from op_line_employee"
			+ " where 1 = 1 "
        	+ " and line_id = #{line_id}"
        	+ " and status = #{status} "
			+ "</script>")
	public List<LineEmployee> findbyLineId(LineEmployee lineEmployee);
	
}
