package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="平台接入")
@Entity
@Table(name = "op_cloud_server_config")
public class CloudServerConfig {
	
	@Id
	@ApiModelProperty(value="平台接入ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer config_id;
	
	@ApiModelProperty(value="访问秘钥")
	@NotNull(message="访问秘钥不能为空")
	private Integer access_key;
	
	@ApiModelProperty(value="ip地址")
	@NotNull(message="ip地址不能为空")
	@Length(max=32,message="ip地址长度不能大于32个字符")
	private String name;
	
	@ApiModelProperty(value="端口号")
	@NotNull(message="端口号不能为空")
	private Integer port;
	
	@ApiModelProperty(value="平台接入类型")
	@Max(value=2,message="平台接入类型长度不能大于2个字符")
	private Integer type;

	public Integer getConfig_id() {
		return config_id;
	}

	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}

	public Integer getAccess_key() {
		return access_key;
	}

	public void setAccess_key(Integer access_key) {
		this.access_key = access_key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
