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
import com.ltybd.entity.Terminal;
import com.ltybd.entity.TerminalBean;
import com.ltybd.service.TerminalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * TerminalController.java
 *
 * describe:设备信息
 * 
 * 2017年10月13日 下午2:38:51 created By Chenjw version 0.1
 *
 * 2017年10月13日 下午2:38:51 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="/terminal", description = "设备信息")
@RestController
@RequestMapping("/terminal/")
@Validated
public class TerminalController {
	@Autowired
	private TerminalService terminalService;
	
	/**
	 * @param terminal
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询设备信息集合
	 * 2017年10月12日上午10:10:19 by Chejw version 0.1
	 */
	@ApiOperation(value = "查询设备信息集合 code:004001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "terminal_id", dataType = "int", required = false, value = "设备编号,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "terminal_type", dataType = "String", required = false, value = "设备类型,非必填"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findListObj(Terminal terminal,Integer pageNum,Integer pageSize,Boolean isPage)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(isPage)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数isPage为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}else {
			if(StringUtils.isEmpty(terminal.getStatus())) terminal.setStatus(0);
			List<TerminalBean> list=new ArrayList<TerminalBean>();
			if(isPage){
				if(StringUtils.isEmpty(pageNum)){
					map.put("result", "1");
					map.put("resultMsg", "请求失败,isPage为true时,参数pageNum为必填项!");
					map.put("resPonse", "查询失败");
					return map;
				}
				if(StringUtils.isEmpty(pageSize)){
					map.put("result", "1");
					map.put("resultMsg", "请求失败,isPage为true时,参数pageSize为必填项!");
					map.put("resPonse", "查询失败");
					return map;
				}
				if(pageNum==0) pageNum=1;//初始页码
				if(pageSize==0) pageSize=15;//初始每页条数
				Page<Terminal> page=PageHelper.startPage(pageNum, pageSize);//分页
				list=terminalService.findListObj(terminal);
				Map<String, Object> pageMap=new HashMap<String,Object>();
				pageMap.put("pageNum", page.getPageNum());//页码
				pageMap.put("pageSize", page.getPageSize());//每页条数
				pageMap.put("pagetotal", page.getPages());//总页数
				pageMap.put("total", page.getTotal());//总条数
				mapData.put("page", pageMap);
			}else{
				list=terminalService.findListObj(terminal);
			}
			mapData.put("terminalList", list);
		}
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * 
	 * @param terminal_id
	 * @return
	 * Map<String, Object>
	 * describe:查询设备信息对象
	 * 2017年10月12日上午10:10:29 by Chejw version 0.1
	 * @throws Exception 
	 */
	@ApiOperation(value = "查询设备信息对象  code:004002", produces ="application/json")
	@RequestMapping(value="entity",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "terminal_id", dataType = "int", required = false, value = "设备编号,必填"), })
	@ResponseBody
	public Map<String, Object> findByDicId(
			@NotNull(message="terminal_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer terminal_id) throws Exception{
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(terminal_id)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数terminal_id为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		Terminal terminal=new Terminal();
		terminal.setTerminal_id(terminal_id);
		map.put("resPonse",terminalService.findByTerminalId(terminal)!=null?terminalService.findByTerminalId(terminal):"没有查找到对应数据!" );
		return map;
	}
	
	/**
	 * @param terminal
	 * @return
	 * Map<String, Object>
	 * describe:更新设备信息对象
	 * 2017年10月12日上午10:10:29 by LinSir version 0.1
	 */
	@ApiOperation(value = "更新设备信息对象  code:004003",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateDicObj(@RequestBody @Validated Terminal terminal,BindingResult error){
		Map<String, Object> map=new HashMap<String,Object>();
		Integer terminal_id=0;
		if (error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
        }
		if(StringUtils.isEmpty(terminal.getTerminal_id())){
			terminal.setStatus(0);
			terminal.setCreate_time(new Date());
			terminalService.insert(terminal);
			map.put("resPonse", "保存成功");
		}else{
			if(terminalService.findByTerminalId(terminal)!=null){
				terminalService.update(terminal);
				map.put("resPonse", "修改成功");
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse","没有查到此设备对象无法更新");
				return map;
			}
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		
		return map;
	}
	
	/**
	 * @param terminal_id
	 * @return
	 * Map<String, Object>
	 * describe:删除设备信息对象
	 * 2017年10月12日上午10:10:32 by Chejw version 0.1
	 */
	@ApiOperation(value = "删除设备信息对象  code:004004",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "terminal_id", dataType = "int", required = false, value = "设备编号,必填"), })
	@ResponseBody
	public Map<String, Object> deleteDicObj(
			@NotNull(message="terminal_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数")
			Integer terminal_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(terminal_id)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数terminal_id为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		Terminal terminal=new Terminal();
		terminal.setTerminal_id(terminal_id);
		int result=terminalService.delete(terminal);
		if(result>0){ 
			map.put("resPonse","删除成功!");
		}else{
			map.put("resPonse", "删除失败!");
		}
		return map;
	}
	
	/**
	 * @param ids
	 * Map<String, Object>
	 * describe:批量删除设备信息对象
	 * 2017年10月12日上午10:10:35 by Chejw version 0.1
	 */
	@ApiOperation(value = "批量删除设备信息对象  code:004005",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "设备编号集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteItems(String ids)
	{
		 Map<String, Object> map=new HashMap<String,Object>();
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
			 Terminal terminal=new Terminal();
			 terminal.setTerminal_id(Integer.parseInt(idss[i]));
			 if(terminalService.findByTerminalId(terminal)==null){
					count++;
					str+=","+terminal.getTerminal_id();
				}
			 }
		 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的是"+str);
		 int result=terminalService.deleteItems(ids);
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse", "删除失败!");
			}
		 return map;
	}
	
	/**
	 * 
	 * @param list
	 * @return Map<String,Object>
	 * @describe:批量更新设备信息对象  code:030004006
	 * @2017年10月16日上午10:59:04 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新设备信息对象  code:004006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Terminal> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,请传参数");
			map.put("resPonse", "更新失败");
			return map;
		}
		int result = terminalService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;

	}
}
