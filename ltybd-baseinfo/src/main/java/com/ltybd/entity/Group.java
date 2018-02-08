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

/**
 * Employee.java
 *
 * describe:员工信息
 * 
 * 2017年10月12日 下午2:20:41 created By Chenjw version 0.1
 *
 * 2017年10月12日 下午2:20:41 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value="集团信息")
@Entity
@Table(name = "op_group")
public class Group {

	@Id
	@ApiModelProperty(value="集团ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer group_id;
	
	@ApiModelProperty(value="集团名称")
	/*@NotEmpty(message="集团名称不能为空")*/
	@Length(max=100,message="集团名称长度不能超过100个字符")
	private String  group_name;
	   
	@ApiModelProperty(value="备注")
	@Length(max=1000,message="备注长度不能大于1000个字符")
	private String remark;
	
	@ApiModelProperty(value="状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	@ApiModelProperty(value="创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value="上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
