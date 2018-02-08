package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Group;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * GroupMapper.java
 *
 * describe:集团信息Mapper
 * 
 * 2017年10月17日 下午2:36:25 created By Yancz version 0.1
 *
 * 2017年10月17日 下午2:36:25 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "GroupMapper", description = "集团信息Mapper")
public interface GroupMapper extends MyMapper<Group> {

	@ApiOperation(value = "查詢集团信息集合")
	@Select("<script>"
			+ "select * from op_group"
			+ " where 1=1"
			+ "<if test='group_name != null'>"
			+ "	and group_name like '%${group_name}%'"
			+ "</if>"
			+ "<if test='group_id != null'>"
			+ "	and group_id=#{group_id}"
			+ "</if>"
			+ "<if test='status != null'>"
			+ "	and status=#{status}"
			+ "</if>"
			+ "</script>")
	public List<Group> findListObj(Group group);
	
	@ApiOperation(value = "批量删除集团信息")
	@Delete("delete from op_group where group_id in (${ids})")
	public int deleteItems(@Param("ids") String ids);
	
	@ApiOperation(value = "批量修改集团信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_group "
			+ "<set>"
			+ "<if test='item.group_name != null'>"
			+ "group_name=#{item.group_name},"
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
			+ "<if test='item.group_id != null'>"
			+ "	and group_id=#{item.group_id}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Group> list);
	
	@ApiOperation(value = "批量新增集团信息")
	@Insert("<script>"
			+ "INSERT INTO op_group (group_id, group_name, remark, status, create_time, last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('group_code'),#{item.group_name},#{item.remark},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Group> list);
	
}
