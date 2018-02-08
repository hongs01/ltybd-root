package com.ltybd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ltybd.entity.LineEmployee;
import com.ltybd.service.LineEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * LineEmployeeController.java
 *
 * describe:
 * 
 * 2017年10月12日 下午4:24:54 created By Yancz version 0.1
 *
 * 2017年10月12日 下午4:24:54 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/lineEmployee", description = "线路员工对应信息")
@RestController
@RequestMapping("/lineEmployee/")
@Validated
public class LineEmployeeController {

	@Autowired
	private LineEmployeeService lineEmployeeService;

	/**
	 * @param lineEmployee
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询线路员工对应信息集合
	 * 2017年10月14日上午9:30:13 by Yancz version 0.1
	 */
	@ApiOperation(value = "查询线路员工对应信息集合 code:007001", produces = "application/json")
	@RequestMapping(value = "list", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(LineEmployee lineEmployee, Integer pageNum, Integer pageSize,
			Boolean isPage) {
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
			if(null == lineEmployee){
				lineEmployee = new LineEmployee();
			}
			if(null == lineEmployee.getStatus()){
				lineEmployee.setStatus(0);
			}
			List<LineEmployee> list = new ArrayList<LineEmployee>();
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
				Page<LineEmployee> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = lineEmployeeService.findListObj(lineEmployee);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = lineEmployeeService.findListObj(lineEmployee);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/***
	 * 
	 * @param line
	 * @return Map<String,Object>
	 * @describe:更新线路员工对应信息对象
	 * @2017年10月12日下午3:16:08 by Yancz version 0.1
	 */
	@ApiOperation(value = "更新线路员工对应信息对象 code:007002", produces = "application/json",notes = "参数line_id或Employee_id至少有一个为必填")
	@RequestMapping(value = "update", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateObj(@RequestBody @Validated LineEmployee lineEmployee,BindingResult error) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
        }
		if (null == lineEmployee||(null == lineEmployee.getEmployee_id() && null == lineEmployee.getLine_id())) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数line_id或Employee_id至少有一个为必填");
			map.put("resPonse", "更新失败");
			return map;
		} 
		int result = lineEmployeeService.updateObj(lineEmployee);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;

	}

	/**
	 * 
	 * @param line
	 * @return Map<String,Object>
	 * @describe:删除线路员工对应信息对象
	 * @2017年10月13日上午9:23:34 by Yancz version 0.1
	 */
	@ApiOperation(value = "删除线路员工对应信息对象 code:007003", produces = "application/json",notes = "参数line_id或Employee_id至少有一个为必填")
	@RequestMapping(value = "delete", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> deleteObj(@RequestBody @Validated LineEmployee lineEmployee,BindingResult error) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
        }
		if (null == lineEmployee||(null == lineEmployee.getEmployee_id() && null == lineEmployee.getLine_id())) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数line_id或Employee_id至少有一个为必填!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		int result = lineEmployeeService.delete(lineEmployee);
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
	 * @describe:批量更新线路员工对应信息对象 code:030007004
	 * @2017年10月16日上午11:00:24 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新线路员工对应信息对象 code:007004", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<LineEmployee> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "更新失败,请传参数");
			map.put("resPonse", "");
			return map;
		}
		int result = lineEmployeeService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;

	}

}
