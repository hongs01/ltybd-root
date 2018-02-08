package com.ltybd.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "设备信息展示实体类")
public class TerminalBean {
	@ApiModelProperty(value = "设备编号")
	private Integer terminal_id;

	@ApiModelProperty(value = "设备类型")
	private Integer terminal_type;
	
	@ApiModelProperty(value = "设备类型名称")
	private String terminal_type_name;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	public Integer getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(Integer terminal_id) {
		this.terminal_id = terminal_id;
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

	public String getTerminal_type_name() {
		return terminal_type_name;
	}

	public void setTerminal_type_name(String terminal_type_name) {
		this.terminal_type_name = terminal_type_name;
	}
	
	
}
