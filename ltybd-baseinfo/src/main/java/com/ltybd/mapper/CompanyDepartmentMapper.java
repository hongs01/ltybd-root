package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.CompanyDepartment;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyDepartmentMapper.java
 *
 * describe:公司对应信息Mapper
 * 
 * 2017年10月17日 下午7:17:06 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:17:06 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "CompanyDepartmentMapper", description = "公司对应信息Mapper")
public interface CompanyDepartmentMapper extends MyMapper<CompanyDepartment>{
	@ApiOperation(value = "修改公司对应信息")
	@Update("<script>"
			+ "update op_company_department "
			+ "<set>"
			+ "<if test='status != null'>"
			+ "status = #{status},"
			+ "</if>"
			+ "<if test='last_modified_time != null'>"
			+ "last_modified_time=#{last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='company_id != null'>"
			+ "	and company_id=#{company_id}"
			+ "</if>"
			+ "<if test='department_id != null'>"
			+ "and department_id=#{department_id} "
			+ "</if>"
			+ "</script>")
	public int update(CompanyDepartment companyDepartment);
	
	@ApiOperation(value = "批量修改公司部门信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_company_department "
			+ "<set>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='item.company_id != null'>"
			+ "	and company_id=#{item.company_id}"
			+ "</if>"
			+ "<if test='item.department_id != null'>"
			+ "and department_id=#{item.department_id} "
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<CompanyDepartment> list);
	
	@ApiOperation(value = "批量增加公司部门信息")
	@Insert("<script>"
			+ "INSERT INTO op_company_department (company_id, department_id, status, create_time, last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.company_id},#{item.department_id},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<CompanyDepartment> list);
}
