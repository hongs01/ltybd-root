package com.ltybd.entity;


import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "异常实体类")
@Entity
public class ResponseData {
	
	private String result;
	
	private String resPonse;
	
	private String resultMsg;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResPonse() {
		return resPonse;
	}

	public void setResPonse(String resPonse) {
		this.resPonse = resPonse;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	 
}
