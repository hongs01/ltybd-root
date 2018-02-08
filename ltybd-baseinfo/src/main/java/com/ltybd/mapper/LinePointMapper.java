package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.Line;
import com.ltybd.entity.LinePoint;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月9日
 */
@Api(value="LinePointMapper", description = "线路辅助点Mapper")
public interface LinePointMapper extends MyMapper<LinePoint> {

	@ApiOperation(value = "根据线路ID查询辅助点对象")
	@Select("<script>"
			+ " select * from op_line_point"
			+ " where 1 = 1 "
        	+ " and line_id = #{line_id}"
        	+ " and direction = #{direction}"
			+ "</script>")
	List<LinePoint> findByLineId(LinePoint linePoint);

	@ApiOperation(value = "根据线路ID删除辅助点对象")
	@Delete("delete from op_line_point where line_id = #{line_id} ")
	int deleteByLineId(LinePoint linePoint);

	@ApiOperation(value = "插入多个辅助点对象")
	@Insert("<script>"
			+ "INSERT INTO op_line_point (line_id, longitude, latitude, direction)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.line_id},#{item.longitude},#{item.latitude},#{item.direction})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<LinePoint> list);
}
