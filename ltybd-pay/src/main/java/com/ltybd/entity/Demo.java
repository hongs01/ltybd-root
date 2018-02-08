package com.ltybd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="实例实体类")
public class Demo implements Serializable{
	
	@ApiModelProperty(value="名字")
	private String name;
	
	@ApiModelProperty(value="名字1")
	private String name1;
	
	@ApiModelProperty(value="名字2")
	private String name2;
	
	@ApiModelProperty(value="名字3")
	private String name3;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	
	
	
}
