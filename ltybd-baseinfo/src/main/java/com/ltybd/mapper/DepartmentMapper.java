package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Department;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * DepartmentMapper.java
 *
 * describe:部门信息Mapper
 * 
 * 2017年10月17日 下午5:39:55 created By Yancz version 0.1
 *
 * 2017年10月17日 下午5:39:55 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "DepartmentMapper", description = "部门信息Mapper")
public interface DepartmentMapper extends MyMapper<Department> {

	@ApiOperation(value = "查詢部门信息集合")
	@Select("<script>"
			+ "select * from op_department"
			+ " where 1=1"
			+ "<if test='department_name != null'>"
			+ "	and department_name like '%${department_name}%'"
			+ "</if>"
			+ "<if test='department_id != null'>"
			+ "	and department_id=#{department_id}"
			+ "</if>"
			+ "<if test='status != null'>"
			+ "	and status=#{status}"
			+ "</if>"
			+ "</script>")
	public List<Department> findListObj(Department department);
	
	@ApiOperation(value = "批量删除部门信息")
	@Delete("delete from op_department where department_id in (${ids})")
	public int deleteItems(@Param("ids") String ids);
	
	@ApiOperation(value = "批量修改部门信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_department "
			+ "<set>"
			+ "<if test='item.department_name != null'>"
			+ "department_name=#{item.department_name},"
			+ "</if>"
			+ "<if test='item.remark != null'>"
			+ "remark=#{item.remark},"
			+ "</if>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='item.department_id != null'>"
			+ "	and department_id=#{item.department_id}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Department> list);
	
	@ApiOperation(value = "批量新增部门信息")
	@Insert("<script>"
			+ "INSERT INTO op_department (department_id, department_name, remark, status, create_time, last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('department_code'),#{item.department_name},#{item.remark},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Department> list);
	
}
