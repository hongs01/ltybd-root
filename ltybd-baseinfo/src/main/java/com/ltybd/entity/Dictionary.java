package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Dictionary.java
 *
 * describe:数据字典
 * 
 * 2017年10月12日 上午10:42:45 created By Chejw version 0.1
 *
 * 2017年10月12日 上午10:42:45 modifyed By Chejw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value="数据字典")
@Entity
@Table(name = "dict_item")
public class Dictionary {
	
	@Id
	@ApiModelProperty(value="字典id")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer dict_id;
	
	@ApiModelProperty(value="租Id")
	/*@NotEmpty(message="租Id不能为空")*/
	private String group_id;
	
	@ApiModelProperty(value="字典名称")
	/*@NotEmpty(message="字典名称不能为空")*/
	@Length(max=40,message="字典名称长度不能超过40个字符")
	private String dict_name;

	public Integer getDict_id() {
		return dict_id;
	}

	public void setDict_id(Integer dict_id) {
		this.dict_id = dict_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getDict_name() {
		return dict_name;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	
	
}
