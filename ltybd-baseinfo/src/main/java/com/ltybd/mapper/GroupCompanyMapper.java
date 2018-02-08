package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.GroupCompany;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 
 * GroupCompanyMapper.java
 *
 * describe:集团公司对应信息Mapper
 * 
 * 2017年10月17日 下午4:12:39 created By Yancz version 0.1
 *
 * 2017年10月17日 下午4:12:39 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "GroupCompanyMapper", description = "集团公司对应信息Mapper")
public interface GroupCompanyMapper extends MyMapper<GroupCompany> {

	@ApiOperation(value = "修改集团公司对应信息")
	@Update("<script>"
			+ "update op_group_company"
			+ "<set>"
			+ "<if test='status != null'>"
			+ "status=#{status},"
			+ "</if>"
			+ "<if test='last_modified_time != null'>"
			+ "last_modified_time=#{last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ "where 1=1"
			+ "<if test='group_id !=null '>"
			+ " and group_id = #{group_id}"
			+ "</if>"
			+ "<if test='company_id !=null '>"
			+ " and company_id = #{company_id}"
			+ "</if>"
			+ "</script>")
	public int update(GroupCompany groupCompany);
	
	@ApiOperation(value = "批量修改集团公司对应信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_group_company"
			+ "<set>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "</set>"
			+ "where 1=1"
			+ "<if test='item.group_id !=null '>"
			+ " and group_id = #{item.group_id}"
			+ "</if>"
			+ "<if test='item.company_id !=null '>"
			+ " and company_id = #{item.company_id}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<GroupCompany> list);
	
	@ApiOperation(value = "批量新增集团公司对应信息")
	@Insert("<script>"
			+ "insert into op_group_company(group_id,company_id,status,create_time,last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.group_id},#{item.company_id},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<GroupCompany> list);
	
}
