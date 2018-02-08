package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * PassSat.java
 *
 * describe:
 * 
 * 2017年10月16日 下午6:44:56 created By chenq version 0.1
 *
 * 2017年10月16日 下午6:44:56 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "乘客满意度信息")
@Entity
@Table(name = "dm_pass_sat_dm")
public class PassSat {
	
	@ApiModelProperty(value = "统计周期")
	private String calculatedDate;
	
	@ApiModelProperty(value = "城市id")
	private String citycode;
	
	@ApiModelProperty(value = "时间类型")
	private int  timeType;
	
	@ApiModelProperty(value = "日期范围 30 60 90 365")
	private int stat_cycle;
	
	@ApiModelProperty(value = "线路id")
	private String lineid;
	
	@ApiModelProperty(value = "方向")
	private int lineDirection;
	
	@ApiModelProperty(value = "日期类型")
	private int dateType;
	
	@ApiModelProperty(value = "年月日")
	private String offdate;
	
	@ApiModelProperty(value = "24小时制")
	private String offtime;
	
	@ApiModelProperty(value = "公交公司ID")
	private String departmentid;
	
	@ApiModelProperty(value = "上车人数")
	private int upnumber;
	
	@ApiModelProperty(value = "下车人数")
	private int downnumber;
	
	@ApiModelProperty(value = "平均发车间隔")
	private int averagestartinterval;
	
	@ApiModelProperty(value = "侯车满意度")
	private double waitingsatisfaction;
	
	@ApiModelProperty(value = "舒适满意度")
	private double comfortSatisfaction;

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

	public String getLineid() {
		return lineid;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
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

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public int getUpnumber() {
		return upnumber;
	}

	public void setUpnumber(int upnumber) {
		this.upnumber = upnumber;
	}

	public int getDownnumber() {
		return downnumber;
	}

	public void setDownnumber(int downnumber) {
		this.downnumber = downnumber;
	}

	public int getAveragestartinterval() {
		return averagestartinterval;
	}

	public void setAveragestartinterval(int averagestartinterval) {
		this.averagestartinterval = averagestartinterval;
	}

	public double getWaitingsatisfaction() {
		return waitingsatisfaction;
	}

	public void setWaitingsatisfaction(double waitingsatisfaction) {
		this.waitingsatisfaction = waitingsatisfaction;
	}

	public double getComfortSatisfaction() {
		return comfortSatisfaction;
	}

	public void setComfortSatisfaction(double comfortSatisfaction) {
		this.comfortSatisfaction = comfortSatisfaction;
	}

	
}
