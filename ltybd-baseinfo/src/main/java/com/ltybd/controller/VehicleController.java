package com.ltybd.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Vehicle;
import com.ltybd.entity.VehicleBean;
import com.ltybd.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * VehicleController.java
 *
 * describe:车辆信息
 * 
 * 2017年10月13日 下午3:28:20 created By Yancz version 0.1
 *
 * 2017年10月13日 下午3:28:20 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/vehicle", description = "车辆信息")
@RestController
@RequestMapping("/vehicle/")
@Validated
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	/**
	 * @param vehicle
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询车辆信息集合
	 * 2017年10月14日下午6:18:03 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询车辆信息集合 code:006001", produces = "application/json")
	@RequestMapping(value = "list", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "plate_no", dataType = "String", required = false, value = "车牌号,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(Vehicle vehicle, Integer pageNum, Integer pageSize, Boolean isPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (StringUtils.isEmpty(isPage)) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数isPage为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		} else {
			if(null == vehicle){
				vehicle = new Vehicle();
			}
			if(null == vehicle.getStatus()){
				vehicle.setStatus(0);
			}
			List<VehicleBean> list = new ArrayList<VehicleBean>();
			if (isPage) {
				if (StringUtils.isEmpty(pageNum)) {
					map.put("result", "1");
					map.put("resultMsg", "请求失败,isPage为true时,参数pageNum为必填项!");
					map.put("resPonse", "查询失败");
					return map;
				}
				if (StringUtils.isEmpty(pageSize)) {
					map.put("result", "1");
					map.put("resultMsg", "请求失败,isPage为true时,参数pageSize为必填项!");
					map.put("resPonse", "查询失败");
					return map;
				}
				if (pageNum.intValue() == 0)
					pageNum = 1;// 初始页码
				if (pageSize.intValue() == 0)
					pageSize = 15;// 初始每页条数
				Page<Vehicle> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = vehicleService.findListObj(vehicle);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = vehicleService.findListObj(vehicle);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * @param vechicle_id
	 * @return
	 * Map<String,Object>
	 * describe:查询车辆信息对象
	 * 2017年10月14日下午6:18:17 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询车辆信息对象 code:006002", produces = "application/json")
	@RequestMapping(value = "entity", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "vechicle_id", dataType = "int", required = false, value = "车辆ID,必填"), })
	@ResponseBody
	public Map<String, Object> findByVehicleId(
			@NotNull(message="vechicle_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer vechicle_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == vechicle_id) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数vechicle_id为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		Vehicle vehicle=new Vehicle();
		vehicle.setVechicle_id(vechicle_id);
		map.put("resPonse",vehicleService.findByVehicleId(vehicle)!=null?vehicleService.findByVehicleId(vehicle):"没有查找到对应数据!" );
		return map;
	}

	/**
	 * @param vehicle
	 * @return
	 * Map<String,Object>
	 * describe:更新车辆信息对象
	 * 2017年10月14日下午6:18:25 by Chenjw version 0.1
	 */
	@ApiOperation(value = "更新车辆信息对象 code:006003", produces = "application/json")
	@RequestMapping(value = "update", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateVechicleObj(@RequestBody @Validated Vehicle vehicle,BindingResult error) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if (error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors(); 
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
        }
		if (!StringUtils.isEmpty(vehicle.getVechicle_id()) ) {
			if(vehicleService.findByVehicleId(vehicle)!=null){
				vehicleService.update(vehicle);
				map.put("resPonse", "修改车辆信息成功!");
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse","没有查到此车辆对象无法更新");
				return map;
			}
		} else {
			vehicleService.insert(vehicle);
			map.put("resPonse", "保存车辆信息成功!");
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		
		return map;

	}

	/**
	 * @param vechicle_id
	 * @return
	 * Map<String,Object>
	 * describe:删除车辆信息对象
	 * 2017年10月14日下午6:18:34 by Chenjw version 0.1
	 */
	@ApiOperation(value = "删除车辆信息对象 code:006004", produces = "application/json")
	@RequestMapping(value = "delete", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "vechicle_id", dataType = "int", required = false, value = "车辆ID,必填"), })
	@ResponseBody
	public Map<String, Object> deleteVehicleObj(
			@NotNull(message="vechicle_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer vechicle_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == vechicle_id) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数vechicle_id为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		Vehicle vehicle=new Vehicle();
		vehicle.setVechicle_id(vechicle_id);
		int result = vehicleService.delete(vehicle);
		if (result > 0) {
			map.put("resPonse", "删除成功!");
		} else {
			map.put("resPonse", "删除失败!");
		}
		return map;
	}

	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量删除车辆信息对象
	 * 2017年10月14日下午6:18:45 by Chenjw version 0.1
	 */
	@ApiOperation(value = "批量删除车辆信息对象 code:006005", produces = "application/json")
	@RequestMapping(value = "deleteItems", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "车辆ID集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteItems(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (StringUtils.isEmpty(ids)) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数ids为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		int result = vehicleService.deleteItems(ids);
		 map.put("resultMsg", "失败的条数为"+(ids.split(",").length-result)+"条,成功条数为"+result+"条");
		if (result > 0) {
			map.put("resPonse", "删除成功!");
		} else {
			map.put("resPonse", "删除失败!");
		}
		return map;
	}
	
	/***
	 * 
	 * @param list
	 * @return Map<String,Object>
	 * @describe:批量更新车辆信息对象 code:030006006
	 * @2017年10月16日上午10:58:27 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新车辆信息对象 code:006006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Vehicle> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,请传参数");
			map.put("resPonse", "更新失败");
			return map;
		}
		int result = vehicleService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;

	}

}
