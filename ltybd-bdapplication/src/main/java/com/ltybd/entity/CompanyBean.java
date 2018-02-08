package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="公司展示实体")
public class CompanyBean {
	@ApiModelProperty(value="公司id")
	@Id
	private Integer company_id;
	
	@ApiModelProperty(value="公司名称")
	private String company_name;
	
	@ApiModelProperty(value="父公司ID")
	private Integer parent_company_id;
	
	@ApiModelProperty(value="父公司名称")
	private String parent_company_name;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@ApiModelProperty(value="状态")
	private Integer status;
	
	@ApiModelProperty(value="创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value="上次修改时间",hidden=true)
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
	

	public String getParent_company_name() {
		return parent_company_name;
	}

	public void setParent_company_name(String parent_company_name) {
		this.parent_company_name = parent_company_name;
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
