package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

/**
 * Line.java
 *
 * describe:线路
 * 
 * 2017年10月12日 上午11:53:10 created By Yancz version 0.1
 *
 * 2017年10月12日 上午11:53:10 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "线路")
@Entity
@Table(name = "op_line")
public class Line {

	@Id
	@ApiModelProperty(value = "线路ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer line_id;

	@ApiModelProperty(value = "线路编码")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	@NotNull(message="线路编码不能为空")
	private Integer line_code;
	
	@ApiModelProperty(value = "线路名称")
	@NotNull(message="线路名称不能为空")
	private String line_name;

	@ApiModelProperty(value = "线路类型")
	@NotNull(message="线路类型不能为空")
	private Integer line_type;

	@ApiModelProperty(value = "环线类型")
	@NotNull(message="环线类型不能为空")
	private Integer loop_type;

	@ApiModelProperty(value = "班制")
	@NotNull(message="班制不能为空")
	private Integer class_system;

	@ApiModelProperty(value = "调车方式")
	@NotNull(message="调车方式不能为空")
	private Integer shunting_type;

	@ApiModelProperty(value = "调度类型")
	@NotNull(message="调度类型不能为空")
	private Integer schedule_type;

	@ApiModelProperty(value = "集团ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer group_id;

	@ApiModelProperty(value = "公司ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer company_id;

	@ApiModelProperty(value = "部门ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer department_id;

	@ApiModelProperty(value = "状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;

	@ApiModelProperty(value = "上行首班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date first_uplink_time;

	@ApiModelProperty(value = "上行末班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date last_uplink_time;

	@ApiModelProperty(value = "下行首班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date first_downlink_time;

	@ApiModelProperty(value = "下行末班时间")
	@JsonFormat(timezone = "GMT+8",pattern = "HH:mm")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date last_downlink_time;

	@ApiModelProperty(value = "里程")
	@NotNull(message="里程不能为空")
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

	@ApiModelProperty(value = "父线路ID")
	private Integer parent_line_id;
	
	@ApiModelProperty(value = "大轮换周期")
	private Integer rotation_cycle;
	
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
	private Integer isShowPoint;
	
	@ApiModelProperty(value = "线条颜色")
	private String color;
	
	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public Integer getRotation_cycle() {
		return rotation_cycle;
	}

	public void setRotation_cycle(Integer rotation_cycle) {
		this.rotation_cycle = rotation_cycle;
	}

	public Integer getLine_id() {
		return line_id;
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

	public Integer getLine_type() {
		return line_type;
	}

	public void setLine_type(Integer line_type) {
		this.line_type = line_type;
	}

	public Integer getLoop_type() {
		return loop_type;
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

	public Integer getParent_line_id() {
		return parent_line_id;
	}

	public void setParent_line_id(Integer parent_line_id) {
		this.parent_line_id = parent_line_id;
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

	public Integer getIsShowPoint() {
		return isShowPoint;
	}

	public void setIsShowPoint(Integer isShowPoint) {
		this.isShowPoint = isShowPoint;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
