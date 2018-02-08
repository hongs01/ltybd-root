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
import com.ltybd.entity.Dictionary;
import com.ltybd.service.DictionaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * DictionaryController.java
 *
 * describe:数据字典
 * 
 * 2017年10月12日 上午10:03:06 created By Chejw version 0.1
 *
 * 2017年10月12日 上午10:03:06 modifyed By Chejw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/dictionary", description = "数据字典")
@RestController
@RequestMapping("/dictionary/")
@Validated
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * @param dic
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> 
	 * describe:查询字典集合 
	 * 2017年10月12日上午10:10:19 by Chejw version 0.1
	 */
	@ApiOperation(value = "查询字典集合 code:030001001", produces = "application/json")
	@RequestMapping(value = "list", method = { RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "dict_name", dataType = "String", required = false, value = "字典名称,模糊查询"),
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(Dictionary dic, Integer pageNum,Integer pageSize, Boolean isPage) {
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
			List<Dictionary> list = new ArrayList<Dictionary>();
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
				Page<Dictionary> page = PageHelper.startPage(pageNum, pageSize);// 分页
				list = dictionaryService.findListObj(dic);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = dictionaryService.findListObj(dic);
			}
			mapData.put("dictionaryList", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * 
	 * @param dic
	 * @return Map<String, Object> 
	 * describe:查询字典对象 
	 * 2017年10月12日上午10:10:29 by Chejw version 0.1
	 */
	@ApiOperation(value = "查询字典对象 code:001002", produces = "application/json")
	@RequestMapping(value = "entity", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "dict_id", dataType = "String", required = false, value = "字典id,必填"), })
	@ResponseBody
	public Map<String, Object> findByDicId(@RequestBody @Validated Dictionary dic,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
		}
		if (StringUtils.isEmpty(dic.getDict_id())) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数dict_id为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		map.put("resPonse",dictionaryService.findByDicId(dic)!=null?dictionaryService.findByDicId(dic):"没有查找到对应数据!" );
		return map;
	}

	/**
	 * @param dic
	 * @return Map<String, Object> 
	 * describe:更新字典对象 
	 * 2017年10月12日上午10:10:29 by Chenjw version 0.1
	 */
	@ApiOperation(value = "更新字典对象 code:001003", produces = "application/json")
	@RequestMapping(value = "update", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateDicObj(@RequestBody @Validated Dictionary dic,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()){
			List<ObjectError> errorList = result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse", errorList);
			return map;
		}
		if (StringUtils.isEmpty(dic.getDict_id())) {
			if(StringUtils.isEmpty(dic.getGroup_id()) || StringUtils.isEmpty(dic.getDict_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "参数异常，Group_id和Dict_name为必填项!");
				return map;
			}else{
				dic.setDict_id(dictionaryService.getSequence("dictionary_code"));
				dictionaryService.insert(dic);
			}
		} else {
			if(dictionaryService.findByDicId(dic)!=null){
			dictionaryService.update(dic);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此字典对象无法更新");
				return map;
			}
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",dictionaryService.getSequence("dictionary_code"));
		return map;
	}

	/**
	 * @param dic
	 * @return Map<String, Object> 
	 * describe:删除字典对象 
	 * 2017年10月12日上午10:10:32 by Chejw version 0.1
	 */
	@ApiOperation(value = "删除字典对象 code:001004", produces = "application/json")
	@RequestMapping(value = "delete", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "dict_id", dataType = "String", required = false, value = "字典id,必填"), })
	@ResponseBody
	public Map<String, Object> deleteDicObj(Dictionary dic) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (StringUtils.isEmpty(dic.getDict_id())) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数dict_id为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}

		int result = dictionaryService.delete(dic);
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
	 * describe:批量删除字典对象
	 * 2017年10月12日上午10:10:35 by Chejw version 0.1
	 */
	@ApiOperation(value = "批量删除字典对象 code:001005", produces = "application/json")
	@RequestMapping(value = "deleteItems", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "字典id集合,必填,以(,)隔开,例:1,2,3"), })
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
			 Dictionary  dictionary =new Dictionary ();
			 dictionary.setDict_id(Integer.parseInt(idss[i]));
			 if(dictionaryService.findByDicId(dictionary)==null){
					count++;
					str+=","+dictionary.getDict_id();
				}
			 }
		 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的是"+str);
		int result = dictionaryService.deleteItems(ids);
		if (result > 0) {
			map.put("resPonse", "删除成功!");
		} else {
			map.put("resPonse", "删除失败!");
		}
		return map;
	}
	
	/**
	 * @param dics
	 * @return Map<String, Object> 
	 * describe:批量更新字典对象 
	 * 2017年10月12日上午10:10:29 by Chenjw version 0.1
	 */
	@ApiOperation(value = "批量更新字典对象 code:001006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateDicObjList(@RequestBody List<Dictionary> dics) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == dics || dics.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "更新失败,请传参数");
			map.put("resPonse", "");
			return map;
		}
		int result = dictionaryService.updateDicObjList(dics);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;
	}

}
