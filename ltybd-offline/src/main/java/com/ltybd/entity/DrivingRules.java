package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * DrivingRules.java
 *
 * describe:
 * 
 * 2017年10月19日 上午11:49:09 created By chenq version 0.1
 *
 * 2017年10月19日 上午11:49:09 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "行车规则")
@Entity
@Table(name = "dm_driving_rule_dm")
public class DrivingRules {
	
	   @ApiModelProperty(value = "公交公司ID")
	   private String departmentId;
	   
	   @ApiModelProperty(value = "线路id")
	   private String lineId;
	   
	   @ApiModelProperty(value = "城市id")
	   private String  cityCode;
	   
	   @ApiModelProperty(value = "统计周期")
	   private String   calculatedDate;
	   
	   @ApiModelProperty(value = "0:单头调，1：双头调")
	   private int	reverseType;
	   
	   @ApiModelProperty(value = "1：上行，2：下行，数据类型需要跟调度统一")
	   private int	direction;
	   
	   @ApiModelProperty(value = "1：节假日:2：周末:3：工作日，默认为0")
	   private int 	dateType;
	   
	   @ApiModelProperty(value = "开始时间")
	   private String startTime;
	   
	   @ApiModelProperty(value = "结束时间")
	   private String  endTime;
	   
	   @ApiModelProperty(value = "发车间隔")
	   private int departureInterval;
	   
	   @ApiModelProperty(value = "预测车速")
	   private float predictedSpeed;
	   
	   @ApiModelProperty(value = "运营时长")
	   private int	operationDuration;
	   
	   @ApiModelProperty(value = "停车时间 单位：秒")
	   private int	 parkingTime;
	   
	   @ApiModelProperty(value = "最小配车数")
	   private int  smallestBus;
	   
	   @ApiModelProperty(value = "富余车辆")
	   private int  surplusBus;
	   
	   @ApiModelProperty(value = "客流采集天数")
	   private int  passengerCollectionDays;
	   
	   @ApiModelProperty(value = "是否跨天   1：跨天，2：未跨天")
	   private int  isInterDay;
	   
	   @ApiModelProperty(value = "标志 1：平峰，2：中峰，3：高峰")
	   private int  lineSigns;
	   
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
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public int getDepartureInterval() {
			return departureInterval;
		}
		public void setDepartureInterval(int departureInterval) {
			this.departureInterval = departureInterval;
		}
		public float getPredictedSpeed() {
			return predictedSpeed;
		}
		public void setPredictedSpeed(float predictedSpeed) {
			this.predictedSpeed = predictedSpeed;
		}
		public int getOperationDuration() {
			return operationDuration;
		}
		public void setOperationDuration(int operationDuration) {
			this.operationDuration = operationDuration;
		}
		public int getParkingTime() {
			return parkingTime;
		}
		public void setParkingTime(int parkingTime) {
			this.parkingTime = parkingTime;
		}
		public int getSmallestBus() {
			return smallestBus;
		}
		public void setSmallestBus(int smallestBus) {
			this.smallestBus = smallestBus;
		}
		public int getSurplusBus() {
			return surplusBus;
		}
		public void setSurplusBus(int surplusBus) {
			this.surplusBus = surplusBus;
		}
		public int getPassengerCollectionDays() {
			return passengerCollectionDays;
		}
		public void setPassengerCollectionDays(int passengerCollectionDays) {
			this.passengerCollectionDays = passengerCollectionDays;
		}
		public int getIsInterDay() {
			return isInterDay;
		}
		public void setIsInterDay(int isInterDay) {
			this.isInterDay = isInterDay;
		}
		public int getLineSigns() {
			return lineSigns;
		}
		public void setLineSigns(int lineSigns) {
			this.lineSigns = lineSigns;
		}
	   
}
