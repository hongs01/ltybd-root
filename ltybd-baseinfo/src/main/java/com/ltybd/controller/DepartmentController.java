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
import com.ltybd.entity.Department;
import com.ltybd.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * DepartmentController.java
 *
 * describe:部门信息
 * 
 * 2017年10月17日 下午7:26:25 created By Yancz version 0.1
 *
 * 2017年10月17日 下午7:26:25 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/department", description = "部门信息")
@RestController
@RequestMapping("/department/")
@Validated
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	/**
	 * 
	 * @param department
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object>
	 * @describe:查询部门信息集合 code:030013001
	 * @2017年10月17日下午7:27:06 by Yancz version 0.1
	 */
	@ApiOperation(value = "查询部门信息集合 code:013001", produces = "application/json")
	@RequestMapping(value = "list", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "department_name", dataType = "String", required = false, value = "部门名称,模糊查询"),
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(Department department, Integer pageNum, Integer pageSize, Boolean isPage) {
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
			if (null == department) {
				department = new Department();
			}
			if (null == department.getStatus()) {
				department.setStatus(0);
			}
			List<Department> list = new ArrayList<Department>();
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
				Page<Department> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = departmentService.findListObj(department);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = departmentService.findListObj(department);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * 
	 * @param department_id
	 * @return Map<String,Object>
	 * @describe:查询部门信息 code:030013002
	 * @2017年10月17日下午7:27:23 by Yancz version 0.1
	 */
	@ApiOperation(value = "查询部门信息 code:013002", produces = "application/json")
	@RequestMapping(value = "entity", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "department_id", dataType = "int", required = false, value = "部门ID,必填"), })
	@ResponseBody
	public Map<String, Object> findByDepartmentId(
			@NotNull(message="department_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer department_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Department department = new Department();
		department.setDepartment_id(department_id);
		map.put("resPonse",departmentService.findByDepartmentId(department)!=null?departmentService.findByDepartmentId(department):"没有查找到对应数据!" );
		return map;
	}

	/***
	 * 
	 * @param department
	 * @return Map<String,Object>
	 * @describe:更新部门信息对象 code:030013003
	 * @2017年10月17日下午7:27:37 by Yancz version 0.1
	 */
	@ApiOperation(value = "更新部门信息对象 code:013003", produces = "application/json")
	@RequestMapping(value = "update", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateDepartmentObj(@RequestBody @Validated Department department,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
		}
		if (null != department && null != department.getDepartment_id()) {
			if(departmentService.findByDepartmentId(department)!=null){
				departmentService.update(department);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此部门对象无法更新");
				return map;
			}
		} else {
			if(StringUtils.isEmpty(department.getDepartment_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "参数异常，Department_name不能为空!");
				return map;
			}else{
				department.setDepartment_id(departmentService.getSequence("department_code"));
				departmentService.insert(department);
			}
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", department.getDepartment_id());
		return map;
	}

	/***
	 * 
	 * @param department_id
	 * @return Map<String,Object>
	 * @describe:删除部门信息对象 code:030013004
	 * @2017年10月17日下午7:27:50 by Yancz version 0.1
	 */
	@ApiOperation(value = "删除部门信息对象 code:013004", produces = "application/json")
	@RequestMapping(value = "delete", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "department_id", dataType = "int", required = false, value = "部门ID,必填"), })
	@ResponseBody
	public Map<String, Object> deleteDepartmentObj(
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer department_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == department_id) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数group_id为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		Department department = new Department();
		department.setDepartment_id(department_id);
		if(departmentService.findByDepartmentId(department)==null){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数"+department_id+"对应的对象不存在");
			map.put("resPonse", "删除失败!");
		}else{
			int result = departmentService.delete(department);
			if (result > 0) {
				map.put("resPonse", "删除成功!");
			} else {
				map.put("resPonse", "删除失败!");
			}
		}
		return map;
	}

	/**
	 * 
	 * @param ids
	 * @return Map<String,Object>
	 * @describe:批量删除部门信息对象 code:030013005
	 * @2017年10月17日下午7:28:07 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量删除部门信息对象 code:013005", produces = "application/json")
	@RequestMapping(value = "deleteItems", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "集团ID集合,必填,以(,)隔开,例:1,2,3"), })
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
		 String[]idss=ids.split(",");
		 int count=0;
		 String str="";
		 for (int i = 0; i < idss.length; i++) {
			 Department department = new Department();
			 department.setDepartment_id(Integer.parseInt(idss[i]));
			 if(departmentService.findByDepartmentId(department)==null){
					count++;
					str+=","+department.getDepartment_id();
				}
			 }
		 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的是"+str);
		int result = departmentService.deleteItems(ids);
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
	 * @describe:批量更新部门信息对象 code:030013006
	 * @2017年10月17日下午7:28:24 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新部门信息对象 code:013006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Department> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,请传参数");
			map.put("resPonse", "更新失败");
			return map;
		}
		int result = departmentService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;

	}

}
