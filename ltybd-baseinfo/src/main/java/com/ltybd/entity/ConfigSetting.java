package com.ltybd.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="配置")
@Entity
@Table(name = "config_setting")
public class ConfigSetting {
	
	@Id
	@ApiModelProperty(value="配置ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer config_setting_id;
	
	@ApiModelProperty(value="高峰候车时长从1")
	private Integer wait_start_duration1;
	
	@ApiModelProperty(value="高峰候车时长从2")
	private Integer wait_start_duration2;
	
	@ApiModelProperty(value="高峰候车时长从3")
	private Integer wait_start_duration3;
	
	@ApiModelProperty(value="高峰候车时长到1")
	private Integer wait_end_duration1;
	
	@ApiModelProperty(value="高峰候车时长到2")
	private Integer wait_end_duration2;
	
	@ApiModelProperty(value="高峰候车时长到3")
	private Integer wait_end_duration3;
	
	@ApiModelProperty(value="平峰候车时长从1")
	private Integer flat_start_duration1;
	
	@ApiModelProperty(value="平峰候车时长从2")
	private Integer flat_start_duration2;
	
	@ApiModelProperty(value="平峰候车时长从3")
	private Integer flat_start_duration3;
	
	@ApiModelProperty(value="平峰候车时长到1")
	private Integer flat_end_duration1;
	
	@ApiModelProperty(value="平峰候车时长到2")
	private Integer flat_end_duration2;
	
	@ApiModelProperty(value="平峰候车时长到3")
	private Integer flat_end_duration3;
	
	@ApiModelProperty(value="低峰候车时长从1")
	private Integer low_start_duration1;
	
	@ApiModelProperty(value="低峰候车时长从2")
	private Integer low_start_duration2;
	
	@ApiModelProperty(value="低峰候车时长从3")
	private Integer low_start_duration3;
	
	@ApiModelProperty(value="低峰候车时长到1")
	private Integer low_end_duration1;
	
	@ApiModelProperty(value="低峰候车时长到2")
	private Integer low_end_duration2;
	
	@ApiModelProperty(value="低峰候车时长到3")
	private Integer low_end_duration3;
	
	@ApiModelProperty(value="高峰满意度分值1")
	private Integer peak_waiting_satisfaction1;
	
	@ApiModelProperty(value="高峰满意度分值2")
	private Integer peak_waiting_satisfaction2;
	
	@ApiModelProperty(value="高峰满意度分值3")
	private Integer peak_waiting_satisfaction3;
	
	@ApiModelProperty(value="平峰满意度分值1")
	private Integer flat_waiting_satisfaction1;
	
	@ApiModelProperty(value="平峰满意度分值2")
	private Integer flat_waiting_satisfaction2;
	
	@ApiModelProperty(value="平峰满意度分值3")
	private Integer flat_waiting_satisfaction3;
	
	@ApiModelProperty(value="低峰满意度分值1")
	private Integer low_waiting_satisfaction1;
	
	@ApiModelProperty(value="低峰满意度分值2")
	private Integer low_waiting_satisfaction2;
	
	@ApiModelProperty(value="低峰满意度分值3")
	private Integer low_waiting_satisfaction3;
	
	@ApiModelProperty(value="高峰满载率舒适度从1")
	private Integer peak_ride_start_duration1;
	
	@ApiModelProperty(value="高峰满载率舒适度从2")
	private Integer peak_ride_start_duration2;
	
	@ApiModelProperty(value="高峰满载率舒适度从3")
	private Integer peak_ride_start_duration3;
	
	@ApiModelProperty(value="高峰满载率舒适度到1")
	private Integer peak_ride_end_duration1;
	
	@ApiModelProperty(value="高峰满载率舒适度到2")
	private Integer peak_ride_end_duration2;
	
	@ApiModelProperty(value="高峰满载率舒适度到3")
	private Integer peak_ride_end_duration3;
	
	@ApiModelProperty(value="高峰满意度分值1")
	private Integer peak_ride_comfort1;
	
	@ApiModelProperty(value="高峰满意度分值2")
	private Integer peak_ride_comfort2;
	
	@ApiModelProperty(value="高峰满意度分值3")
	private Integer peak_ride_comfort3;
	
	@ApiModelProperty(value="平峰满载率舒适度从1")
	private Integer flat_ride_start_duration1;
	
	@ApiModelProperty(value="平峰满载率舒适度从2")
	private Integer flat_ride_start_duration2;
	
	@ApiModelProperty(value="平峰满载率舒适度从3")
	private Integer flat_ride_start_duration3;
	
	@ApiModelProperty(value="平峰满载率舒适度到1")
	private Integer flat_ride_end_duration1;
	
	@ApiModelProperty(value="平峰满载率舒适度到2")
	private Integer flat_ride_end_duration2;
	
	@ApiModelProperty(value="平峰满载率舒适度到3")
	private Integer flat_ride_end_duration3;
	
	@ApiModelProperty(value="平峰满意度分值1")
	private Integer flat_ride_comfort1;
	
	@ApiModelProperty(value="平峰满意度分值2")
	private Integer flat_ride_comfort2;
	
	@ApiModelProperty(value="平峰满意度分值3")
	private Integer flat_ride_comfort3;
	
	@ApiModelProperty(value="低峰满载率舒适度从1")
	private Integer low_ride_start_duration1;
	
	@ApiModelProperty(value="低峰满载率舒适度从2")
	private Integer low_ride_start_duration2;
	
	@ApiModelProperty(value="低峰满载率舒适度从3")
	private Integer low_ride_start_duration3;
	
	@ApiModelProperty(value="低峰满载率舒适度到1")
	private Integer low_ride_end_duration1;
	
	@ApiModelProperty(value="低峰满载率舒适度到2")
	private Integer low_ride_end_duration2;
	
	@ApiModelProperty(value="低峰满载率舒适度到3")
	private Integer low_ride_end_duration3;
	
	@ApiModelProperty(value="低峰满意度分值1")
	private Integer low_ride_comfort1;
	
	@ApiModelProperty(value="低峰满意度分值2")
	private Integer low_ride_comfort2;
	
	@ApiModelProperty(value="低峰满意度分值3")
	private Integer low_ride_comfort3;
	
	@ApiModelProperty(value="高峰额定满载率")
	private Integer peak_full_load;
	
	@ApiModelProperty(value="平峰额定满载率")
	private Integer flat_full_load;
	
	@ApiModelProperty(value="低峰额定满载率")
	private Integer low_full_load;
	
	@ApiModelProperty(value="定时任务设置")
	private Integer dispatch_ir_cron;
	
	@ApiModelProperty(value="是否选用通用日期行车规则")
	private Integer is_general;
	
	@ApiModelProperty(value="通用日期结束时间")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date end_time;
	
	@ApiModelProperty(value="通用日期开始时间")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date start_time;
	
	@ApiModelProperty(value="低峰峰段标识")
	private Integer marked_low_peak;
	
	@ApiModelProperty(value="高峰峰段标识")
	private Integer marked_peak;
	
	@ApiModelProperty(value="平峰峰段标识")
	private Integer marked_falt_peak;
	
	@ApiModelProperty(value="乘客满意度加权值，高峰从")
	private Integer peak_waiting;
	
	@ApiModelProperty(value="乘客满意度加权值，高峰到")
	private Integer peak_ride;
	
	@ApiModelProperty(value="乘客满意度加权值，平峰从")
	private Integer falt_waiting;
	
	@ApiModelProperty(value="乘客满意度加权值，平峰到")
	private Integer falt_ride;
	
	@ApiModelProperty(value="乘客满意度加权值，低峰从")
	private Integer low_waiting;
	
	@ApiModelProperty(value="乘客满意度加权值，低峰到")
	private Integer low_ride;

	public Integer getConfig_setting_id() {
		return config_setting_id;
	}

	public void setConfig_setting_id(Integer config_setting_id) {
		this.config_setting_id = config_setting_id;
	}

	public Integer getWait_start_duration1() {
		return wait_start_duration1;
	}

	public void setWait_start_duration1(Integer wait_start_duration1) {
		this.wait_start_duration1 = wait_start_duration1;
	}

	public Integer getWait_start_duration2() {
		return wait_start_duration2;
	}

	public void setWait_start_duration2(Integer wait_start_duration2) {
		this.wait_start_duration2 = wait_start_duration2;
	}

	public Integer getWait_start_duration3() {
		return wait_start_duration3;
	}

	public void setWait_start_duration3(Integer wait_start_duration3) {
		this.wait_start_duration3 = wait_start_duration3;
	}

	public Integer getWait_end_duration1() {
		return wait_end_duration1;
	}

	public void setWait_end_duration1(Integer wait_end_duration1) {
		this.wait_end_duration1 = wait_end_duration1;
	}

	public Integer getWait_end_duration2() {
		return wait_end_duration2;
	}

	public void setWait_end_duration2(Integer wait_end_duration2) {
		this.wait_end_duration2 = wait_end_duration2;
	}

	public Integer getWait_end_duration3() {
		return wait_end_duration3;
	}

	public void setWait_end_duration3(Integer wait_end_duration3) {
		this.wait_end_duration3 = wait_end_duration3;
	}

	public Integer getFlat_start_duration1() {
		return flat_start_duration1;
	}

	public void setFlat_start_duration1(Integer flat_start_duration1) {
		this.flat_start_duration1 = flat_start_duration1;
	}

	public Integer getFlat_start_duration2() {
		return flat_start_duration2;
	}

	public void setFlat_start_duration2(Integer flat_start_duration2) {
		this.flat_start_duration2 = flat_start_duration2;
	}

	public Integer getFlat_start_duration3() {
		return flat_start_duration3;
	}

	public void setFlat_start_duration3(Integer flat_start_duration3) {
		this.flat_start_duration3 = flat_start_duration3;
	}

	public Integer getFlat_end_duration1() {
		return flat_end_duration1;
	}

	public void setFlat_end_duration1(Integer flat_end_duration1) {
		this.flat_end_duration1 = flat_end_duration1;
	}

	public Integer getFlat_end_duration2() {
		return flat_end_duration2;
	}

	public void setFlat_end_duration2(Integer flat_end_duration2) {
		this.flat_end_duration2 = flat_end_duration2;
	}

	public Integer getFlat_end_duration3() {
		return flat_end_duration3;
	}

	public void setFlat_end_duration3(Integer flat_end_duration3) {
		this.flat_end_duration3 = flat_end_duration3;
	}

	public Integer getLow_start_duration1() {
		return low_start_duration1;
	}

	public void setLow_start_duration1(Integer low_start_duration1) {
		this.low_start_duration1 = low_start_duration1;
	}

	public Integer getLow_start_duration2() {
		return low_start_duration2;
	}

	public void setLow_start_duration2(Integer low_start_duration2) {
		this.low_start_duration2 = low_start_duration2;
	}

	public Integer getLow_start_duration3() {
		return low_start_duration3;
	}

	public void setLow_start_duration3(Integer low_start_duration3) {
		this.low_start_duration3 = low_start_duration3;
	}

	public Integer getLow_end_duration1() {
		return low_end_duration1;
	}

	public void setLow_end_duration1(Integer low_end_duration1) {
		this.low_end_duration1 = low_end_duration1;
	}

	public Integer getLow_end_duration2() {
		return low_end_duration2;
	}

	public void setLow_end_duration2(Integer low_end_duration2) {
		this.low_end_duration2 = low_end_duration2;
	}

	public Integer getLow_end_duration3() {
		return low_end_duration3;
	}

	public void setLow_end_duration3(Integer low_end_duration3) {
		this.low_end_duration3 = low_end_duration3;
	}

	public Integer getPeak_waiting_satisfaction1() {
		return peak_waiting_satisfaction1;
	}

	public void setPeak_waiting_satisfaction1(Integer peak_waiting_satisfaction1) {
		this.peak_waiting_satisfaction1 = peak_waiting_satisfaction1;
	}

	public Integer getPeak_waiting_satisfaction2() {
		return peak_waiting_satisfaction2;
	}

	public void setPeak_waiting_satisfaction2(Integer peak_waiting_satisfaction2) {
		this.peak_waiting_satisfaction2 = peak_waiting_satisfaction2;
	}

	public Integer getPeak_waiting_satisfaction3() {
		return peak_waiting_satisfaction3;
	}

	public void setPeak_waiting_satisfaction3(Integer peak_waiting_satisfaction3) {
		this.peak_waiting_satisfaction3 = peak_waiting_satisfaction3;
	}

	public Integer getFlat_waiting_satisfaction1() {
		return flat_waiting_satisfaction1;
	}

	public void setFlat_waiting_satisfaction1(Integer flat_waiting_satisfaction1) {
		this.flat_waiting_satisfaction1 = flat_waiting_satisfaction1;
	}

	public Integer getFlat_waiting_satisfaction2() {
		return flat_waiting_satisfaction2;
	}

	public void setFlat_waiting_satisfaction2(Integer flat_waiting_satisfaction2) {
		this.flat_waiting_satisfaction2 = flat_waiting_satisfaction2;
	}

	public Integer getFlat_waiting_satisfaction3() {
		return flat_waiting_satisfaction3;
	}

	public void setFlat_waiting_satisfaction3(Integer flat_waiting_satisfaction3) {
		this.flat_waiting_satisfaction3 = flat_waiting_satisfaction3;
	}

	public Integer getLow_waiting_satisfaction1() {
		return low_waiting_satisfaction1;
	}

	public void setLow_waiting_satisfaction1(Integer low_waiting_satisfaction1) {
		this.low_waiting_satisfaction1 = low_waiting_satisfaction1;
	}

	public Integer getLow_waiting_satisfaction2() {
		return low_waiting_satisfaction2;
	}

	public void setLow_waiting_satisfaction2(Integer low_waiting_satisfaction2) {
		this.low_waiting_satisfaction2 = low_waiting_satisfaction2;
	}

	public Integer getLow_waiting_satisfaction3() {
		return low_waiting_satisfaction3;
	}

	public void setLow_waiting_satisfaction3(Integer low_waiting_satisfaction3) {
		this.low_waiting_satisfaction3 = low_waiting_satisfaction3;
	}

	public Integer getPeak_ride_start_duration1() {
		return peak_ride_start_duration1;
	}

	public void setPeak_ride_start_duration1(Integer peak_ride_start_duration1) {
		this.peak_ride_start_duration1 = peak_ride_start_duration1;
	}

	public Integer getPeak_ride_start_duration2() {
		return peak_ride_start_duration2;
	}

	public void setPeak_ride_start_duration2(Integer peak_ride_start_duration2) {
		this.peak_ride_start_duration2 = peak_ride_start_duration2;
	}

	public Integer getPeak_ride_start_duration3() {
		return peak_ride_start_duration3;
	}

	public void setPeak_ride_start_duration3(Integer peak_ride_start_duration3) {
		this.peak_ride_start_duration3 = peak_ride_start_duration3;
	}

	public Integer getPeak_ride_end_duration1() {
		return peak_ride_end_duration1;
	}

	public void setPeak_ride_end_duration1(Integer peak_ride_end_duration1) {
		this.peak_ride_end_duration1 = peak_ride_end_duration1;
	}

	public Integer getPeak_ride_end_duration2() {
		return peak_ride_end_duration2;
	}

	public void setPeak_ride_end_duration2(Integer peak_ride_end_duration2) {
		this.peak_ride_end_duration2 = peak_ride_end_duration2;
	}

	public Integer getPeak_ride_end_duration3() {
		return peak_ride_end_duration3;
	}

	public void setPeak_ride_end_duration3(Integer peak_ride_end_duration3) {
		this.peak_ride_end_duration3 = peak_ride_end_duration3;
	}

	public Integer getPeak_ride_comfort1() {
		return peak_ride_comfort1;
	}

	public void setPeak_ride_comfort1(Integer peak_ride_comfort1) {
		this.peak_ride_comfort1 = peak_ride_comfort1;
	}

	public Integer getPeak_ride_comfort2() {
		return peak_ride_comfort2;
	}

	public void setPeak_ride_comfort2(Integer peak_ride_comfort2) {
		this.peak_ride_comfort2 = peak_ride_comfort2;
	}

	public Integer getPeak_ride_comfort3() {
		return peak_ride_comfort3;
	}

	public void setPeak_ride_comfort3(Integer peak_ride_comfort3) {
		this.peak_ride_comfort3 = peak_ride_comfort3;
	}

	public Integer getFlat_ride_start_duration1() {
		return flat_ride_start_duration1;
	}

	public void setFlat_ride_start_duration1(Integer flat_ride_start_duration1) {
		this.flat_ride_start_duration1 = flat_ride_start_duration1;
	}

	public Integer getFlat_ride_start_duration2() {
		return flat_ride_start_duration2;
	}

	public void setFlat_ride_start_duration2(Integer flat_ride_start_duration2) {
		this.flat_ride_start_duration2 = flat_ride_start_duration2;
	}

	public Integer getFlat_ride_start_duration3() {
		return flat_ride_start_duration3;
	}

	public void setFlat_ride_start_duration3(Integer flat_ride_start_duration3) {
		this.flat_ride_start_duration3 = flat_ride_start_duration3;
	}

	public Integer getFlat_ride_end_duration1() {
		return flat_ride_end_duration1;
	}

	public void setFlat_ride_end_duration1(Integer flat_ride_end_duration1) {
		this.flat_ride_end_duration1 = flat_ride_end_duration1;
	}

	public Integer getFlat_ride_end_duration2() {
		return flat_ride_end_duration2;
	}

	public void setFlat_ride_end_duration2(Integer flat_ride_end_duration2) {
		this.flat_ride_end_duration2 = flat_ride_end_duration2;
	}

	public Integer getFlat_ride_end_duration3() {
		return flat_ride_end_duration3;
	}

	public void setFlat_ride_end_duration3(Integer flat_ride_end_duration3) {
		this.flat_ride_end_duration3 = flat_ride_end_duration3;
	}

	public Integer getFlat_ride_comfort1() {
		return flat_ride_comfort1;
	}

	public void setFlat_ride_comfort1(Integer flat_ride_comfort1) {
		this.flat_ride_comfort1 = flat_ride_comfort1;
	}

	public Integer getFlat_ride_comfort2() {
		return flat_ride_comfort2;
	}

	public void setFlat_ride_comfort2(Integer flat_ride_comfort2) {
		this.flat_ride_comfort2 = flat_ride_comfort2;
	}

	public Integer getFlat_ride_comfort3() {
		return flat_ride_comfort3;
	}

	public void setFlat_ride_comfort3(Integer flat_ride_comfort3) {
		this.flat_ride_comfort3 = flat_ride_comfort3;
	}

	public Integer getLow_ride_start_duration1() {
		return low_ride_start_duration1;
	}

	public void setLow_ride_start_duration1(Integer low_ride_start_duration1) {
		this.low_ride_start_duration1 = low_ride_start_duration1;
	}

	public Integer getLow_ride_start_duration2() {
		return low_ride_start_duration2;
	}

	public void setLow_ride_start_duration2(Integer low_ride_start_duration2) {
		this.low_ride_start_duration2 = low_ride_start_duration2;
	}

	public Integer getLow_ride_start_duration3() {
		return low_ride_start_duration3;
	}

	public void setLow_ride_start_duration3(Integer low_ride_start_duration3) {
		this.low_ride_start_duration3 = low_ride_start_duration3;
	}

	public Integer getLow_ride_end_duration1() {
		return low_ride_end_duration1;
	}

	public void setLow_ride_end_duration1(Integer low_ride_end_duration1) {
		this.low_ride_end_duration1 = low_ride_end_duration1;
	}

	public Integer getLow_ride_end_duration2() {
		return low_ride_end_duration2;
	}

	public void setLow_ride_end_duration2(Integer low_ride_end_duration2) {
		this.low_ride_end_duration2 = low_ride_end_duration2;
	}

	public Integer getLow_ride_end_duration3() {
		return low_ride_end_duration3;
	}

	public void setLow_ride_end_duration3(Integer low_ride_end_duration3) {
		this.low_ride_end_duration3 = low_ride_end_duration3;
	}

	public Integer getLow_ride_comfort1() {
		return low_ride_comfort1;
	}

	public void setLow_ride_comfort1(Integer low_ride_comfort1) {
		this.low_ride_comfort1 = low_ride_comfort1;
	}

	public Integer getLow_ride_comfort2() {
		return low_ride_comfort2;
	}

	public void setLow_ride_comfort2(Integer low_ride_comfort2) {
		this.low_ride_comfort2 = low_ride_comfort2;
	}

	public Integer getLow_ride_comfort3() {
		return low_ride_comfort3;
	}

	public void setLow_ride_comfort3(Integer low_ride_comfort3) {
		this.low_ride_comfort3 = low_ride_comfort3;
	}

	public Integer getPeak_full_load() {
		return peak_full_load;
	}

	public void setPeak_full_load(Integer peak_full_load) {
		this.peak_full_load = peak_full_load;
	}

	public Integer getFlat_full_load() {
		return flat_full_load;
	}

	public void setFlat_full_load(Integer flat_full_load) {
		this.flat_full_load = flat_full_load;
	}

	public Integer getLow_full_load() {
		return low_full_load;
	}

	public void setLow_full_load(Integer low_full_load) {
		this.low_full_load = low_full_load;
	}

	public Integer getDispatch_ir_cron() {
		return dispatch_ir_cron;
	}

	public void setDispatch_ir_cron(Integer dispatch_ir_cron) {
		this.dispatch_ir_cron = dispatch_ir_cron;
	}

	public Integer getIs_general() {
		return is_general;
	}

	public void setIs_general(Integer is_general) {
		this.is_general = is_general;
	}

	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Integer getMarked_low_peak() {
		return marked_low_peak;
	}

	public void setMarked_low_peak(Integer marked_low_peak) {
		this.marked_low_peak = marked_low_peak;
	}

	public Integer getMarked_peak() {
		return marked_peak;
	}

	public void setMarked_peak(Integer marked_peak) {
		this.marked_peak = marked_peak;
	}

	public Integer getMarked_falt_peak() {
		return marked_falt_peak;
	}

	public void setMarked_falt_peak(Integer marked_falt_peak) {
		this.marked_falt_peak = marked_falt_peak;
	}

	public Integer getPeak_waiting() {
		return peak_waiting;
	}

	public void setPeak_waiting(Integer peak_waiting) {
		this.peak_waiting = peak_waiting;
	}

	public Integer getPeak_ride() {
		return peak_ride;
	}

	public void setPeak_ride(Integer peak_ride) {
		this.peak_ride = peak_ride;
	}

	public Integer getFalt_waiting() {
		return falt_waiting;
	}

	public void setFalt_waiting(Integer falt_waiting) {
		this.falt_waiting = falt_waiting;
	}

	public Integer getFalt_ride() {
		return falt_ride;
	}

	public void setFalt_ride(Integer falt_ride) {
		this.falt_ride = falt_ride;
	}

	public Integer getLow_waiting() {
		return low_waiting;
	}

	public void setLow_waiting(Integer low_waiting) {
		this.low_waiting = low_waiting;
	}

	public Integer getLow_ride() {
		return low_ride;
	}

	public void setLow_ride(Integer low_ride) {
		this.low_ride = low_ride;
	}

	
}
