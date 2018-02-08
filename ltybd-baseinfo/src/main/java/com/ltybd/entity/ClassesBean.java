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

@ApiModel(value="班次2")
@Entity
@Table(name = "op_classes")
public class ClassesBean {
	@Id
	@ApiModelProperty(value="班次id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer classes_id;
	
	@ApiModelProperty(value="班次名称")
	@Length(max=100,message="班次名称长度不能大于100个字符")
	private String classes_name;
	
	@ApiModelProperty(value="状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	@ApiModelProperty(value="创建时间(yyyy-MM-dd HH:mm:ss)",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;
	
	public Integer getClasses_id() {
		return classes_id;
	}
	public void setClasses_id(Integer classes_id) {
		this.classes_id = classes_id;
	}
	public String getClasses_name() {
		return classes_name;
	}
	public void setClasses_name(String classes_name) {
		this.classes_name = classes_name;
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
