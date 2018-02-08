package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.DateType;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * DateTypeMapper.java
 *
 * describe:
 * 
 * 2017年11月6日 下午5:38:33 created By chenq version 0.1
 *
 * 2017年11月6日 下午5:38:33 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="DateTypeMapper", description = "日期类型Mapper")
public interface DateTypeMapper extends MyMapper<DateType> {

	@ApiOperation(value="模糊查询日期类型信息对象列表")
	@Select("<script>"
			+ "select c.* from op_date_type c "
			+ "where 1=1"
			+ "<if test='date_type_id != null'>"
			+ " and c.date_type_id like '%${date_type_id}%'"
			+ "</if>"
			+ "<if test='type_name != null '>"
			+ " and c.type_name like concat ('%',trim('${type_name}'),'%') "
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and c.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<DateType> findDateTypeList(DateType dateType);
	
	@ApiOperation(value="批量删除日期类型信息对象")
	@Delete("delete from op_date_type where date_type_id in (${ids})") 
	public int deleteDateTypeItems(@Param("ids")String  ids);
	
	@ApiOperation(value="查询日期类型信息对象")
	@Select("<script>"
			+ "select c.* from op_date_type c "
			+ " where 1=1"
			+ " and c.date_type_id = ${date_type_id}"
			+ "</script>")
	public DateType findByDateTypeId(DateType dateType);

	@ApiOperation(value="批量查询日期类型信息对象")
	@Select("<script>"
			+ "select * from op_date_type  "
			+ " where 1=1 "
			+ " and date_type_id in (${ids})"
			+ "</script>")
	public List<DateType> batchfindListObj(@Param("ids")String ids);

}
