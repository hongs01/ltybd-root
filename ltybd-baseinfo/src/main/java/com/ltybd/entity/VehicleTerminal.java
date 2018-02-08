package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VehicleTerminal.java
 *
 * describe:车辆设备信息
 * 
 * 2017年10月12日 下午2:28:17 created By Yancz version 0.1
 *
 * 2017年10月12日 下午2:28:17 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "车辆设备信息")
@Entity
@Table(name = "op_vehicle_terminal")
public class VehicleTerminal {

	@ApiModelProperty(value = "车辆ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer vehicle_id;

	@ApiModelProperty(value = "设备ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer terminal_id;

	@ApiModelProperty(value = "状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	@ApiModelProperty(value = "上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;

	public Integer getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Integer vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public Integer getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(Integer terminal_id) {
		this.terminal_id = terminal_id;
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

}
