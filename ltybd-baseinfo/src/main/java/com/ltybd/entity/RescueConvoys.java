package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="施救车队实体")
@Entity
@Table(name = "op_rescue_convoys")
public class RescueConvoys {
	
	@Id
	@ApiModelProperty(value="施救车队id")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer rescue_id;
	
	@ApiModelProperty(value="所属公司")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer company_id;
	
	@ApiModelProperty(value="车辆号码")
	@Length(max=50,message="车辆号码长度不能大于50个字符")
	private String vehicle_no;
	
	@ApiModelProperty(value="施救车队名称")
	@Length(max=32,message="施救车队名称长度不能大于32个字符")
	private String rescue_name;
	
	@ApiModelProperty(value="停止地址")
	@Length(max=50,message="停止地址长度不能大于50个字符")
	private String stop_address;
	
	@ApiModelProperty(value="负责人电话")
	@Length(max=50,message="负责人电话长度不能大于50个字符")
	private String responsible_telephone;
	
	@ApiModelProperty(value="负责人")
	@Length(max=50,message="负责人长度不能大于50个字符")
	private String responsible_person;
	
	@ApiModelProperty(value="服务时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date service_date;
	
	@ApiModelProperty(value="服务路线")
	@Length(max=50,message="服务路线长度不能大于50个字符")
	private String service_line;
	
	@ApiModelProperty(value="状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	@ApiModelProperty(value="创建日期",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;

	public Integer getRescue_id() {
		return rescue_id;
	}

	public void setRescue_id(Integer rescue_id) {
		this.rescue_id = rescue_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getStop_address() {
		return stop_address;
	}

	public void setStop_address(String stop_address) {
		this.stop_address = stop_address;
	}

	public String getResponsible_telephone() {
		return responsible_telephone;
	}

	public void setResponsible_telephone(String responsible_telephone) {
		this.responsible_telephone = responsible_telephone;
	}

	public String getResponsible_person() {
		return responsible_person;
	}

	public void setResponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
	}

	public Date getService_date() {
		return service_date;
	}

	public void setService_date(Date service_date) {
		this.service_date = service_date;
	}

	public String getService_line() {
		return service_line;
	}

	public void setService_line(String service_line) {
		this.service_line = service_line;
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
