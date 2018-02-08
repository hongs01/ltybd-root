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
import com.ltybd.entity.Group;
import com.ltybd.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * GroupController.java
 *
 * describe:集团信息
 * 
 * 2017年10月17日 下午2:59:57 created By Yancz version 0.1
 *
 * 2017年10月17日 下午2:59:57 modifyed By Yancz version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/group", description = "集团信息")
@RestController
@RequestMapping("/group/")
@Validated
public class GroupController {

	@Autowired
	private GroupService groupService;

	/***
	 * 
	 * @param group
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object>
	 * @describe:
	 * @2017年10月17日下午3:45:34 by Yancz version 0.1
	 */
	@ApiOperation(value = "查询集团信息集合 code:011001", produces = "application/json")
	@RequestMapping(value = "list", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "group_name", dataType = "String", required = false, value = "集团名称,模糊查询"),
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(Group group, Integer pageNum, Integer pageSize, Boolean isPage) {
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
			if (null == group) {
				group = new Group();
			}
			if (null == group.getStatus()) {
				group.setStatus(0);
			}
			List<Group> list = new ArrayList<Group>();
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
				Page<Group> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = groupService.findListObj(group);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = groupService.findListObj(group);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/***
	 * 
	 * @param group_id
	 * @return Map<String,Object>
	 * @describe:查询集团信息对象
	 * @2017年10月17日下午3:45:12 by Yancz version 0.1
	 */
	@ApiOperation(value = "查询集团信息对象 code:011002", produces = "application/json")
	@RequestMapping(value = "entity", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "group_id", dataType = "int", required = false, value = "集团ID,必填"), })
	@ResponseBody
	public Map<String, Object> findByGroupId(
			@NotNull(message="group_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer group_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == group_id) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数group_id为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		Group group = new Group();
		group.setGroup_id(group_id);
		map.put("resPonse",groupService.findByGroupId(group)!=null?groupService.findByGroupId(group):"没有查找到对应数据!" );
		return map;
	}

	/***
	 * 
	 * @param group
	 * @return Map<String,Object>
	 * @describe:更新集团信息对象
	 * @2017年10月17日下午3:46:37 by Yancz version 0.1
	 */
	@ApiOperation(value = "更新集团信息对象 code:011003", produces = "application/json")
	@RequestMapping(value = "update", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateGroupObj(@RequestBody @Validated Group group,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
		}
		if (null != group && null != group.getGroup_id()) {
			if(groupService.findByGroupId(group)!=null){
				groupService.update(group);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此集团对象无法更新");
				return map;
			}
			
		} else {
			if(StringUtils.isEmpty(group.getGroup_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "集团名称Group_name不能为空!");
				return map;
			}else{
				group.setGroup_id(groupService.getSequence("group_code"));
				groupService.insert(group);
			}
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", group.getGroup_id());
		return map;
	}

	/***
	 * 
	 * @param group_id
	 * @return Map<String,Object>
	 * @describe:删除集团信息对象
	 * @2017年10月17日下午3:47:23 by Yancz version 0.1
	 */
	@ApiOperation(value = "删除集团信息对象 code:011004", produces = "application/json")
	@RequestMapping(value = "delete", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "group_id", dataType = "int", required = false, value = "集团ID,必填"), })
	@ResponseBody
	public Map<String, Object> deleteGroupObj(
			@NotNull(message="group_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer group_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == group_id) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数group_id为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		Group group = new Group();
		group.setGroup_id(group_id);
		int result = groupService.delete(group);
		if (result > 0) {
			map.put("resPonse", "删除成功!");
		} else {
			map.put("resPonse", "删除失败!");
		}
		return map;
	}

	/***
	 * 
	 * @param ids
	 * @return Map<String,Object>
	 * @describe:批量删除集团信息对象
	 * @2017年10月17日下午3:48:11 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量删除集团信息对象 code:011005", produces = "application/json")
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
			 Group group=new Group();
			 group.setGroup_id(Integer.parseInt(idss[i]));
			 if(groupService.findByGroupId(group)==null){
					count++;
					str+=","+group.getGroup_id();
				}
			 }
		 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的是"+str);
		int result = groupService.deleteItems(ids);
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
	 * @describe:批量更新集团信息对象
	 * @2017年10月17日下午3:48:41 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新集团信息对象 code:011006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Group> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "更新失败,请传参数");
			map.put("resPonse", "");
			return map;
		}
		int result = groupService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;

	}

}
