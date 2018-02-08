package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Company;
import com.ltybd.entity.CompanyDepartment;
import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.LineBean;
import com.ltybd.entity.Yard;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="CompanyMapper", description = "车场Mapper")
public interface YardMapper extends MyMapper<Yard> {
	
	@ApiOperation(value="模糊查询车场信息对象列表")
	@Select("<script> select * from op_yard "
			+"where 1=1"
			+ "<if test='yard_name != null'>"
			+ " and yard_name like '%${yard_name}%'"
			+ "</if>"
			+"<if test='direction != null'>"
			+ " and direction = #{direction}"
			+ "</if>"
			+ "</script>")
	public List<Yard> findYardList(Yard yard);
	
	@ApiOperation(value="删除车场信息对象列表")
	@Delete("delete from op_yard where yard_id in (${ids})")
	public int deleteYardList(@Param("ids")String yard_ids);

	@ApiOperation(value="更新车场信息对象")
	@Update("<script> update op_yard "
			+"<set>"
			+"<if test='yard_name != null'>"
			+ "yard_name = #{yard_name},"
			+ "</if>"
			+"<if test='direction != null'>"
			+ "direction = #{direction},"
			+ "</if>"
			+"<if test='is_yard != null'>"
			+ "is_yard = #{is_yard},"
			+ "</if>"
			+"<if test='status != null'>"
			+ "status = #{status},"
			+ "</if>"
			+"</set>"
			+"where yard_id = #{yard_id}"
			+ "</script>")
	public int updateYard(Yard yard);

	@ApiOperation(value="查询车场对应线路信息列表")
	@Select("<script> "
			+"select * from op_line where line_id in (select line_id from op_yard_line yl where yl.yard_id = #{yard_id})"
			+ "</script>")
	public List<Line> findLinesByYardId(Integer yard_id);

	@ApiOperation(value="查询车场对应调度屏信息列表")
	@Select("<script> select * from op_dispatch_screen s where s.yard_id = #{yard_Id} </script>")
	public List<Dispatch_Screen> findScreenByYardId(Integer yard_Id);
	

	@ApiOperation(value = "根据车场ID查询车场对象")
	@Select("<script>"
			+ " select * from op_yard"
			+ " where 1 = 1 "
        	+ " and yard_id = #{yard_id}"
        	+ " and status = #{status} "
			+ "</script>")
	public Yard getYardById(Yard yard);

	@ApiOperation(value = "新增车场信息")
	@Insert("<script>"
			+ "insert into op_yard(yard_id,yard_name,direction,is_yard,create_date,status) values ( #{yard_id},#{yard_name},#{direction},#{is_yard},#{create_date},#{status})"
			+ "</script>")
	public int insertYard(Yard yard);

	
	@ApiOperation(value="查询线路Id")
	@Select("<script> "
			+"select line_id from op_yard_line where yard_id = #{yard_id}"
			+ "</script>")
	public List<String> findLineIds(Integer yard_id);
	
	@ApiOperation(value="查询线路")
	@Select("<script> "
			+"select line_name from op_line where line_id in (${ids})"
			+ "</script>")
	public List<String> findLineById(@Param("ids")String _ids);

	@ApiOperation(value="通过id查询车场信息集合")
	@Select("select * from op_yard where yard_id in (${ids})")
	public List<Yard> findYardListByIds(@Param("ids")String yard_ids);

	@ApiOperation(value = "批量增加车场信息")
	@Insert("<script>"
			+ "INSERT INTO op_yard (yard_id, yard_name, direction, is_yard,create_date, status)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(#{item.yard_id},#{item.yard_name},#{item.direction},#{item.is_yard},#{item.create_date},#{item.status})" 
        	+"</foreach>"
			+ "</script>")
	public void addYardList(@Param("list")List<Yard> list);
	
	/*@ApiOperation(value="查询线路")
	@Select("<script> "
			+"select * from op_line where line_id in (select line_id from op_yard_line yl where yard_id = #{yard_id})"
			+ "</script>")
	public List<Line> findLineById(Integer yard_id);*/
	
}
