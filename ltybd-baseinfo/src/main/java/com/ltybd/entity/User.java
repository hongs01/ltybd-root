package com.ltybd.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@ApiModel(value = "用户")
@Entity
@Table(name = "op_user")
public class User {

	@Id
	@ApiModelProperty(value = "用户ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	@NotEmpty(message="用户ID不能为空")
	private Integer user_id;
	
	@ApiModelProperty(value = "角色ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	@NotEmpty(message="线路名称不能为空")
	private Integer role_id;
	
	@ApiModelProperty(value = "用户姓名")
	@NotEmpty(message="线路名称不能为空")
	private String user_name;
	
	@ApiModelProperty(value = "登录名")
	@NotEmpty(message="登录名不能为空")
	private String login_name;
	
	@ApiModelProperty(value = "密码")
	@NotEmpty(message="密码不能为空")
	private String password;
	
	@ApiModelProperty(value = "状态")
	private Integer status;
	
	@ApiModelProperty(value = "创建日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
