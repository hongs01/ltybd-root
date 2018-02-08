package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Classes;
import com.ltybd.entity.Shift;
import com.ltybd.entity.ShiftClasses;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ShiftMapper", description = "班制Mapper")
public interface ShiftMapper extends MyMapper<Shift>{
	
	@ApiOperation(value="查询班制列表")
	@Select("<script>"
			+ "select s.* from op_shift s "
			+ "where 1=1"
			+ "<if test='shift_name != null'>"
			+ " and s.shift_name like '%${shift_name}%'"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and s.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<Shift> findShiftList(Shift shift);
	
	@ApiOperation(value="查询班制对象")
	@Select("<script>"
			+ "select s.* from op_shift s "
			+ "where 1=1 and s.shift_id = #{shift_id}"
			+ "</script>")
	public Shift findById(Integer shift_id);
	
	@ApiOperation(value="删除班制信息对象）")
	@Delete("delete from op_shift where shift_id in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
	@ApiOperation(value="插入班制与班次关系对象）")
	@Insert("<script>"
			+ "INSERT INTO op_shift_classes (shift_id, classes_id, create_time) VALUES "
            +"(#{shift_id},#{classes_id},#{create_time})" 
			+ "</script>")
	public int insertShiftClasses(ShiftClasses shiftClasses);
	
	@ApiOperation(value="更新班制与班次关系对象）")
	@Update("<script>"
			+ "Update op_shift_classes set classes_id=#{classes_id} where seq_id=#{seq_id} "
			+ "</script>")
	public int updateShiftClasses(Integer classes_id,Integer seq_id);
	
	
	@ApiOperation(value="查询班制下的班次列表")
	@Select("<script>"
			+ "select c.* from op_shift_classes s left join op_classes c on s.classes_id=c.classes_id "
			+ "where 1=1 and s.shift_id = #{shift_id}"
			+ "</script>")
	public List<Classes> selectShiftClassesList(Integer shift_id);
	
	@ApiOperation(value="查询班制与班次对应对象")
	@Select("<script>"
			+ "select s.* from op_shift_classes s  "
			+ "where 1=1 and s.seq_id = #{seq_id}"
			+ "</script>")
	public ShiftClasses selectShiftClasse(Integer seq_id);
}
