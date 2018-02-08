package com.ltybd.entity;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "站台信息展示实体")
public class StationblockBean {
	@ApiModelProperty(value = "站台ID")
	private Integer bus_station_code;

	@ApiModelProperty(value = "站台名称")
	private String bus_station_name;

	@ApiModelProperty(value = "站台类型")
	private String station_type;
	
	@ApiModelProperty(value = "站台类型名称")
	private String station_type_name;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "区域ID")
	private Integer district_id;
	
	@ApiModelProperty(value = "区域名称")
	private String area_name;

	@ApiModelProperty(value = "方位")
	private String orientation;

	@ApiModelProperty(value = "进站方位角")
	private Integer enter_azimuth;

	@ApiModelProperty(value = "出站方位角")
	private Integer leave_azimuth;

	@ApiModelProperty(value = "站台经度")
	private Double longitude;

	@ApiModelProperty(value = "站台纬度")
	private Double latiude;

	@ApiModelProperty(value = "进站经度")
	private Double enter_longitude;

	@ApiModelProperty(value = "进站纬度")
	private Double enter_latitude;

	@ApiModelProperty(value = "出站经度")
	private Double leave_longitude;

	@ApiModelProperty(value = "出站纬度")
	private Double leave_latitude;

	@ApiModelProperty(value = "备注")
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

	public String getStation_type_name() {
		return station_type_name;
	}

	public void setStation_type_name(String station_type_name) {
		this.station_type_name = station_type_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
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

	public String getStation_condition() {
		return station_condition;
	}

	public void setStation_condition(String station_condition) {
		this.station_condition = station_condition;
	}
	
	
	
}
