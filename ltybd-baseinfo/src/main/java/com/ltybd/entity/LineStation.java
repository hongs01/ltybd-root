package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * LineStation.java
 *
 * describe:线路站点信息
 * 
 * 2017年10月12日 下午1:48:40 created By Yancz version 0.1
 *
 * 2017年10月12日 下午1:48:40 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "线路站点信息")
@Entity
@Table(name = "op_line_station")
public class LineStation {

	@ApiModelProperty(value = "线路ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer line_id;

	@ApiModelProperty(value = "线路方向")
	@Max(value=2,message="线路方向长度不能大于2个字符")
	private Integer direction;

	@ApiModelProperty(value = "站台ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer bus_station_code;

	@ApiModelProperty(value = "站序")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer order_no;

	@ApiModelProperty(value = "站点类型")
	@NotEmpty(message="站点类型不能为空")
	private String station_type;

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

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getBus_station_code() {
		return bus_station_code;
	}

	public void setBus_station_code(Integer bus_station_code) {
		this.bus_station_code = bus_station_code;
	}

	public Integer getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public String getStation_type() {
		return station_type;
	}

	public void setStation_type(String station_type) {
		this.station_type = station_type;
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
