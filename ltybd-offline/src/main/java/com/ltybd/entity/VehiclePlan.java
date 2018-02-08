package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * VehiclePlan.java
 *
 * describe:
 * 
 * 2017年10月19日 下午3:06:22 created By chenq version 0.1
 *
 * 2017年10月19日 下午3:06:22 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "配车方案")
@Entity
@Table(name = "dm_vehicle_plan_dm")
public class VehiclePlan {
	
	@ApiModelProperty(value = "公交公司ID")
    private String  departmentId;
	
	@ApiModelProperty(value = "线路id")
    private String  lineId;
	
	@ApiModelProperty(value = "城市id")
    private String  cityCode;
	
	@ApiModelProperty(value = "统计周期")
    private String calculatedDate;
	
	@ApiModelProperty(value = "0:单头调，1：双头调")
    private int reverseType;
	
	@ApiModelProperty(value = "1：上行，2：下行，数据类型需要跟调度统一")
    private int direction;
	
	@ApiModelProperty(value = "日期类型 1：节假日:2：周末:3：工作日，默认为0")
    private int dateType;
	
	@ApiModelProperty(value = "运营车数量")
    private int numberOperatingVehicles;
	
	@ApiModelProperty(value = "机动车数量")
    private int numberMotorVehicle;
	
	@ApiModelProperty(value = "核载人数")
    private int mannedTotal;
	
	@ApiModelProperty(value = "车型：1:中巴，2：大巴，3,：小巴")
    private int modelsType;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCalculatedDate() {
		return calculatedDate;
	}

	public void setCalculatedDate(String calculatedDate) {
		this.calculatedDate = calculatedDate;
	}

	public int getReverseType() {
		return reverseType;
	}

	public void setReverseType(int reverseType) {
		this.reverseType = reverseType;
	}

	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public int getNumberOperatingVehicles() {
		return numberOperatingVehicles;
	}

	public void setNumberOperatingVehicles(int numberOperatingVehicles) {
		this.numberOperatingVehicles = numberOperatingVehicles;
	}

	public int getNumberMotorVehicle() {
		return numberMotorVehicle;
	}

	public void setNumberMotorVehicle(int numberMotorVehicle) {
		this.numberMotorVehicle = numberMotorVehicle;
	}

	public int getMannedTotal() {
		return mannedTotal;
	}

	public void setMannedTotal(int mannedTotal) {
		this.mannedTotal = mannedTotal;
	}

	public int getModelsType() {
		return modelsType;
	}

	public void setModelsType(int modelsType) {
		this.modelsType = modelsType;
	}
	
}
