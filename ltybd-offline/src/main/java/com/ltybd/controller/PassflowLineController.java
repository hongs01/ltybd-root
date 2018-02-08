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
import org.springframework.util.StringUtils;

import com.ltybd.service.PassflowLineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * PassflowLineController.java
 *
 * describe:
 * 
 * 2017年10月16日 下午8:12:07 created By chenq version 0.1
 *
 * 2017年10月16日 下午8:12:07 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/passflowLine", description = "线路分时客流查询")
@RestController
@RequestMapping("/passflowLine/")
public class PassflowLineController {
	
	@Autowired
	private PassflowLineService passflowLineService;
	
	/**
	 * @param apikey --系统注册生成apiKey
	 * @param line_id --线路ID
	 * @param city_code --城市代码
	 * @param date_end --结束日期
	 * @param date_period --日期范围（30，60，90，365，all）
	 * @param direction --方向
	 * @param step  --步长：1：小时，2：天，3：周，4：月
	 * @return Map<String,Object> describe:查询分时准点率与滞站客流 2017年10月16日上午10:16:19 by
	 *         Chenq version 0.1
	 * 
	 */
	
	@ApiOperation(value = "线路分时客流查询 code:002003001", produces = "application/json")
	@RequestMapping(value = "getPassengerFlow", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "apikey", dataType = "String", required = false, value = "系统注册生成apiKey"),
		@ApiImplicitParam(paramType = "query", name = "line_id", dataType = "int", required = false, value = "线路ID,必填"),
		@ApiImplicitParam(paramType = "query", name = "city_code", dataType = "String", required = false, value = "城市代码，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_end", dataType = "String", required = false, value = "结束日期，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_period", dataType = "int", required = false, value = "日期范围（30，60，90，365，all)，必填"),
		@ApiImplicitParam(paramType = "query", name = "direction", dataType = "String", required = false, value = "方向，必填"),
		@ApiImplicitParam(paramType = "query", name = "step", dataType = "int", required = false, value = "步长：1：小时，2：天，3：周，4：月,必填"),})
	@ResponseBody
	public  Map<String,Object> getPassengerFlow(String apikey,Integer line_id,String city_code,String date_end,String direction,Integer date_period,Integer step){
	        
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
			if(StringUtils.isEmpty(step)){
				doResult.put("result", 7);
				doResult.put("resultMsg", "参数异常:step为空!");
				doResult.put("response", "");
				return doResult;
			}
			Map<String, Object> param=new HashMap<String,Object>();
			param.put("city_code", city_code);//设置城市编号
			param.put("line_id", line_id);//设置线路编号
			param.put("date_end", date_end);//结束日期
			param.put("date_period", date_period);//日期范围
			param.put("direction", direction);//行车方向
			param.put("step", step);//步长
			
			//预测客流
			List<Map<String,Object>> prePfData=new ArrayList<Map<String,Object>>();
			prePfData.addAll(passflowLineService.getPrePassengerFlow(param));
			//历史客流
			List<Map<String,Object>> htPfData=new ArrayList<Map<String,Object>>();
			htPfData.addAll(passflowLineService.getHistoryPassengerFlow(param));
			//历史运力
			List<Map<String,Object>> htCpData=new ArrayList<Map<String,Object>>();
			//运力
			List<Map<String,Object>> suggusetCpData=new ArrayList<Map<String,Object>>();

			Map<String, Object> response=new HashMap<String,Object>();
			response.put("line_id", line_id);
			response.put("city_code", city_code);
			response.put("date", date_end);
			response.put("date_period", date_period);
			response.put("direction", direction);
			response.put("pre_passenger_flow", prePfData);
			response.put("history_passenger_flow", htPfData);
			response.put("history_capacity", htCpData);
			response.put("sugguset_capacity", suggusetCpData);
			doResult.put("response", response);
			return doResult;
		}
	
	

	/**
	 * @param apikey --系统注册生成apiKey
	 * @param company_id --公司ID
	 * @param city_code --城市代码
	 * @param date_end --结束日期
	 * @param date_period --日期范围（30，60，90，365，all）
	 * @param step  --步长：1：小时，2：天，3：周，4：月
	 * @return Map<String,Object> describe:查询分时准点率与滞站客流 2017年10月16日上午15:16:19 by
	 *         Chenq version 0.1
	 * 
	 */
	
	@ApiOperation(value = "各公司分时客流与构成查询 code:002003002", produces = "application/json")
	@RequestMapping(value = "companyPassengerFlow", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "apikey", dataType = "String", required = false, value = "系统注册生成apiKey"),
		@ApiImplicitParam(paramType = "query", name = "company_id", dataType = "int", required = false, value = "公司ID,必填"),
		@ApiImplicitParam(paramType = "query", name = "city_code", dataType = "String", required = false, value = "城市代码，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_end", dataType = "String", required = false, value = "结束日期，必填"),
		@ApiImplicitParam(paramType = "query", name = "date_period", dataType = "int", required = false, value = "日期范围（30，60，90，365，all)，必填"),
		@ApiImplicitParam(paramType = "query", name = "step", dataType = "int", required = false, value = "步长：1：小时，2：天，3：周，4：月,必填"),})
	@ResponseBody
	public  Map<String,Object> companyPassengerFlow(String apikey,Integer company_id,String city_code,String date_end,Integer date_period,Integer step){
        
		Map<String,Object> doResult =new HashMap<String,Object>();
		doResult.put("result", 0);
		doResult.put("resultMsg", "获取数据成功!");
		if (StringUtils.isEmpty(apikey)){
			doResult.put("result", 1);
			doResult.put("resultMsg", "参数异常:apikey为空!");
			doResult.put("response", "");			
		}
		if (StringUtils.isEmpty(company_id)){
			doResult.put("result", 2);
			doResult.put("resultMsg", "参数异常:company_id为空!");
			doResult.put("response", "");			
		}
		if (StringUtils.isEmpty(city_code)){
			doResult.put("result", 3);
			doResult.put("resultMsg", "参数异常:city_code为空!");
			doResult.put("response", "");			
		}
		if (StringUtils.isEmpty(date_end)){
			doResult.put("result", 4);
			doResult.put("resultMsg", "参数异常:date_end为空!");
			doResult.put("response", "");			
		}
		if (StringUtils.isEmpty(date_period)){
			doResult.put("result", 5);
			doResult.put("resultMsg", "参数异常:date_period为空!");
			doResult.put("response", "");			
		}
		if (StringUtils.isEmpty(step)){
			doResult.put("result", 6);
			doResult.put("resultMsg", "参数异常:step为空!");
			doResult.put("response", "");			
		}
		Map<String,Object> param =new HashMap<String,Object>();
		param.put("company_id", company_id);//公司ID
		param.put("city_code", city_code);//设置城市编号
		param.put("date_end", date_end);//结束日期
		param.put("date_period", date_period);//日期范围
		param.put("step", step);//步长
		List<Map<String,Object>> companylineData =null;
		List<Map<String,Object>> companypieData =null;
		List<Map<String,Object>> linePassengerData =null;
		List<Map<String,Object>> piePassengerData =null;
		Map<String, Object> response=new HashMap<String,Object>();
		if (company_id ==-1){
			param.put("company_id", "");
			//公司客流折线图
			companylineData =new ArrayList<Map<String,Object>>();
			companylineData.addAll(passflowLineService.companyPassengerFlowLineChart(param));
			
			//对公司客流返回数据进行封装
			Map<Integer,List<Map<String,Object>>> returnlinemap=new HashMap<Integer,List<Map<String,Object>>>();
			Map<Integer,List<String>>  tmpMap=new HashMap<Integer, List<String>>();
			for(int i=0;i<companylineData.size();i++){
				if(tmpMap.get((Integer)companylineData.get(i).get("son_name"))==null){
					List<String> tmpList=new ArrayList<String>();
					tmpList.add(companylineData.get(i).get("x_data").toString()+"@"+companylineData.get(i).get("y_data_passenger_flow"));
					tmpMap.put((Integer)companylineData.get(i).get("son_name"), tmpList);
				}else{
					List<String> tmpList=tmpMap.get((Integer)companylineData.get(i).get("son_name"));
					tmpList.add(companylineData.get(i).get("x_data").toString()+"@"+companylineData.get(i).get("y_data_passenger_flow"));
					tmpMap.put((Integer)companylineData.get(i).get("son_name"), tmpList);
				}
			}
		
			for(Integer n:tmpMap.keySet()){
				List<String> list=tmpMap.get(n);
				List<Map<String,Object>> listm=new ArrayList<Map<String,Object>>();
			           for(int i=0;i<list.size();i++){
			        	   Map<String,Object> mp=new HashMap<String, Object>();
			        	   mp.put("x_data", list.get(i).split("@")[0]);
			        	   mp.put("y_data_passenger_flow", list.get(i).split("@")[1]);
			        	   listm.add(mp);
			           }
			           returnlinemap.put(n, listm);
			}
			
			//公司客流饼状图
			companypieData =new ArrayList<Map<String,Object>>();
			companypieData.addAll(passflowLineService.companyPassengerFlowPieChart(param));
			response.put("companylineData", returnlinemap);
			response.put("companypieData", companypieData);
			response.put("linePassengerData", "");
			response.put("piePassengerData", "");
		}
		else{
			//线路客流折线图
			 linePassengerData=new ArrayList<Map<String,Object>>();
			 linePassengerData.addAll(passflowLineService.PassengerFlowlineChart(param));
			 
			//对线路客流数据进行格式封装
			Map<String,List<Map<String,Object>>> returnlinemap=new HashMap<String,List<Map<String,Object>>>();
			Map<String,List<String>>  tmpMap=new HashMap<String, List<String>>();
			for(int i=0;i<linePassengerData.size();i++){
				if(tmpMap.get((String)linePassengerData.get(i).get("son_name"))==null){
					List<String> tmpList=new ArrayList<String>();
					tmpList.add(linePassengerData.get(i).get("x_data").toString()+"@"+linePassengerData.get(i).get("y_data_passenger_flow"));
					tmpMap.put((String)linePassengerData.get(i).get("son_name"), tmpList);
				}else{
					List<String> tmpList=tmpMap.get((String)linePassengerData.get(i).get("son_name"));
					tmpList.add(linePassengerData.get(i).get("x_data").toString()+"@"+linePassengerData.get(i).get("y_data_passenger_flow"));
					tmpMap.put((String)linePassengerData.get(i).get("son_name"), tmpList);
				}
			}
			
			for(String n:tmpMap.keySet()){
				List<String> list=tmpMap.get(n);
				List<Map<String,Object>> listm=new ArrayList<Map<String,Object>>();
			           for(int i=0;i<list.size();i++){
			        	   Map<String,Object> mp=new HashMap<String, Object>();
			        	   mp.put("x_data", list.get(i).split("@")[0]);
			        	   mp.put("y_data_passenger_flow", list.get(i).split("@")[1]);
			        	   listm.add(mp);
			           }
			           returnlinemap.put(n, listm);
			}
			//线路客流饼状图
			 piePassengerData=new ArrayList<Map<String,Object>>();
			 piePassengerData.addAll(passflowLineService.PassengerFlowPieChart(param));
			 response.put("linePassengerData", returnlinemap);
			 response.put("piePassengerData", piePassengerData);
			 response.put("companylineData", "");
			 response.put("companypieData", "");
		}
		response.put("company_id", company_id);
		response.put("city_code", city_code);
		response.put("date_end", date_end);
		response.put("date_period", date_period);
		doResult.put("response", response);
		return doResult;
	}
	
}
