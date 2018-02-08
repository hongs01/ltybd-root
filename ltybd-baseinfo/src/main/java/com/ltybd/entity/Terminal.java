package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Terminal.java
 *
 * describe:设备信息表
 * 
 * 2017年10月12日 下午2:09:09 created By Yancz version 0.1
 *
 * 2017年10月12日 下午2:09:09 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "设备信息")
@Entity
@Table(name = "op_terminal")
public class Terminal {

	@Id
	@ApiModelProperty(value = "设备编号",hidden=true)
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer terminal_id;

	@ApiModelProperty(value = "设备类型")
	@NotNull(message="设备类型不能为空")
	private Integer terminal_type;

	@ApiModelProperty(value = "状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value = "设备编码")
	@NotNull(message="设备编码不能为空")
	@Length(max=100,message="设备编码长度不能大于100字符")
	private String terminal_code;

	public Integer getTerminal_id() {
		return terminal_id;
	}

	public Integer getTerminal_type() {
		return terminal_type;
	}

	public void setTerminal_type(Integer terminal_type) {
		this.terminal_type = terminal_type;
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

	public String getTerminal_code() {
		return terminal_code;
	}

	public void setTerminal_code(String terminal_code) {
		this.terminal_code = terminal_code;
	}

	public void setTerminal_id(Integer terminal_id) {
		this.terminal_id = terminal_id;
	}


}
