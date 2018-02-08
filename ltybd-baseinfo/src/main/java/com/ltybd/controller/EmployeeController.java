package com.ltybd.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Employee;
import com.ltybd.entity.EmployeeBean;
import com.ltybd.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * EmployeeController.java
 *
 * describe:员工信息
 * 
 * 2017年10月12日 下午2:47:05 created By Chenjw version 0.1
 *
 * 2017年10月12日 下午2:47:05 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/employee", description = "员工信息")
@RestController
@RequestMapping("/employee/")
@Validated
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	/**
	 * @param employee
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> 
	 * describe:查询员工信息集合 
	 * 2017年10月12日上午10:10:19 by Chejw version 0.1
	 */
	@ApiOperation(value = "查询员工信息集合 code:002001", produces = "application/json")
	@RequestMapping(value = "list", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "employee_id", dataType = "int", required = false, value = "员工id,模糊查询"),
			@ApiImplicitParam(paramType = "query", name = "employee_name", dataType = "String", required = false, value = "员工姓名,模糊查询"),
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(Employee employee, Integer pageNum, Integer pageSize, Boolean isPage) {
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
			if (StringUtils.isEmpty(employee.getStatus()))
				employee.setStatus(0);
			List<EmployeeBean> list = new ArrayList<EmployeeBean>();
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
				if (pageNum == 0)
					pageNum = 1;// 初始页码
				if (pageSize == 0)
					pageSize = 15;// 初始每页条数
				Page<Employee> page = PageHelper.startPage(pageNum, pageSize);// 分页
				list = employeeService.findListObj(employee);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = employeeService.findListObj(employee);
			}
			mapData.put("employeeList", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * 
	 * @param employee_id
	 * @return Map<String, Object> 
	 * describe:查询员工信息对象 
	 * 2017年10月12日上午10:10:29 by Chejw version 0.1
	 * @throws Exception
	 */
	@ApiOperation(value = "查询员工信息对象 code:002002", produces = "application/json")
	@RequestMapping(value = "entity", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "employee_id", dataType = "int", required = false, value = "员工id,必填"), })
	@ResponseBody
	public Map<String, Object> findByDicId(
			@NotNull(message="employee_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer employee_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (StringUtils.isEmpty(employee_id)) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数dict_id为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		Employee employee = new Employee();
		employee.setEmployee_id(employee_id);
		map.put("resPonse",employeeService.findByEmployeeId(employee)!=null?employeeService.findByEmployeeId(employee):"没有查找到对应数据!" );
		return map;
	}

	/**
	 * @param employee
	 * @return Map<String, Object> 
	 * describe:更新员工信息对象 
	 * 2017年10月12日上午10:10:29 by Chenjw version 0.1
	 */
	@ApiOperation(value = "更新员工信息对象 code:002003", produces = "application/json")
	@RequestMapping(value = "update", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateObj(@RequestBody @Validated Employee employee,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
		}
		if (StringUtils.isEmpty(employee.getEmployee_id())) {
			if(StringUtils.isEmpty(employee.getEmployee_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "员工名称Employee_name不能为空!");
				return map;
			}else{
				employee.setEmployee_id(employeeService.getSequence("employee_code"));
				employee.setStatus(0);
				employee.setCreate_time(new Date());
				employee.setLast_modified_time(new Date());
				employeeService.insert(employee);
			}
			
		} else {
			if(employeeService.findByEmployeeId(employee)!=null){
			employee.setLast_modified_time(new Date());
			employeeService.update(employee);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此员工对象无法更新");
				return map;
			}
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",employeeService.getSequence("employee_code"));
		return map;
	}

	/**
	 * @param employee_id
	 * @return Map<String, Object> 
	 * describe:删除员工信息对象 
	 * 2017年10月12日上午10:10:32 by Chejw version 0.1
	 */
	@ApiOperation(value = "删除员工信息对象 code:002004", produces = "application/json")
	@RequestMapping(value = "delete", method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "employee_id", dataType = "int", required = false, value = "员工id,必填"), })
	@ResponseBody
	public Map<String, Object> deleteDicObj(
			@NotNull(message="employee_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer employee_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (StringUtils.isEmpty(employee_id)) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数employee_id为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		Employee employee = new Employee();
		employee.setEmployee_id(employee_id);
		int result = employeeService.delete(employee);
		if (result > 0) {
			map.put("resPonse", "删除成功!");
		} else {
			map.put("resPonse", "删除失败!");
		}
		return map;
	}

	/**
	 * @param ids
	 * Map<String, Object> 
	 * describe:批量删除员工信息对象 
	 * 2017年10月12日上午10:10:35 by Chejw version 0.1
	 */
	@ApiOperation(value = "批量删除员工信息对象 code:002005", produces = "application/json")
	@RequestMapping(value = "deleteItems", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "员工id集合,必填,以(,)隔开,例:1,2,3"), })
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
			 Employee employee=new Employee();
			 employee.setEmployee_id(Integer.parseInt(idss[i]));
			 if(employeeService.findByEmployeeId(employee)==null){
					count++;
					str+=","+employee.getEmployee_id();
				}
			 }
		 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的是"+str);
		int result = employeeService.deleteItems(ids);
		if (result > 0) {
			map.put("resPonse", "删除成功!");
		} else {
			map.put("resPonse", "删除失败!");
		}
		return map;
	}
	
	/**
	 * @param employees
	 * @return Map<String, Object> 
	 * describe:批量更新员工对象 
	 * 2017年10月12日上午10:10:29 by Chenjw version 0.1
	 */
	@ApiOperation(value = "批量更新员工对象 code:002006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateObjList(@RequestBody List<Employee> employees) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == employees || employees.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "更新失败,请传参数");
			map.put("resPonse", "");
			return map;
		}
		int result = employeeService.updateObjList(employees);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;
	}

}
