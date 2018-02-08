package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.RescueConvoys;
import com.ltybd.entity.Yard;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="CompanyMapper", description = "施救车队Mapper")
public interface RescueConvoysMapper extends MyMapper<RescueConvoys> {
	
	@ApiOperation(value="模糊查询施救车队信息对象列表")
	@Select("<script> select * from op_rescue_convoys"
			+ " where 1=1"
			+ "<if test='rescue_name != null'>"
			+ " and rescue_name like '%${rescue_name}%'"
			+ "</if>"
			+ "<if test='company_id != null'>"
			+ " and company_id = #{company_id}"
			+ "</if>"
			+ "<if test='vehicle_no != null'>"
			+ " and vehicle_no = #{vehicle_no}"
			+ "</if>"
			+ "<if test='stop_address != null'>"
			+ " and stop_address like '%${stop_address}%'"
			+ "</if>"
			+ "<if test='responsible_telephone != null'>"
			+ " and responsible_telephone = #{responsible_telephone}"
			+ "</if>"
			+ "<if test='responsible_person != null'>"
			+ " and responsible_person like '%${responsible_person}%'"
			+ "</if>"
			+ "<if test='status != null'>"
			+ " and status = #{status}"
			+ "</if>"
			+ "<if test='vehicle_no != null'>"
			+ " and service_line like '%${service_line}%'"
			+ "</if>"
			+ "</script>")
	public List<RescueConvoys> findRescueListObj(RescueConvoys rescueConvoys);
	
	@ApiOperation(value="删除施救车队信息对象列表")
	@Delete("delete from op_rescue_convoys where rescue_id in (${ids})")
	public int deleteRescueList(@Param("ids")String rescue_ids);
	
	@ApiOperation(value="更新施救车队信息对象")
	@Update("<script> update op_rescue_convoys "
			+"<set>"
			+"<if test='rescue_name != null'>"
			+ "rescue_name = #{rescue_name},"
			+ "</if>"
			+"<if test='company_id != null'>"
			+ "company_id = #{company_id},"
			+ "</if>"
			+"<if test='vehicle_no != null'>"
			+ "vehicle_no = #{vehicle_no},"
			+ "</if>"
			+"<if test='stop_address != null'>"
			+ "stop_address = #{stop_address},"
			+ "</if>"
			+"<if test='responsible_telephone != null'>"
			+ "responsible_telephone = #{responsible_telephone},"
			+ "</if>"
			+"<if test='responsible_person != null'>"
			+ "responsible_person = #{responsible_person},"
			+ "</if>"
			+"<if test='service_date != null'>"
			+ "service_date = #{service_date},"
			+ "</if>"
			+"<if test='service_line != null'>"
			+ "service_line = #{service_line},"
			+ "</if>"
			+"<if test='status != null'>"
			+ "status = #{status},"
			+ "</if>"
			+"</set>"
			+"where rescue_id = #{rescue_id}"
			+ "</script>")
	public int updateRescue(RescueConvoys rescueConvoys);

	@ApiOperation(value="新增施救车队信息对象")
	@Insert("<script>"
			+ "insert into op_rescue_convoys("
			+ "rescue_id,company_id,vehicle_no,stop_address,"
			+ "responsible_telephone,responsible_person,"
			+ "service_date,service_line,status,create_date) "
			+ "values (#{rescue_id},#{company_id},#{vehicle_no},"
			+ "#{stop_address},#{responsible_telephone},"
			+ "#{responsible_person},#{service_date},"
			+ "#{service_line},#{status},#{create_date});"
			+ "</script>")
	public void insertRescue(RescueConvoys rescue);
	
}
