package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * PassflowLine.java
 *
 * describe:
 * 
 * 2017年10月17日 上午9:22:37 created By chenq version 0.1
 *
 * 2017年10月17日 上午9:22:37 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "线路分时客流")
@Entity
@Table(name = "dm_passflow_line_dm")
public class PassflowLine {
	
	
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
	private int direction;
	
	@ApiModelProperty(value = "日期类型")
	private int dateType;
	
	@ApiModelProperty(value = "年月日")
	private String offdate;
	
	@ApiModelProperty(value = "24小时制")
	private String offtime;
	
	@ApiModelProperty(value = "预测客流")
	private int predictionPassengerNum;
	
	@ApiModelProperty(value = "历史客流")
	private int historyPassengerNum;
	
	@ApiModelProperty(value = "侯车满意度")
	private double waitingSatisfaction;
	
	@ApiModelProperty(value = "乘车舒适满意度")
	private double rideSatisfaction;
	
	@ApiModelProperty(value = "滞站客流(等车人数)")
	public double stagnantTraffic; 
	
	@ApiModelProperty(value = "公交公司ID")
	public int departmentid;

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

	public int getPredictionPassengerNum() {
		return predictionPassengerNum;
	}

	public void setPredictionPassengerNum(int predictionPassengerNum) {
		this.predictionPassengerNum = predictionPassengerNum;
	}

	public int getHistoryPassengerNum() {
		return historyPassengerNum;
	}

	public void setHistoryPassengerNum(int historyPassengerNum) {
		this.historyPassengerNum = historyPassengerNum;
	}

	public double getWaitingSatisfaction() {
		return waitingSatisfaction;
	}

	public void setWaitingSatisfaction(double waitingSatisfaction) {
		this.waitingSatisfaction = waitingSatisfaction;
	}

	public double getRideSatisfaction() {
		return rideSatisfaction;
	}

	public void setRideSatisfaction(double rideSatisfaction) {
		this.rideSatisfaction = rideSatisfaction;
	}

	public double getStagnantTraffic() {
		return stagnantTraffic;
	}

	public void setStagnantTraffic(double stagnantTraffic) {
		this.stagnantTraffic = stagnantTraffic;
	}

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	
}
