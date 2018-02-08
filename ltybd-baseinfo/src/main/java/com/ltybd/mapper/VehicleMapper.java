package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Vehicle;
import com.ltybd.entity.VehicleBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * VehicleMapper.java
 *
 * describe:车辆信息Mapper
 * 
 * 2017年10月13日 下午3:00:08 created By Yancz version 0.1
 *
 * 2017年10月13日 下午3:00:08 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "VehicleMapper", description = "车辆信息Mapper")
public interface VehicleMapper extends MyMapper<Vehicle> {
	@ApiOperation(value = "批量删除车辆信息对象")
	@Delete("delete from op_vehicle where vechicle_id in (${ids})")
	public int deleteItems(@Param("ids") String ids);
	
	@ApiOperation(value = "查詢车辆信息集合")
	@Select("<script>"
			+ "select v.*,d.dict_name as vechicle_type_name,"
			+ "d1.dict_name as envir_sign_name,"
			+ "d2.dict_name as gearbox_name,"
			+ "d3.dict_name as power_type_name,"
			+ "d4.dict_name as brand_name,"
			+ "d5.dict_name as model_name,"
			+ "d6.dict_name as colour_name"
			+ " from op_vehicle v "
			+ "left join dict_item d on d.dict_id=v.vechicle_type "
			+ "left join dict_item d1 on d1.dict_id=v.envir_sign "
			+ "left join dict_item d2 on d2.dict_id=v.gearbox "
			+ "left join dict_item d3 on d3.dict_id=v.power_type "
			+ "left join dict_item d4 on d4.dict_id=v.brand "
			+ "left join dict_item d5 on d5.dict_id=v.model "
			+ "left join dict_item d6 on d6.dict_id=v.colour "
			+ "where 1=1"
			+ "<if test='vechicle_id != null '>"
			+ " and vechicle_id=#{vechicle_id}"
			+ "</if>"
			+ "<if test='vechicle_type != null '>"
			+ " and vechicle_type=#{vechicle_type}"
			+ "</if>"
			+ "<if test='group_id != null '>"
			+ " and group_id=#{group_id}"
			+ "</if>"
			+ "<if test='company_id != null '>"
			+ " and company_id=#{department_id}"
			+ "</if>"
			+ "<if test='department_id != null '>"
			+ " and department_id=#{department_id}"
			+ "</if>"
			+ "<if test='plate_no != null'>"
			+ " and plate_no like '%${plate_no}%'"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and status = #{status}"
			+ "</if>"
			+ "<if test='vechicle_code != null '>"
			+ " and vechicle_code like '%${vechicle_code}%'"
			+ "</if>"
			+ "</script>")
	public List<VehicleBean> findListObj(Vehicle vehicle);
	
	
	@ApiOperation(value = "查詢车辆信息对象")
	@Select("<script>"
			+ "select v.*,d.dict_name as vechicle_type_name,"
			+ "d1.dict_name as envir_sign_name,"
			+ "d2.dict_name as gearbox_name,"
			+ "d3.dict_name as power_type_name,"
			+ "d4.dict_name as brand_name,"
			+ "d5.dict_name as model_name,"
			+ "d6.dict_name as colour_name"
			+ " from op_vehicle v "
			+ "left join dict_item d on d.dict_id=v.vechicle_type "
			+ "left join dict_item d1 on d1.dict_id=v.envir_sign "
			+ "left join dict_item d2 on d2.dict_id=v.gearbox "
			+ "left join dict_item d3 on d3.dict_id=v.power_type "
			+ "left join dict_item d4 on d4.dict_id=v.brand "
			+ "left join dict_item d5 on d5.dict_id=v.model "
			+ "left join dict_item d6 on d6.dict_id=v.colour "
			+ " where 1=1"
			+ " and vechicle_id=#{vechicle_id}"
			+ "</script>")
	public VehicleBean findByVehicleId(Vehicle vehicle);
	
