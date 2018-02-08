package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="公司")
@Entity
@Table(name = "op_company")
public class Company {
	
	@Id
	@ApiModelProperty(value="公司id")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer company_id;
	
	@ApiModelProperty(value="公司名称")
	/*@NotNull(message="公司名称不能为空")*/
	@Length(max=100,message="公司名称长度不能大于100个字符")
	private String company_name;
	
	@ApiModelProperty(value="父公司ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer parent_company_id;
	
	@ApiModelProperty(value="备注")
	@Length(max=1000,message="备注长度不能大于1000个字符")
	private String remark;
	
	@ApiModelProperty(value="状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	@ApiModelProperty(value="创建时间(yyyy-MM-dd HH:mm:ss)",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value="上次修改时间(yyyy-MM-dd HH:mm:ss)",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Integer getParent_company_id() {
		return parent_company_id;
	}

	public void setParent_company_id(Integer parent_company_id) {
		this.parent_company_id = parent_company_id;
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
