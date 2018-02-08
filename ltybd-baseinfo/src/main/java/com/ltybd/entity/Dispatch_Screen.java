package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "调度屏信息")
@Entity
@Table(name = "op_dispatch_screen")
public class Dispatch_Screen {
	
	@Id
	@ApiModelProperty(value = "调度屏ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer screen_id;

	@ApiModelProperty(value = "调度屏名称")
	@NotEmpty(message="名称不能为空")
	@Length(max=1000,message="名称长度不能大于30个字符")
	private String screen_name;
	
	
	@ApiModelProperty(value = "调度屏IP")
	@Length(max=1000,message="地址长度不能大于30个字符")
	private String screen_ip;
	
	
	@ApiModelProperty(value = "车场ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer yard_id;
	
	@ApiModelProperty(value = "状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	

	@ApiModelProperty(value = "创建日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;


	public Integer getScreen_id() {
		return screen_id;
	}


	public void setScreen_id(Integer screen_id) {
		this.screen_id = screen_id;
	}


	public String getScreen_name() {
		return screen_name;
	}


	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}


	public String getScreen_ip() {
		return screen_ip;
	}


	public void setScreen_ip(String screen_ip) {
		this.screen_ip = screen_ip;
	}


	public Integer getYard_id() {
		return yard_id;
	}


	public void setYard_id(Integer yard_id) {
		this.yard_id = yard_id;
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
