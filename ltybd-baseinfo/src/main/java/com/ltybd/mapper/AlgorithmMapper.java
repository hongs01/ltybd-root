package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.Algorithm;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="AlgorithmMapper", description = "轮趟Mapper")
public interface AlgorithmMapper extends MyMapper<Algorithm>{
	@ApiOperation(value = "查詢轮趟集合")
	@Select("<script>"
			+ "select * from op_algorithm"
			+ " where 1=1"
			+ "<if test='algorithm_name != null'>"
			+ "	and algorithm_name like '%${algorithm_name}%'"
			+ "</if>"
			+ "<if test='algorithm_id != null'>"
			+ "	and algorithm_id=#{algorithm_id}"
			+ "</if>"
			+ "<if test='status != null'>"
			+ "	and status=#{status}"
			+ "</if>"
			+ "</script>")
	List<Algorithm> findListObj(Algorithm algorithm);
	
	@ApiOperation(value="查询轮趟对象")
	@Select("<script>"
			+ "select * from op_algorithm "
			+ " where 1=1"
			+ " and algorithm_id = ${algorithm_id}"
			+ "</script>")
	Algorithm findByAlgorithmId(Algorithm algorithm);

	@ApiOperation(value = "批量删除轮趟")
	@Delete("delete from op_algorithm where algorithm_id in (${ids})")
	int deleteItems(@Param("ids") String ids);

	@ApiOperation(value="批量查询轮趟对象")
	@Select("<script>"
			+ "select * from op_algorithm "
			+ " where 1=1"
			+ " and algorithm_id in (${ids})"
			+ "</script>")
	List<Algorithm> batchfindListObj(@Param("ids") String ids);


}
