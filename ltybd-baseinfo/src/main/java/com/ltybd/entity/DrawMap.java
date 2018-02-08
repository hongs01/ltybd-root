package com.ltybd.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "线路绘制展示类")
public class DrawMap {
	
	@ApiModelProperty(value = "线路ID")
	@NotEmpty(message="线路ID不能为空")
	private Integer line_id;
	
	@ApiModelProperty(value = "线路名称")
	private String line_name;
	
	@ApiModelProperty(value = "线条宽度")
	@NotEmpty(message="线条宽度不能为空")
	private Integer width;
	
	@ApiModelProperty(value = "站点颜色")
	@NotEmpty(message="站点颜色不能为空")
	private String stationColor;
	
	@ApiModelProperty(value = "站点图标")
	@NotEmpty(message="站点图标不能为空")
	private String stationIcon;
	
	@ApiModelProperty(value = "是否显示站点")
	@NotEmpty(message="是否显示站点不能为空")
	private Integer isShowStation;
	
	@ApiModelProperty(value = "是否显示站名")
	@NotEmpty(message="是否显示站名不能为空")
	private Integer isShowStationName;
	
	@ApiModelProperty(value = "站点名颜色")
	@NotEmpty(message="站点名颜色不能为空")
	private String stationNameColor;
	
	@ApiModelProperty(value = "站点名字体")
	@NotEmpty(message="站点名字体不能为空")
	private String stationNamefont;
	
	@ApiModelProperty(value = "是否显示辅助点")
	@NotEmpty(message="是否显示辅助点不能为空")
	private String isShowPoint;
	
	@ApiModelProperty(value = "线条颜色")
	@NotEmpty(message="线条颜色不能为空")
	private String color;
	
	@ApiModelProperty(value = "线路辅助点对象集合")
	private List<LinePoint> f_list;
	
	@ApiModelProperty(value = "线路站点经纬度集合")
	private List<StationblockBean> z_list;
	
	@ApiModelProperty(value = "站台名称")
	private String bus_station_name;

	@ApiModelProperty(value = "方向")
	private String direction;
	
	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public String getLine_name() {
		return line_name;
	}

	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getStationColor() {
		return stationColor;
	}

	public void setStationColor(String stationColor) {
		this.stationColor = stationColor;
	}

	public String getStationIcon() {
		return stationIcon;
	}

	public void setStationIcon(String stationIcon) {
		this.stationIcon = stationIcon;
	}

	public Integer getIsShowStation() {
		return isShowStation;
	}

	public void setIsShowStation(Integer isShowStation) {
		this.isShowStation = isShowStation;
	}

	public Integer getIsShowStationName() {
		return isShowStationName;
	}

	public void setIsShowStationName(Integer isShowStationName) {
		this.isShowStationName = isShowStationName;
	}

	public String getStationNameColor() {
		return stationNameColor;
	}

	public void setStationNameColor(String stationNameColor) {
		this.stationNameColor = stationNameColor;
	}

	public String getStationNamefont() {
		return stationNamefont;
	}

	public void setStationNamefont(String stationNamefont) {
		this.stationNamefont = stationNamefont;
	}

	public String getIsShowPoint() {
		return isShowPoint;
	}

	public void setIsShowPoint(String isShowPoint) {
		this.isShowPoint = isShowPoint;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getBus_station_name() {
		return bus_station_name;
	}

	public void setBus_station_name(String bus_station_name) {
		this.bus_station_name = bus_station_name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<LinePoint> getF_list() {
		return f_list;
	}

	public void setF_list(List<LinePoint> f_list) {
		this.f_list = f_list;
	}

	public List<StationblockBean> getZ_list() {
		return z_list;
	}

	public void setZ_list(List<StationblockBean> z_list) {
		this.z_list = z_list;
	}


}