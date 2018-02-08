package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="班组1")
@Entity
@Table(name = "op_bus_group")
public class BusGroupBean {
	@Id
	@ApiModelProperty(value="班组id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	@NotNull(message="班组id不能为空")
	private Integer group_id;
	
	@ApiModelProperty(value="班组名称")
	@Length(max=100,message="班组名称长度不能大于100个字符")
	private String name;
	
	@ApiModelProperty(value="司乘轮班算法id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer bus_driver_algorithm_id;

	@ApiModelProperty(value="车辆轮趟算法id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer bus_algorithm_id;
	
	@ApiModelProperty(value="线路id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer line_id;
	
	@ApiModelProperty(value="班制id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer shift_id;

	@ApiModelProperty(value="方向")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer direction;
	
	@ApiModelProperty(value="状态")
	private Integer status;

	@ApiModelProperty(value="创建时间(yyyy-MM-dd HH:mm:ss)",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBus_driver_algorithm_id() {
		return bus_driver_algorithm_id;
	}

	public void setBus_driver_algorithm_id(Integer bus_driver_algorithm_id) {
		this.bus_driver_algorithm_id = bus_driver_algorithm_id;
	}

	public Integer getBus_algorithm_id() {
		return bus_algorithm_id;
	}

	public void setBus_algorithm_id(Integer bus_algorithm_id) {
		this.bus_algorithm_id = bus_algorithm_id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public Integer getShift_id() {
		return shift_id;
	}

	public void setShift_id(Integer shift_id) {
		this.shift_id = shift_id;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