	@ApiOperation(value = "批量修改车辆设备信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+"update op_vehicle"
			+"<set>"
			+ "<if test='item.vechicle_type != null'>"
			+"vechicle_type=#{item.vechicle_type},"
			+ "</if>"
			+ "<if test='item.group_id != null'>"
			+"group_id=#{item.group_id},"
			+ "</if>"
			+ "<if test='item.company_id != null'>"
			+"company_id=#{item.company_id},"
			+ "</if>"
			+ "<if test='item.department_id != null'>"
			+"department_id=#{item.department_id},"
			+ "</if>"
			+ "<if test='item.pic_address != null'>"
			+"pic_address=#{item.pic_address},"
			+ "</if>"
			+ "<if test='item.plate_no != null'>"
			+"plate_no=#{item.plate_no},"
			+ "</if>"
			+ "<if test='item.frame_no != null'>"
			+"frame_no=#{item.frame_no},"
			+ "</if>"
			+ "<if test='item.engine_no != null'>"
			+"engine_no=#{item.engine_no},"
			+ "</if>"
			+ "<if test='item.registration_no != null'>"
			+"registration_no=#{item.registration_no},"
			+ "</if>"
			+ "<if test='item.registration_date != null'>"
			+"registration_date=#{item.registration_date},"
			+ "</if>"
			+ "<if test='item.scrap_date != null'>"
			+"scrap_date=#{item.scrap_date},"
			+ "</if>"
			+ "<if test='item.mot_date != null'>"
			+"mot_date=#{item.mot_date},"
			+ "</if>"
			+ "<if test='item.displacement != null'>"
			+"displacement=#{item.displacement},"
			+ "</if>"
			+ "<if test='item.input_date != null'>"
			+"input_date=#{item.input_date},"
			+ "</if>"
			+ "<if test='item.service_life != null'>"
			+"service_life=#{item.service_life},"
			+ "</if>"
			+ "<if test='item.residual_value != null'>"
			+"residual_value=#{item.residual_value},"
			+ "</if>"
			+ "<if test='item.length != null'>"
			+"length=#{item.length},"
			+ "</if>"
			+ "<if test='item.width != null'>"
			+"width=#{item.width},"
			+ "</if>"
			+ "<if test='item.height != null'>"
			+"height=#{item.height},"
			+ "</if>"
			+ "<if test='item.envir_sign != null'>"
			+"envir_sign=#{item.envir_sign},"
			+ "</if>"
			+ "<if test='item.gearbox != null'>"
			+"gearbox=#{item.gearbox},"
			+ "</if>"
			+ "<if test='item.power_type != null'>"
			+"power_type=#{item.power_type},"
			+ "</if>"
			+ "<if test='item.carbon_dioxide != null'>"
			+"carbon_dioxide=#{item.carbon_dioxide},"
			+ "</if>"
			+ "<if test='item.horsepower != null'>"
			+"horsepower=#{item.horsepower},"
			+ "</if>"
			+ "<if test='item.power != null'>"
			+"power=#{item.power},"
			+ "</if>"
			+ "<if test='item.weight != null'>"
			+"weight=#{item.weight},"
			+ "</if>"
			+ "<if test='item.brand != null'>"
			+"brand=#{item.brand},"
			+ "</if>"
			+ "<if test='item.model != null'>"
			+"model=#{item.model},"
			+ "</if>"
			+ "<if test='item.colour != null'>"
			+"colour=#{item.colour},"
			+ "</if>"
			+ "<if test='item.seats != null'>"
			+"seats=#{item.seats},"
			+ "</if>"
			+ "<if test='item.passengers_no != null'>"
			+"passengers_no=#{item.passengers_no},"
			+ "</if>"
			+ "<if test='item.doors != null'>"
			+"doors=#{item.doors},"
			+ "</if>"
			+ "<if test='item.status != null'>"
			+"status=#{item.status},"
			+ "</if>"
			+ "<if test='item.create_time != null'>"
			+"create_time=#{item.create_time},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+"last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "<if test='vechicle_code != null '>"
			+ "vechicle_code = #{vechicle_code}"
			+ "</if>"
			+"</set>"
			+" where 1=1"
			+" AND vechicle_id =#{item.vechicle_id}"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Vehicle> list);
	
	@ApiOperation(value = "批量添加车辆设备信息")
	@Insert("<script>"
			+ "INSERT INTO op_vehicle (vechicle_id, vechicle_type, group_id, company_id, department_id, pic_address, plate_no, frame_no, engine_no, registration_no, registration_date, scrap_date, mot_date, displacement, input_date, service_life, residual_value, length, width, height, envir_sign, gearbox, power_type, carbon_dioxide, horsepower, power, weight, brand, model, colour, seats, passengers_no, doors, status, create_time, last_modified_time,vechicle_code)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('vehicle_code'),#{item.vechicle_type},#{item.group_id},#{item.company_id},#{item.department_id},#{item.pic_address},#{item.plate_no},#{item.frame_no},#{item.engine_no},#{item.registration_no},#{item.registration_date},#{item.scrap_date},#{item.mot_date},#{item.displacement},#{item.input_date},#{item.service_life},#{item.residual_value},#{item.length},#{item.width},#{item.height},#{item.envir_sign},#{item.gearbox},#{item.power_type},#{item.carbon_dioxide},#{item.horsepower},#{item.power},#{item.weight},#{item.brand},#{item.model},#{item.colour},#{item.seats},#{item.passengers_no},#{item.doors},#{item.status},#{item.create_time},#{item.last_modified_time},#{item.vechicle_code})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Vehicle> list);

}
