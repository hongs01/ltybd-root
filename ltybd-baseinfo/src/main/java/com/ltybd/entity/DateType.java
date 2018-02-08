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


/**
 * DateType.java
 *
 * describe:
 * 
 * 2017年11月6日 上午11:59:16 created By chenq version 0.1
 *
 * 2017年11月6日 上午11:59:16 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value="日期类型表")
@Entity
@Table(name = "op_date_type")
public class DateType {
	    
	   @Id
	   @ApiModelProperty(value="日期类型id")
	   @Min(value=1000,message="必须为大于或者等于1000的正整数")
	   private Integer date_type_id;
	   
	   @ApiModelProperty(value="日期类型名")
	   @Length(max=100,message="日期类型名长度不能超过100个字符")
	   private String type_name;
	   
	   @ApiModelProperty(value="开始时间")
	   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	   private Date start_date;
	  
	   @ApiModelProperty(value="结束时间")
	   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	   private Date end_date;
	   
	   @ApiModelProperty(value="优先级")
	   @Length(max=10,message="优先级长度不能大于10个字符")
	   private String priority;
	   
	   @ApiModelProperty(value="状态")
	   @Max(value=2,message="状态长度不能大于2个字符")
	   private Integer status;
	      
	   @ApiModelProperty(value="创建时间",hidden=true)
	   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	   private Date create_date;
	   

		public Integer getDate_type_id() {
			return date_type_id;
		}
	
		public void setDate_type_id(Integer date_type_id) {
			this.date_type_id = date_type_id;
		}
	
		public String getType_name() {
			return type_name;
		}
	
		public void setType_name(String type_name) {
			this.type_name = type_name;
		}
	
		public Date getStart_date() {
			return start_date;
		}
	
		public void setStart_date(Date start_date) {
			this.start_date = start_date;
		}
	
		public Date getEnd_date() {
			return end_date;
		}
	
		public void setEnd_date(Date end_date) {
			this.end_date = end_date;
		}
	
		public String getPriority() {
			return priority;
		}
	
		public void setPriority(String priority) {
			this.priority = priority;
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
