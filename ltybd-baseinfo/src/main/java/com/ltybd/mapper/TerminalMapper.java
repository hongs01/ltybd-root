package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Terminal;
import com.ltybd.entity.TerminalBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "TerminalMapper", description = "设备Mapper")
public interface TerminalMapper extends MyMapper<Terminal>{
	@ApiOperation(value="模糊查询设备信息对象列表")
	@Select("<script>"
			+ "select t.*,d.dict_name as terminal_type_name from op_terminal t "
			+ "left join dict_item d on d.dict_id=t.terminal_type "
			+ "where 1=1"
			+ "<if test='terminal_id != null'>"
			+ " and terminal_id like '%${terminal_id}%'"
			+ "</if>"
			+ "<if test='terminal_type != null '>"
			+ " and terminal_type = #{terminal_type}"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and status = #{status}"
			+ "</if>"
			+ "<if test='terminal_code != null '>"
			+ " and terminal_code like '%${terminal_code}%'"
			+ "</if>"
			+ "</script>")
	public List<TerminalBean> findListObj(Terminal terminal);
	
	@ApiOperation(value="模糊查询设备信息对象列表")
	@Select("<script>"
			+ "select t.*,d.dict_name as terminal_type_name from op_terminal t "
			+ "left join dict_item d on d.dict_id=t.terminal_type "
			+ " where 1=1"
			+ " and terminal_id = ${terminal_id}"
			+ "</script>")
	public TerminalBean findByTerminalId(Terminal terminal);
	
	@ApiOperation(value="批量删除设备信息对象")
	@Delete("delete from op_terminal where terminal_id in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
	@ApiOperation(value = "批量更新设备信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+"UPDATE op_terminal "
			+"<set>"
			+"<if test='item.terminal_type != null'>"
			+ "terminal_type = #{item.terminal_type},"
			+ "</if>"
			+" <if test='item.status != null'>"
			+ "status = #{item.status},"
			+ "</if>"
			+" <if test='item.terminal_code != null'>"
			+ "terminal_code = #{item.terminal_code},"
			+ "</if>"
			+"</set>"
			+"WHERE 1=1"
			+" and terminal_id = #{item.terminal_id}"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Terminal> list);
	
	@ApiOperation(value = "批量新增设备信息")
	@Insert("<script>"
			+ "INSERT INTO op_terminal (terminal_id, terminal_type, status, create_time,terminal_code)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('terminal_code'),#{item.terminal_type},#{item.status},#{item.create_time},#{item.terminal_code})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Terminal> list);
}
