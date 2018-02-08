package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="班制与班次关系表")
@Entity
@Table(name = "op_shift_classes")
public class ShiftClasses {
	@Id
	@ApiModelProperty(value="主键id",hidden=true)
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer seq_id;
	
	@ApiModelProperty(value="班制ID")
	@NotNull(message="班制ID不能为空")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer shift_id;
	
	@ApiModelProperty(value="班次ID")
	@NotNull(message="班次ID不能为空")
	@Min(value=1,message="必须为大于或者等于1的正整数")
	private Integer classes_id; 
	
	@ApiModelProperty(value="创建时间(yyyy-MM-dd HH:mm:ss)",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_date;

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

	public Integer getShift_id() {
		return shift_id;
	}

	public void setShift_id(Integer shift_id) {
		this.shift_id = shift_id;
	}

	public Integer getClasses_id() {
		return classes_id;
	}

	public void setClasses_id(Integer classes_id) {
		this.classes_id = classes_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
	
}
