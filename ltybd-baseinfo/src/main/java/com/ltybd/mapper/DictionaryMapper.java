package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Dictionary;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="DictionaryMapper", description = "数据字典Mapper")
public interface DictionaryMapper extends MyMapper<Dictionary>{
	
	@ApiOperation(value="模糊查询字典信息对象列表")
	@Select("<script>"
			+ "select * from dict_item where 1=1"
			+ "<if test='dict_id != null '>"
			+ " and dict_id = ${dict_id} "
			+ "</if>"
			+ "<if test='dict_name != null'>"
			+ " and dict_name like '%${dict_name}%'"
			+ "</if>"
			+ "<if test='group_id != null '>"
			+ " and group_id = #{group_id}"
			+ "</if>"
			+ "</script>")
	public List<Dictionary> findListObj(Dictionary dic);
	
	@ApiOperation(value="批量删除字典对象")
	@Delete("delete from dict_item where dict_id in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
	@ApiOperation(value="批量修改字典对象")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update dict_item "
			+ "<set>"
			+ "<if test='item.group_id != null'>"
			+ "group_id=#{item.group_id},"
			+ "</if>"
			+ "<if test='item.dict_name != null'>"
			+ "dict_name=#{item.dict_name},"
			+ "</if>"
			+ "</set>"
			+ " where 1=1 "
			+ "<if test='item.dict_id != null'>"
			+ "	and dict_id=#{item.dict_id}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Dictionary> list);
	
	@ApiOperation(value = "批量插入字典信息")
	@Insert("<script>"
			+ "insert into dict_item(dict_id,group_id,dict_name)"
			+ " values"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('dictionary_code'),#{item.group_id},#{item.dict_name})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Dictionary> list);
	
}
