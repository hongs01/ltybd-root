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
@ApiModel(value="轮趟")
@Entity
@Table(name = "op_algorithm")
public class Algorithm {
	@Id
	@ApiModelProperty(value="轮趟算法id")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer algorithm_id;
	
	@ApiModelProperty(value="轮趟名称")
	@Length(max=100,message="轮趟名称长度不能大于100个字符")
	private String algorithm_name;
	
	@ApiModelProperty(value="轮趟名称")
	private String algorithm_not;
	
	@ApiModelProperty(value="周期")
	private Integer cycle;
	
	@ApiModelProperty(value="轮趟方向")
	private String driection;
	
	@ApiModelProperty(value="类型")
	private Integer type;
	
	@ApiModelProperty(value="状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	
	@ApiModelProperty(value="创建时间(yyyy-MM-dd HH:mm:ss)",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;
	

	@ApiModelProperty(value="备注")
	@Length(max=1000,message="备注长度不能大于1000个字符")
	private String remark;

	public Integer getAlgorithm_id() {
		return algorithm_id;
	}

	public void setAlgorithm_id(Integer algorithm_id) {
		this.algorithm_id = algorithm_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAlgorithm_name() {
		return algorithm_name;
	}

	public void setAlgorithm_name(String algorithm_name) {
		this.algorithm_name = algorithm_name;
	}

	public String getAlgorithm_not() {
		return algorithm_not;
	}

	public void setAlgorithm_not(String algorithm_not) {
		this.algorithm_not = algorithm_not;
	}

	public String getDriection() {
		return driection;
	}

	public void setDriection(String driection) {
		this.driection = driection;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	} 
	

}
