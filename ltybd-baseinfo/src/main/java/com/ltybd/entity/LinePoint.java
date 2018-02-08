package com.ltybd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangquan
 *
 *2017年11月9日
 */
@ApiModel(value = "线路辅助点")
@Entity
@Table(name = "op_line_point")
public class LinePoint {

	@Id
	@ApiModelProperty(value = "线路辅助点ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer point_id;
	
	@ApiModelProperty(value = "线路ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer line_id;

	@ApiModelProperty(value = "经度")
	private  Double longitude;
	
	@ApiModelProperty(value = "纬度")
	private Double latitude;
	
	@ApiModelProperty(value = "方向")
	private Integer direction;

	public Integer getPoint_id() {
		return point_id;
	}

	public void setPoint_id(Integer point_id) {
		this.point_id = point_id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	
}
