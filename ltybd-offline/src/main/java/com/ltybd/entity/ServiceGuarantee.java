package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * ServiceGuarantee.java
 *
 * describe:
 * 
 * 2017年10月17日 上午11:34:55 created By chenq version 0.1
 *
 * 2017年10月17日 上午11:34:55 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "服务保障能查询")
@Entity
@Table(name = "dm_service_guarantee_dm")
public class ServiceGuarantee {
	
	@ApiModelProperty(value = "统计周期")
	private String calculatedDate;
	
	@ApiModelProperty(value = "城市id")
	private String citycode;
	
	@ApiModelProperty(value = "时间类型")
	private int  timeType;
	
	@ApiModelProperty(value = "日期范围 30 60 90 365")
	private int stat_cycle;
	
	@ApiModelProperty(value = "线路id")
	private int routeId;
	
	@ApiModelProperty(value = "方向")
	private int lineDirection;
	
	@ApiModelProperty(value = "日期类型")
	private int dateType;
	
	@ApiModelProperty(value = "年月日")
	private String offdate;
	
	@ApiModelProperty(value = "24小时制")
	private String offtime;
	
	@ApiModelProperty(value = "站点ID")
	private double opStationMainId;
	
	@ApiModelProperty(value = "历史客流")
	private int passengerFlow;
	
	@ApiModelProperty(value = "公交公司ID")
	private String departmentid;

	public String getCalculatedDate() {
		return calculatedDate;
	}

	public void setCalculatedDate(String calculatedDate) {
		this.calculatedDate = calculatedDate;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public int getStat_cycle() {
		return stat_cycle;
	}

	public void setStat_cycle(int stat_cycle) {
		this.stat_cycle = stat_cycle;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public int getLineDirection() {
		return lineDirection;
	}

	public void setLineDirection(int lineDirection) {
		this.lineDirection = lineDirection;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public String getOffdate() {
		return offdate;
	}

	public void setOffdate(String offdate) {
		this.offdate = offdate;
	}

	public String getOfftime() {
		return offtime;
	}

	public void setOfftime(String offtime) {
		this.offtime = offtime;
	}

	public double getOpStationMainId() {
		return opStationMainId;
	}

	public void setOpStationMainId(double opStationMainId) {
		this.opStationMainId = opStationMainId;
	}

	public int getPassengerFlow() {
		return passengerFlow;
	}

	public void setPassengerFlow(int passengerFlow) {
		this.passengerFlow = passengerFlow;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	
}
