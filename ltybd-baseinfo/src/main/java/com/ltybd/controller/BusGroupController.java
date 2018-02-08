package com.ltybd.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltybd.entity.BusGroup;
import com.ltybd.entity.BusGroupBean;
import com.ltybd.service.BusGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * BusGroupController.java
 *
 * describe:班组信息
 * 
 * 2017年11月10日 下午4:35:38 created By Chenjw version 0.1
 *
 * 2017年11月10日 下午4:35:38 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/busGroup", description = "班组信息")
@RestController
@RequestMapping("/busGroup/")
@Validated
public class BusGroupController {
	@Autowired
	private BusGroupService busGroupService;
	
	@ApiOperation(value = "查询班组信息对象  code:016002", produces ="application/json")
	@RequestMapping(value="object",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "group_id", dataType = "int", required = false, value = "班组ID,必填,大于等于1正整数")})
	public Map<String, Object> findByBusGroupId(
			@NotNull(message="group_id不能为空") 
			@Min(value=1,message="必须为大于或者等于1的正整数") 
			@RequestParam Integer group_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",busGroupService.findById(group_id)!=null?busGroupService.findById(group_id):"没有查找到对应数据!" );
		return map;
	}
	
	@ApiOperation(value = "插入班组信息对象  code:025003", produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insert(@RequestBody @Validated BusGroup busGroup,BindingResult result){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		if(StringUtils.isEmpty(busGroup.getStatus())){
    			busGroup.setStatus(0);
    		}
    		busGroup.setCreate_date(new Date());
    		busGroupService.insert(busGroup);
    		map.put("resPonse", "新增班次成功!");
        }
		return map;
	}
	
	@ApiOperation(value = "修改 班制信息对象  code:016004", produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> update(@RequestBody @Validated BusGroupBean busGroup,BindingResult result){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", "修改班次成功!");
    		if(busGroupService.findById(busGroup.getGroup_id())!=null){
    			BusGroup obj=new BusGroup();
    			obj.setGroup_id(busGroup.getGroup_id());
    			obj.setName(busGroup.getName());
    			obj.setBus_driver_algorithm_id(busGroup.getBus_driver_algorithm_id());
    			obj.setBus_algorithm_id(busGroup.getBus_algorithm_id());
    			obj.setLine_id(busGroup.getLine_id());
    			obj.setShift_id(busGroup.getShift_id());
    			obj.setDirection(busGroup.getDirection());
    			obj.setStatus(busGroup.getStatus());
    			busGroupService.update(obj);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此班制对象无法更新");
				return map;
			}
        }
		return map;
	}
	
	
}
