package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
public class YardLine {

	@Id
	@ApiModelProperty(value="车场id")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer yard_id;

	@ApiModelProperty(value = "线路ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	@NotEmpty(message="线路ID不能为空")
	private Integer line_id;
	
	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value = "最后修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;

	public Integer getYard_id() {
		return yard_id;
	}

	public void setYard_id(Integer yard_id) {
		this.yard_id = yard_id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
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
