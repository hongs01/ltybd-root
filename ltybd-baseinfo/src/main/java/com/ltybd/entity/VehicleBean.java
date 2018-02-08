package com.ltybd.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "车辆信息展示实体")
public class VehicleBean {
	@ApiModelProperty(value = "车辆ID")
	private Integer vechicle_id;

	@ApiModelProperty(value = "车辆类型")
	private String vechicle_type;
	
	@ApiModelProperty(value = "车辆类型名称")
	private String vechicle_type_name;

	@ApiModelProperty(value = "集团ID")
	private Integer group_id;

	@ApiModelProperty(value = "公司ID")
	private Integer company_id;

	@ApiModelProperty(value = "部门ID")
	private Integer department_id;

	@ApiModelProperty(value = "图片存储路径")
	private String pic_address;

	@ApiModelProperty(value = "车牌号")
	private String plate_no;

	@ApiModelProperty(value = "车架号")
	private String frame_no;

	@ApiModelProperty(value = "发动机号")
	private String engine_no;

	@ApiModelProperty(value = "登记证号")
	private String registration_no;

	@ApiModelProperty(value = "登记日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registration_date;

	@ApiModelProperty(value = "强制报废日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date scrap_date;

	@ApiModelProperty(value = "年检日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date mot_date;

	@ApiModelProperty(value = "排量")
	private Double displacement;

	@ApiModelProperty(value = "投入日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date input_date;

	@ApiModelProperty(value = "使用年限")
	private Integer service_life;

	@ApiModelProperty(value = "年限残值")
	private Double residual_value;

	@ApiModelProperty(value = "车长")
	private Integer length;

	@ApiModelProperty(value = "车宽")
	private Integer width;

	@ApiModelProperty(value = "车高")
	private Integer height;

	@ApiModelProperty(value = "环保标志")
	private String envir_sign;
	
	@ApiModelProperty(value = "环保标志名称")
	private String envir_sign_name;

	@ApiModelProperty(value = "变速箱")
	private String gearbox;
	
	@ApiModelProperty(value = "变速箱名称")
	private String gearbox_name;

	@ApiModelProperty(value = "动力类型")
	private String power_type;
	
	@ApiModelProperty(value = "动力类型名称")
	private String power_type_name;

	@ApiModelProperty(value = "尾气排放量")
	private Double carbon_dioxide;

	@ApiModelProperty(value = "马力")
	private Integer horsepower;

	@ApiModelProperty(value = "功率")
	private Double power;

	@ApiModelProperty(value = "重量")
	private Double weight;

	@ApiModelProperty(value = "品牌")
	private String brand;
	
	@ApiModelProperty(value = "品牌名称")
	private String brand_name;

	@ApiModelProperty(value = "型号")
	private String model;
	
	@ApiModelProperty(value = "型号名称")
	private String model_name;

	@ApiModelProperty(value = "颜色名称")
	private String colour_name;
	
	@ApiModelProperty(value = "颜色")
	private String colour;

	@ApiModelProperty(value = "座位数")
	private Integer seats;

	@ApiModelProperty(value = "载客人数")
	private Integer passengers_no;

	@ApiModelProperty(value = "门数")
	private Integer doors;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	@ApiModelProperty(value = "上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;

	public Integer getVechicle_id() {
		return vechicle_id;
	}

	public void setVechicle_id(Integer vechicle_id) {
		this.vechicle_id = vechicle_id;
	}

	public String getVechicle_type() {
		return vechicle_type;
	}

	public void setVechicle_type(String vechicle_type) {
		this.vechicle_type = vechicle_type;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getPic_address() {
		return pic_address;
	}

	public void setPic_address(String pic_address) {
		this.pic_address = pic_address;
	}

	public String getPlate_no() {
		return plate_no;
	}

	public void setPlate_no(String plate_no) {
		this.plate_no = plate_no;
	}

	public String getFrame_no() {
		return frame_no;
	}

	public void setFrame_no(String frame_no) {
		this.frame_no = frame_no;
	}

	public String getEngine_no() {
		return engine_no;
	}

	public void setEngine_no(String engine_no) {
		this.engine_no = engine_no;
	}

	public String getRegistration_no() {
		return registration_no;
	}

	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public Date getScrap_date() {
		return scrap_date;
	}

	public void setScrap_date(Date scrap_date) {
		this.scrap_date = scrap_date;
	}

	public Date getMot_date() {
		return mot_date;
	}

	public void setMot_date(Date mot_date) {
		this.mot_date = mot_date;
	}

	public Double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	public Date getInput_date() {
		return input_date;
	}

	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}

	public Integer getService_life() {
		return service_life;
	}

	public void setService_life(Integer service_life) {
		this.service_life = service_life;
	}

	public Double getResidual_value() {
		return residual_value;
	}

	public void setResidual_value(Double residual_value) {
		this.residual_value = residual_value;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getEnvir_sign() {
		return envir_sign;
	}

	public void setEnvir_sign(String envir_sign) {
		this.envir_sign = envir_sign;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getPower_type() {
		return power_type;
	}

	public void setPower_type(String power_type) {
		this.power_type = power_type;
	}

	public Double getCarbon_dioxide() {
		return carbon_dioxide;
	}

	public void setCarbon_dioxide(Double carbon_dioxide) {
		this.carbon_dioxide = carbon_dioxide;
	}

	public Integer getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(Integer horsepower) {
		this.horsepower = horsepower;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getPassengers_no() {
		return passengers_no;
	}

	public void setPassengers_no(Integer passengers_no) {
		this.passengers_no = passengers_no;
	}

	public Integer getDoors() {
		return doors;
	}

	public void setDoors(Integer doors) {
		this.doors = doors;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getLast_modified_time() {
		return last_modified_time;
	}

	public void setLast_modified_time(Date last_modified_time) {
		this.last_modified_time = last_modified_time;
	}

	public String getVechicle_type_name() {
		return vechicle_type_name;
	}

	public void setVechicle_type_name(String vechicle_type_name) {
		this.vechicle_type_name = vechicle_type_name;
	}

	public String getEnvir_sign_name() {
		return envir_sign_name;
	}

	public void setEnvir_sign_name(String envir_sign_name) {
		this.envir_sign_name = envir_sign_name;
	}

	public String getGearbox_name() {
		return gearbox_name;
	}

	public void setGearbox_name(String gearbox_name) {
		this.gearbox_name = gearbox_name;
	}

	public String getPower_type_name() {
		return power_type_name;
	}

	public void setPower_type_name(String power_type_name) {
		this.power_type_name = power_type_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getColour_name() {
		return colour_name;
	}

	public void setColour_name(String colour_name) {
		this.colour_name = colour_name;
	}
	
	
}
