package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.Classes;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ClassesMapper", description = "班次Mapper")
public interface ClassesMapper extends MyMapper<Classes> {
	@ApiOperation(value="模糊查询班次列表")
	@Select("<script>"
			+ "select c.* from op_classes c "
			+ "where 1=1"
			+ "<if test='classes_name != null'>"
			+ " and c.classes_name like '%${classes_name}%'"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and c.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<Classes> findClassesList(Classes classes);
	
	@ApiOperation(value="查询班次对象")
	@Select("<script>"
			+ "select c.* from op_classes c "
			+ "where 1=1 and c.classes_id = #{classes_id}"
			+ "</script>")
	public Classes findById(Integer classes_id);
	
	@ApiOperation(value="删除公司信息对象）")
	@Delete("delete from op_classes where classes_id in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
}
