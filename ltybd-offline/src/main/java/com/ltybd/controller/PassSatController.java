package com.ltybd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltybd.service.PassSatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * PassSatController.java
 *
 * describe:
 * 
 * 2017年10月16日 下午6:43:31 created By chenq version 0.1
 *
 * 2017年10月16日 下午6:43:31 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/passSat", description = "乘客满意度信息")
@RestController
@RequestMapping("/passSat/")
public class PassSatController {
	
	@Autowired
	private PassSatService passSatService;
	
	/**
	 * @param apikey --系统注册生成apiKey
	 * @param line_id --线路ID
	 * @param city_code --城市代码
	 * @param date_end --结束日期
	 * @param date_period --日期范围（30，60，90，365，all）
	 * @param step  --步长：1：小时，2：天，3：周，4：月
	 * @return Map<String,Object> describe:查询分时准点率与滞站客流 2017年10月16日上午12:16:19 by
	 *         Chenq version 0.1
	 * 
	 */
	@ApiOperation(value = "乘客满意度查询接口code:002002001", produces = "application/json")
	@RequestMapping(value = "getCustomerDegree", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "apikey", dataType = "String", required = false, value = "系统注册生成apiKey"),
		@ApiImplicitParam(paramType = "query", name = "line_id", dataType = "int", required = false, value = "线路ID,必填"),
		@ApiImplicitParam(paramType = "query", name = "city_code", dataType = "String", required = false, value = "城市代码，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_end", dataType = "String", required = false, value = "结束日期，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_period", dataType = "int", required = false, value = "日期范围（30，60，90，365，all)，必填"),
		@ApiImplicitParam(paramType = "query", name = "step", dataType = "int", required = false, value = "步长：1：小时，2：天，3：周，4：月,必填"),})
	@ResponseBody
	public  Map<String,Object> getCustomerDegree(String apikey,Integer line_id,String city_code,String date_end,Integer date_period,Integer step){
		    //1. 定义全局变量：返回结果
			Map<String,Object> doResult=new HashMap<String,Object>();
			doResult.put("result", 0);
			doResult.put("resultMsg", "获取数据成功!");
			if(StringUtils.isEmpty(apikey)){
				doResult.put("result", 1);
				doResult.put("resultMsg", "参数异常:apikey为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(line_id)){
				doResult.put("result", 2);
				doResult.put("resultMsg", "参数异常:line_id为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(city_code)){
				doResult.put("result", 3);
				doResult.put("resultMsg", "参数异常:city_code为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(date_end)){
				doResult.put("result", 4);
				doResult.put("resultMsg", "参数异常:date_end为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(date_period)){
				doResult.put("result", 5);
				doResult.put("resultMsg", "参数异常:date_period为空!");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(step)){
				doResult.put("result", 6);
				doResult.put("resultMsg", "参数异常:step为空!");
				doResult.put("response", "");
				return doResult;
			}
			Map<String, Object> param=new HashMap<String,Object>();
			param.put("city_code", city_code);//设置城市编号
			param.put("line_id", line_id);//设置线路编号
			param.put("date_end", date_end);//结束日期
			param.put("date_period", date_period);//日期范围
			param.put("step", step);//步长
			//乘客满意度信息
			List<Map<String,Object>> degreeData=new ArrayList<Map<String,Object>>();
			degreeData.addAll(passSatService.getCustomerDegree(param));
					
			Map<String, Object> response=new HashMap<String,Object>();
			response.put("line_id", line_id);
			response.put("line_name", "");
			response.put("city_code", city_code);
			response.put("date", date_end);
			response.put("date_period", date_period);
			response.put("satisfaction_details", degreeData);
			doResult.put("response", response);
			return doResult;
		}
	
	
	/**
	 * @param apikey --系统注册生成apiKey
	 * @param line_id --线路ID
	 * @param city_code --城市代码
	 * @param date_end --结束日期
	 * @param date_period --日期范围（30，60，90，365，all）
	 * @param direction  --方向
	 * @return Map<String,Object> describe:查询分时准点率与滞站客流 2017年10月16日上午15:16:19 by
	 *         Chenq version 0.1
	 * 
	 */
	@ApiOperation(value = "乘车满意度查询接口code:002002002", produces = "application/json")
	@RequestMapping(value = "getSatisfactionDegree", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "apikey", dataType = "String", required = false, value = "系统注册生成apiKey"),
		@ApiImplicitParam(paramType = "query", name = "line_id", dataType = "int", required = false, value = "线路ID,必填"),
		@ApiImplicitParam(paramType = "query", name = "city_code", dataType = "String", required = false, value = "城市代码，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_end", dataType = "String", required = false, value = "结束日期，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_period", dataType = "int", required = false, value = "日期范围（30，60，90，365，all)，必填"),
		@ApiImplicitParam(paramType = "query", name = "direction", dataType = "String", required = false, value = "方向,必填"),})
	@ResponseBody
	public  Map<String,Object> getSatisfactionDegree(String apikey,Integer line_id,String city_code,String date_end,Integer date_period,String direction){
	    //1. 定义全局变量：返回结果
		Map<String,Object> doResult=new HashMap<String,Object>();
		doResult.put("result", 0);
		doResult.put("resultMsg", "获取数据成功!");
		if(StringUtils.isEmpty(apikey)){
			doResult.put("result", 1);
			doResult.put("resultMsg", "参数异常:apikey为空!");
			doResult.put("response", "");
			return doResult;
		}
		if(StringUtils.isEmpty(line_id)){
			doResult.put("result", 2);
			doResult.put("resultMsg", "参数异常:line_id为空!");
			doResult.put("response", "");
			return doResult;
		}
		if(StringUtils.isEmpty(city_code)){
			doResult.put("result", 3);
			doResult.put("resultMsg", "参数异常:city_code为空!");
			doResult.put("response", "");
			return doResult;
		}
		if(StringUtils.isEmpty(date_end)){
			doResult.put("result", 4);
			doResult.put("resultMsg", "参数异常:date_end为空!");
			doResult.put("response", "");
			return doResult;
		}
		if(StringUtils.isEmpty(date_period)){
			doResult.put("result", 5);
			doResult.put("resultMsg", "参数异常:date_period为空!");
			doResult.put("response", "");
			return doResult;
		}
		if(StringUtils.isEmpty(direction)){
			doResult.put("result", 6);
			doResult.put("resultMsg", "参数异常:direction为空!");
			doResult.put("response", "");
			return doResult;
		}
		Map<String, Object> param=new HashMap<String,Object>();
		param.put("city_code", city_code);//设置城市编号
		param.put("line_id", line_id);//设置线路编号
		param.put("date_end", date_end);//结束日期
		param.put("date_period", date_period);//日期范围
		param.put("direction", direction);//方向
		//侯车满意度
		List<Map<String,Object>> waitingSatisfaction=new ArrayList<Map<String,Object>>();
		waitingSatisfaction.addAll(passSatService.getWaitingSatisfaction(param));
		//舒适满意度
		List<Map<String,Object>> comfortSatisfaction=new ArrayList<Map<String,Object>>();
		comfortSatisfaction.addAll(passSatService.getComfortSatisfaction(param));
		//企业满意度
		List<Map<String,Object>> enterprisePleased=new ArrayList<Map<String,Object>>();
		//乘客满意度
		List<Map<String,Object>> passengerPleased=new ArrayList<Map<String,Object>>();
		Map<String, Object> response=new HashMap<String,Object>();
		response.put("line_id", line_id);
		response.put("line_name", "");
		response.put("city_code", city_code);
		response.put("date", date_end);
		response.put("date_period", date_period);
		response.put("wait_pleased", waitingSatisfaction);
		response.put("comfortable_pleased", comfortSatisfaction);
		response.put("enterprise_pleased", enterprisePleased);
		response.put("passenger_pleased", passengerPleased);
		doResult.put("response", response);
		return doResult;
	}
	
}
