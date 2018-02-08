package com.ltybd.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="员工信息展示实体")
public class EmployeeBean {
	@ApiModelProperty(value="员工id")
	private Integer employee_id;
	 
	@ApiModelProperty(value="员工姓名")
	private String employee_name;
	
	@ApiModelProperty(value="员工类型")
	private Integer employee_type;
	
	@ApiModelProperty(value="员工类型名称")
	private String employee_type_name;
	
	@ApiModelProperty(value="联系电话")
	private String phone_no;
	
	@ApiModelProperty(value="通讯地址")
	private String address;
	
	@ApiModelProperty(value="身份证")
	private String card_id;
	
	@ApiModelProperty(value="生日")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@ApiModelProperty(value="性别")
	private String sex;
	
	@ApiModelProperty(value="状态")
	private Integer status;
	
	@ApiModelProperty(value="集团ID")
	private Integer group_id;
	
	@ApiModelProperty(value="公司ID")
	private Integer company_id;
	
	@ApiModelProperty(value="部门ID")
	private Integer department_id;
	
	@ApiModelProperty(value="创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value="上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public Integer getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(Integer employee_type) {
		this.employee_type = employee_type;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
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

	public String getEmployee_type_name() {
		return employee_type_name;
	}

	public void setEmployee_type_name(String employee_type_name) {
		this.employee_type_name = employee_type_name;
	}

	
}
