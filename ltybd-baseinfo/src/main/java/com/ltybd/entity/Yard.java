package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="车场实体")
@Entity
@Table(name = "op_yard")
public class Yard {
	@Id
	@ApiModelProperty(value="车场id")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer yard_id;
	
	@ApiModelProperty(value="车场名称")
	@Length(max=50,message="车场名称长度不能大于50个字符")
	private String yard_name;
	
	@ApiModelProperty(value="方向")
	@Length(max=50,message="方向长度不能大于50个字符")
	private String direction;
	
	@ApiModelProperty(value="是否是发车场")
	private Boolean is_yard;
	
	@ApiModelProperty(value="车场状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	@ApiModelProperty(value="创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;

	public Integer getYard_id() {
		return yard_id;
	}

	public void setYard_id(Integer yard_id) {
		this.yard_id = yard_id;
	}

	public String getYard_name() {
		return yard_name;
	}

	public void setYard_name(String yard_name) {
		this.yard_name = yard_name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Boolean getIs_yard() {
		return is_yard;
	}

	public void setIs_yard(Boolean is_yard) {
		this.is_yard=is_yard;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "Yard [yard_id=" + yard_id + ", yard_name=" + yard_name + ", direction=" + direction + ", is_yard="
				+ is_yard + ", status=" + status + ", create_date=" + create_date + "]";
	}

	
}
