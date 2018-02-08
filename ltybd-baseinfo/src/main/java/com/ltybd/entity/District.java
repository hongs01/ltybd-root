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
 * District.java
 *
 * describe:
 * 
 * 2017年11月7日 下午8:28:48 created By chenq version 0.1
 *
 * 2017年11月7日 下午8:28:48 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value="区域")
@Entity
@Table(name = "op_district")
public class District {
	
	  @Id
	  @ApiModelProperty(value="区域ID")
	  @Min(value=1000,message="必须为大于或者等于1000的正整数")
	  private Integer district_id ;
	  
	  @ApiModelProperty(value="区域编码")
	  @Length(max=100,message="日期类型名长度不能超过100个字符")
	  private String  district_code ;
	  
	  @ApiModelProperty(value="区域名称")
	  @Length(max=100,message="日期类型名长度不能超过100个字符")
	  private String  district_name;
	  
	  @ApiModelProperty(value="状态")
	  @Max(value=2,message="状态长度不能大于2个字符")
	  private Integer status;
	  
	  @ApiModelProperty(value="创建时间",hidden=true)
	  @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	  private Date create_date;

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
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
