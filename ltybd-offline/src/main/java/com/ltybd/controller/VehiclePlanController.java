package com.ltybd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltybd.service.VehiclePlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;



/**
 * VehiclePlanController.java
 *
 * describe:
 * 
 * 2017年10月19日 下午3:06:09 created By chenq version 0.1
 *
 * 2017年10月19日 下午3:06:09 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/vehiclePlan", description = "行车规则")
@RestController
@RequestMapping("/vehiclePlan/")
public class VehiclePlanController {
	
	@Autowired
	private VehiclePlanService vehiclePlanService;
	
	/**
	 * @param apikey --系统注册生成apiKey
	 * @param line_id --线路ID
	 * @param city_code --城市代码
	 * @param cur_date --日期
	 * @param reverse_type --0:单头调，1：双头调
	 * @return Map<String,Object> describe:查询分时准点率与滞站客流 2017年10月19日上午11:16:19 by
	 *         Chenq version 0.1
	 */
	@ApiOperation(value = "行车规则 code:002006001", produces = "application/json")
	@RequestMapping(value = "getBusTraffRule", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "apikey", dataType = "String", required = false, value = "系统注册生成apiKey"),
		@ApiImplicitParam(paramType = "query", name = "line_id", dataType = "int", required = false, value = "线路ID,必填"),
		@ApiImplicitParam(paramType = "query", name = "city_code", dataType = "String", required = false, value = "城市代码，必填"),
		@ApiImplicitParam(paramType = "query", name = "cur_date", dataType = "String", required = false, value = "日期，必填"),
		@ApiImplicitParam(paramType = "query", name = "reverse_type", dataType = "String", required = false, value = "0:单头调，1：双头调,必填"),})
	@ResponseBody
	public  Map<String,Object> getBusTraffRule(String apikey,String city_code,int line_id,String cur_date,int reverse_type)
	{
		    //1. 定义全局变量：返回结果
			Map<String,Object> doResult=new HashMap<String,Object>();
			doResult.put("result", 0);
			doResult.put("resultMsg ", "获取数据成功!");
			//2.处理参数异常情况
			if(StringUtils.isEmpty(apikey))
			{
				doResult.put("result", 1);
				doResult.put("resultMsg ", "参数异常:apikey为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(city_code))
			{
				doResult.put("result", 2);
				doResult.put("resultMsg ", "参数异常:city_code为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(line_id))
			{
				doResult.put("result", 3);
				doResult.put("resultMsg ", "参数异常:line_id为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(cur_date))
			{
				doResult.put("result", 4);
				doResult.put("resultMsg ", "参入参数异常:cur_date为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(reverse_type))
			{
				doResult.put("result", 5);
				doResult.put("resultMsg ", "参入参数异常:reverse_type为空!");
				doResult.put("response", "");
				return doResult;
			}
			
			Map<String, Object> param=new HashMap<String,Object>();
			param.put("cityCode", city_code);//设置城市编号
			param.put("lineId", line_id);//设置线路编号
			param.put("curDate", cur_date);//设置当前日期
			param.put("reverseType", reverse_type);//设置调车方式
			
			//3.获取数据
			//上行的配车方案 
			List<Map<String,Object>> planvehiclearrageup=new ArrayList<Map<String,Object>>();
			planvehiclearrageup.addAll(vehiclePlanService.getPlanvehiclearrageUpList(param));
			//下行的配车方案 
			List<Map<String,Object>> planvehiclearragedown=new ArrayList<Map<String,Object>>();
			planvehiclearragedown.addAll(vehiclePlanService.getPlanVehicleArrageDownList(param));
			// 上行行车规则
			List<Map<String,Object>> timearrageup=new ArrayList<Map<String,Object>>();
			timearrageup.addAll(vehiclePlanService.getTimeArrageUp(param));
			//下行行车规则
			List<Map<String,Object>> timearragedwon=new ArrayList<Map<String,Object>>();
			timearragedwon.addAll(vehiclePlanService.getTimeArrageDwon(param));
			//4. 组织返回数据格式，并且返回
		    Map<String, Object> response=new HashMap<String,Object>();
			response.put("line_id", line_id);
			response.put("date_type", " ");
			response.put("reverse_type", reverse_type);
			response.put("planvehiclearrageup", planvehiclearrageup);
			response.put("planvehiclearragedown", planvehiclearragedown);
			response.put("timearrageup", timearrageup);
			response.put("timearragedwon", timearragedwon);
			doResult.put("response", response);
			return doResult;
		}
	}
	

