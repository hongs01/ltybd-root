package com.ltybd.entity;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "线路展示实体")
public class LineBean {
	@ApiModelProperty(value = "线路ID")
	private Integer line_id;

	@ApiModelProperty(value = "线路名称")
	private String line_name;

	@ApiModelProperty(value = "线路类型")
	private Integer line_type;
	
	@ApiModelProperty(value = "线路类型名称")
	private String line_type_name;

	@ApiModelProperty(value = "环线类型")
	private Integer loop_type;
	
	@ApiModelProperty(value = "环线类型名称")
	private String loop_type_name;

	@ApiModelProperty(value = "班制")
	private Integer class_system;
	
	@ApiModelProperty(value = "班制名称")
	private String class_system_name;

	@ApiModelProperty(value = "调车方式")
	private Integer shunting_type;
	
	@ApiModelProperty(value = "调车方式名称")
	private String shunting_type_name;

	@ApiModelProperty(value = "调度类型")
	private Integer schedule_type;
	
	@ApiModelProperty(value = "调度类型名称")
	private String schedule_type_name;

	@ApiModelProperty(value = "集团ID")
	private Integer group_id;

	@ApiModelProperty(value = "公司ID")
	private Integer company_id;

	@ApiModelProperty(value = "部门ID")
	private Integer department_id;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "线路编码")
	private Integer line_code;
	
	@ApiModelProperty(value = "大轮换周期")
	private Integer rotation_cycle;
	
	@ApiModelProperty(value = "上行首班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm:ss")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date first_uplink_time;

	@ApiModelProperty(value = "上行末班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm:ss")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date last_uplink_time;

	@ApiModelProperty(value = "下行首班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm:ss")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date first_downlink_time;

	@ApiModelProperty(value = "下行末班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm:ss")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date last_downlink_time;

	@ApiModelProperty(value = "里程")
	private Double mileage;

	@ApiModelProperty(value = "线路开通日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date line_open_time;

	@ApiModelProperty(value = "线路关闭日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date line_off_time;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	@ApiModelProperty(value = "上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;
	
	@ApiModelProperty(value = "线路车辆总数")
	private Integer vechicleNum;
	
	@ApiModelProperty(value = "线路驾驶员总数")
	private Integer driverNum;
	
	@ApiModelProperty(value = "线路乘务员总数")
	private Integer conductorNum;
	
	@ApiModelProperty(value = "线路总人数")
	private Integer peopleNum;
	
	@ApiModelProperty(value = "驾驶员人车比")
	private float driverRate;
	
	@ApiModelProperty(value = "乘务员人车比")
	private float conductorRate;
	
	@ApiModelProperty(value = "人车比")
	private float peopleRate;
	
	@ApiModelProperty(value = "父线路ID")
	private Integer parent_line_id;

	@ApiModelProperty(value = "是否大轮换")
	private Integer is_big_rotation;

	@ApiModelProperty(value = "最后轮换时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_rotation_date;
	
	@ApiModelProperty(value = "是否有售票员")
	private Integer is_artificial_ticket;
	
	@ApiModelProperty(value = "票价")
	private Double ticket_price;
	
	@ApiModelProperty(value = "速度")
	private Double speed;
	
	@ApiModelProperty(value = "汽车类型")
	private String bus_type;
	
	// 线路地图制作相关字段
		@ApiModelProperty(value = "线条宽度")
		private Integer width;
		
		@ApiModelProperty(value = "站点颜色")
		private String stationColor;
		
		@ApiModelProperty(value = "站点图标")
		private String stationIcon;
		
		@ApiModelProperty(value = "是否显示站点")
		private Integer isShowStation;
		
		@ApiModelProperty(value = "是否显示站名")
		private Integer isShowStationName;
		
		@ApiModelProperty(value = "站点名颜色")
		private String stationNameColor;
		
		@ApiModelProperty(value = "站点名字体")
		private String stationNamefont;
		
		@ApiModelProperty(value = "是否显示辅助点")
		private String isShowPoint;
		
		@ApiModelProperty(value = "线条颜色")
		private String color;
	
	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public Integer getParent_line_id() {
		return parent_line_id;
	}

	public Integer getIs_big_rotation() {
		return is_big_rotation;
	}

	public void setIs_big_rotation(Integer is_big_rotation) {
		this.is_big_rotation = is_big_rotation;
	}

	public Date getLast_rotation_date() {
		return last_rotation_date;
	}

	public void setLast_rotation_date(Date last_rotation_date) {
		this.last_rotation_date = last_rotation_date;
	}

	public Integer getIs_artificial_ticket() {
		return is_artificial_ticket;
	}

	public void setIs_artificial_ticket(Integer is_artificial_ticket) {
		this.is_artificial_ticket = is_artificial_ticket;
	}

	public Double getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(Double ticket_price) {
		this.ticket_price = ticket_price;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public void setParent_line_id(Integer parent_line_id) {
		this.parent_line_id = parent_line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public String getLine_name() {
		return line_name;
	}

	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}

	public Integer getLine_code() {
		return line_code;
	}

	public void setLine_code(Integer line_code) {
		this.line_code = line_code;
	}

	public Integer getRotation_cycle() {
		return rotation_cycle;
	}

	public void setRotation_cycle(Integer rotation_cycle) {
		this.rotation_cycle = rotation_cycle;
	}

	public Integer getLine_type() {
		return line_type;
	}

	public void setLine_type(Integer line_type) {
		this.line_type = line_type;
	}

	public Integer getLoop_type() {
		return loop_type;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public void setLoop_type(Integer loop_type) {
		this.loop_type = loop_type;
	}

	public Integer getClass_system() {
		return class_system;
	}

	public void setClass_system(Integer class_system) {
		this.class_system = class_system;
	}

	public Integer getShunting_type() {
		return shunting_type;
	}

	public void setShunting_type(Integer shunting_type) {
		this.shunting_type = shunting_type;
	}

	public Integer getSchedule_type() {
		return schedule_type;
	}

	public void setSchedule_type(Integer schedule_type) {
		this.schedule_type = schedule_type;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFirst_uplink_time() {
		return first_uplink_time;
	}

	public void setFirst_uplink_time(Date first_uplink_time) {
		this.first_uplink_time = first_uplink_time;
	}

	public Date getLast_uplink_time() {
		return last_uplink_time;
	}

	public void setLast_uplink_time(Date last_uplink_time) {
		this.last_uplink_time = last_uplink_time;
	}

	public Date getFirst_downlink_time() {
		return first_downlink_time;
	}

	public void setFirst_downlink_time(Date first_downlink_time) {
		this.first_downlink_time = first_downlink_time;
	}

	public Date getLast_downlink_time() {
		return last_downlink_time;
	}

	public void setLast_downlink_time(Date last_downlink_time) {
		this.last_downlink_time = last_downlink_time;
	}

	

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Date getLine_open_time() {
		return line_open_time;
	}

	public void setLine_open_time(Date line_open_time) {
		this.line_open_time = line_open_time;
	}

	public Date getLine_off_time() {
		return line_off_time;
	}

	public void setLine_off_time(Date line_off_time) {
		this.line_off_time = line_off_time;
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

	public String getLine_type_name() {
		return line_type_name;
	}

	public void setLine_type_name(String line_type_name) {
		this.line_type_name = line_type_name;
	}

	public String getLoop_type_name() {
		return loop_type_name;
	}

	public void setLoop_type_name(String loop_type_name) {
		this.loop_type_name = loop_type_name;
	}

	public String getClass_system_name() {
		return class_system_name;
	}

	public void setClass_system_name(String class_system_name) {
		this.class_system_name = class_system_name;
	}

	public String getShunting_type_name() {
		return shunting_type_name;
	}

	public void setShunting_type_name(String shunting_type_name) {
		this.shunting_type_name = shunting_type_name;
	}

	public String getSchedule_type_name() {
		return schedule_type_name;
	}

	public void setSchedule_type_name(String schedule_type_name) {
		this.schedule_type_name = schedule_type_name;
	}

	public float getDriverRate() {
		return driverRate;
	}

	public void setDriverRate(float driverRate) {
		this.driverRate = driverRate;
	}

	public float getConductorRate() {
		return conductorRate;
	}

	public void setConductorRate(float conductorRate) {
		this.conductorRate = conductorRate;
	}

	public float getPeopleRate() {
		return peopleRate;
	}

	public void setPeopleRate(float peopleRate) {
		this.peopleRate = peopleRate;
	}

	public Integer getVechicleNum() {
		return vechicleNum;
	}

	public void setVechicleNum(Integer vechicleNum) {
		this.vechicleNum = vechicleNum;
	}

	public Integer getDriverNum() {
		return driverNum;
	}

	public void setDriverNum(Integer driverNum) {
		this.driverNum = driverNum;
	}

	public Integer getConductorNum() {
		return conductorNum;
	}

	public void setConductorNum(Integer conductorNum) {
		this.conductorNum = conductorNum;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getStationColor() {
		return stationColor;
	}

	public void setStationColor(String stationColor) {
		this.stationColor = stationColor;
	}

	public String getStationIcon() {
		return stationIcon;
	}

	public void setStationIcon(String stationIcon) {
		this.stationIcon = stationIcon;
	}

	public Integer getIsShowStation() {
		return isShowStation;
	}

	public void setIsShowStation(Integer isShowStation) {
		this.isShowStation = isShowStation;
	}

	public Integer getIsShowStationName() {
		return isShowStationName;
	}

	public void setIsShowStationName(Integer isShowStationName) {
		this.isShowStationName = isShowStationName;
	}

	public String getStationNameColor() {
		return stationNameColor;
	}

	public void setStationNameColor(String stationNameColor) {
		this.stationNameColor = stationNameColor;
	}

	public String getStationNamefont() {
		return stationNamefont;
	}

	public void setStationNamefont(String stationNamefont) {
		this.stationNamefont = stationNamefont;
	}

	public String getIsShowPoint() {
		return isShowPoint;
	}

	public void setIsShowPoint(String isShowPoint) {
		this.isShowPoint = isShowPoint;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
