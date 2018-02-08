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
 * Road.java
 *
 * describe:
 * 
 * 2017年11月7日 下午8:29:05 created By chenq version 0.1
 *
 * 2017年11月7日 下午8:29:05 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value="道路")
@Entity
@Table(name = "op_road")
public class Road {
	
	   @Id
	   @ApiModelProperty(value="道路id")
	   @Min(value=1000,message="必须为大于或者等于1000的正整数")
	   private Integer road_id;
	   
	   @ApiModelProperty(value="道路编码")
	   @Length(max=100,message="日期类型名长度不能超过100个字符")
	   private String road_code;
	   
	   @ApiModelProperty(value="道路名称")
	   @Length(max=100,message="日期类型名长度不能超过100个字符")
	   private String road_name;
	   
	   @ApiModelProperty(value="区域ID")
	   @Min(value=1000,message="必须为大于或者等于1000的正整数")
	   private Integer district_id ;
	 
	   @ApiModelProperty(value="状态")
	   @Max(value=2,message="状态长度不能大于2个字符")
	   private Integer status;
	
	   @ApiModelProperty(value="创建时间",hidden=true)
	   @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	   private Date create_date;

		public Integer getRoad_id() {
			return road_id;
		}
		
		public void setRoad_id(Integer road_id) {
			this.road_id = road_id;
		}
		
		public String getRoad_code() {
			return road_code;
		}
		
		public void setRoad_code(String road_code) {
			this.road_code = road_code;
		}
		
		public String getRoad_name() {
			return road_name;
		}
		
		public void setRoad_name(String road_name) {
			this.road_name = road_name;
		}
		
		public Integer getDistrict_id() {
			return district_id;
		}
		
		public void setDistrict_id(Integer district_id) {
			this.district_id = district_id;
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
