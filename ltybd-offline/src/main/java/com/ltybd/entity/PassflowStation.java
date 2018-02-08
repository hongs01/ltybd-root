package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * PassflowStation.java
 *
 * describe:
 * 
 * 2017年10月17日 上午9:22:37 created By chenq version 0.1
 *
 * 2017年10月17日 上午9:22:37 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "站点分时客流")
@Entity
@Table(name = "dm_passflow_station_dm")
public class PassflowStation {
	
	@ApiModelProperty(value = "统计周期")
    private String calculatedDate; 
	
	@ApiModelProperty(value = "城市id")
    private String citycode;
	
	@ApiModelProperty(value = "时间类型")
	private int  timeType;
	
	@ApiModelProperty(value = "日期范围 30 60 90 365")
	private int stat_cycle;
	
	@ApiModelProperty(value = "线路id")
	private int lineid;
	
	@ApiModelProperty(value = "方向")
	private int direction;
	
	@ApiModelProperty(value = "日期类型")
	private int dateType;
	
	@ApiModelProperty(value = "年月日")
	private String offdate;
	
	@ApiModelProperty(value = "24小时制")
	private String offtime;
	
	@ApiModelProperty(value = "站点名称")
	private String stationName;
	
	@ApiModelProperty(value = "站点id")
	private int bus_station_code;
	
	@ApiModelProperty(value = "人次，上车人数")
	private int passengerFlow;

}
