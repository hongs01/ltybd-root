package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Stationblock.java
 *
 * describe:站台信息
 * 
 * 2017年10月12日 下午1:58:01 created By Yancz version 0.1
 *
 * 2017年10月12日 下午1:58:01 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value = "站台信息")
@Entity
@Table(name = "op_stationblock")
public class Stationblock {

	@Id
	@ApiModelProperty(value = "站台ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer bus_station_code;

	@ApiModelProperty(value = "站台名称")
	/*@NotNull(message="站台名称不能为空")*/
	private String bus_station_name;

	@ApiModelProperty(value = "站台类型")
	@NotNull(message="站台类型不能为空")
	private String station_type;

	@ApiModelProperty(value = "状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;

	@ApiModelProperty(value = "区域ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer district_id;

	@ApiModelProperty(value = "方位")
	@NotNull(message="方位不能为空")
	private String orientation;

	@ApiModelProperty(value = "进站方位角")
	@NotNull(message="进站方位角不能为空")
	private Integer enter_azimuth;

	@ApiModelProperty(value = "出站方位角")
	@NotNull(message="出站方位角不能为空")
	private Integer leave_azimuth;

	@ApiModelProperty(value = "站台经度")
	@NotNull(message="站台经度不能为空")
	private Double longitude;

	@ApiModelProperty(value = "站台纬度")
	@NotNull(message="站台纬度不能为空")
	private Double latiude;

	@ApiModelProperty(value = "进站经度")
	@NotNull(message="进站经度不能为空")
	private Double enter_longitude;

	@ApiModelProperty(value = "进站纬度")
	@NotNull(message="进站纬度不能为空")
	private Double enter_latitude;

	@ApiModelProperty(value = "出站经度")
	@NotNull(message="出站经度不能为空")
	private Double leave_longitude;

	@ApiModelProperty(value = "出站纬度")
	@NotNull(message="出站纬度不能为空")
	private Double leave_latitude;

	@ApiModelProperty(value = "备注")
	@Length(max=1000,message="备注长度不能大于1000个字符")
	private String remark;

	@ApiModelProperty(value = "创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	@ApiModelProperty(value = "上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;
	
	@ApiModelProperty(value="站台状况")
	@Length(max=100,message="站台状况长度不能超过100个字符")
	private String station_condition ;
	
	@ApiModelProperty(value="电子站牌编号")
	@Length(max=100,message="电子站牌编号长度不能超过100个字符")
	private String electronic_bus_board_number ;
	
	@ApiModelProperty(value="所属道路")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer road_id;
	
	@ApiModelProperty(value="地址")
	@Length(max=100,message="地址长度不能超过100个字符")
	private String address;
	
	@ApiModelProperty(value="附近地点名称")
	@Length(max=100,message="附近地点名称长度不能超过100个字符")
	private String  nearby;              

	public Integer getBus_station_code() {
		return bus_station_code;
	}

	public void setBus_station_code(Integer bus_station_code) {
		this.bus_station_code = bus_station_code;
	}

	public String getBus_station_name() {
		return bus_station_name;
	}

	public void setBus_station_name(String bus_station_name) {
		this.bus_station_name = bus_station_name;
	}

	public String getStation_type() {
		return station_type;
	}

	public void setStation_type(String station_type) {
		this.station_type = station_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public Integer getEnter_azimuth() {
		return enter_azimuth;
	}

	public void setEnter_azimuth(Integer enter_azimuth) {
		this.enter_azimuth = enter_azimuth;
	}

	public Integer getLeave_azimuth() {
		return leave_azimuth;
	}

	public void setLeave_azimuth(Integer leave_azimuth) {
		this.leave_azimuth = leave_azimuth;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatiude() {
		return latiude;
	}

	public void setLatiude(Double latiude) {
		this.latiude = latiude;
	}

	public Double getEnter_longitude() {
		return enter_longitude;
	}

	public void setEnter_longitude(Double enter_longitude) {
		this.enter_longitude = enter_longitude;
	}

	public Double getEnter_latitude() {
		return enter_latitude;
	}

	public void setEnter_latitude(Double enter_latitude) {
		this.enter_latitude = enter_latitude;
	}

	public Double getLeave_longitude() {
		return leave_longitude;
	}

	public void setLeave_longitude(Double leave_longitude) {
		this.leave_longitude = leave_longitude;
	}

	public Double getLeave_latitude() {
		return leave_latitude;
	}

	public void setLeave_latitude(Double leave_latitude) {
		this.leave_latitude = leave_latitude;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getLast_modified_time() {
		return last_modified_time;
	}

	public void setLast_modified_time(Date last_modified_time) {
		this.last_modified_time = last_modified_time;
	}

	public String getElectronic_bus_board_number() {
		return electronic_bus_board_number;
	}

	public void setElectronic_bus_board_number(String electronic_bus_board_number) {
		this.electronic_bus_board_number = electronic_bus_board_number;
	}

	public Integer getRoad_id() {
		return road_id;
	}

	public void setRoad_id(Integer road_id) {
		this.road_id = road_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNearby() {
		return nearby;
	}

	public void setNearby(String nearby) {
		this.nearby = nearby;
	}
	
}
