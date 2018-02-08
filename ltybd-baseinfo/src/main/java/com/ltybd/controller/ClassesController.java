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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Classes;
import com.ltybd.entity.ClassesBean;
import com.ltybd.entity.ClassesTime;
import com.ltybd.entity.ClassesTimeBean;
import com.ltybd.service.ClassesService;
import com.ltybd.service.ClassesTimeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * ClassesController.java
 *
 * describe:班次信息
 * 
 * 2017年11月6日 下午8:10:19 created By Chenjw version 0.1
 *
 * 2017年11月6日 下午8:10:19 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/classes", description = "班次信息")
@RestController
@RequestMapping("/classes/")
@Validated
public class ClassesController {
	@Autowired
	private ClassesService classesService;
	@Autowired
	private ClassesTimeService classesTimeService;
	
	/**
	 * @param classes
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询班次集合
	 * 2017年11月9日下午2:13:24 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询班次集合 code:017001",produces ="application/json")
	@RequestMapping(value="classeslist",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "classes_name", dataType = "String", required = false, value = "班次名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody  
	public Map<String, Object> findClassesList(
			Classes classes,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(classes.getStatus())) classes.setStatus(0);
		List<Classes> list=new ArrayList<Classes>();
		if(isPage){
			if(StringUtils.isEmpty(pageNum) || pageNum<=0){
				map.put("result", "1");
				map.put("resultMsg", "请求失败,isPage为true时,参数pageNum必须为大于0的正整数!");
				map.put("resPonse", "查询失败");
				return map;
			}
			if(StringUtils.isEmpty(pageSize) || pageSize<=0){
				map.put("result", "1");
				map.put("resultMsg", "请求失败,isPage为true时,参数pageSize必须为大于0的正整数!");
				map.put("resPonse", "查询失败");
				return map;
			}
			if(pageNum==0) pageNum=1;//初始页码
			if(pageSize==0) pageSize=15;//初始每页条数
			Page<Classes> page=PageHelper.startPage(pageNum, pageSize);//分页
			list=classesService.findClassesList(classes);
			Map<String, Object> pageMap=new HashMap<String,Object>();
			pageMap.put("pageNum", page.getPageNum());//页码
			pageMap.put("pageSize", page.getPageSize());//每页条数
			pageMap.put("pagetotal", page.getPages());//总页数
			pageMap.put("total", page.getTotal());//总条数
			mapData.put("page", pageMap);
		}else{
			list=classesService.findClassesList(classes);
		}
		mapData.put("classesList", list);
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * @param classes_id
	 * @return
	 * Map<String,Object>
	 * describe:查询班次信息对象
	 * 2017年11月9日下午2:13:38 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询班次信息对象  code:017002", produces ="application/json")
	@RequestMapping(value="object",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "classes_id", dataType = "int", required = false, value = "班次ID,必填,大于等于1正整数")})
	public Map<String, Object> findByClassesId(
			@NotNull(message="classes_id不能为空") 
			@Min(value=1,message="必须为大于或者等于1的正整数") 
			@RequestParam Integer classes_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",classesService.findById(classes_id)!=null?classesService.findById(classes_id):"没有查找到对应数据!" );
		return map;
	}
	
	/**
	 * @param classes
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:插入班次信息对象
	 * 2017年11月9日下午2:13:49 by Chenjw version 0.1
	 */
	@ApiOperation(value = "插入班次信息对象  code:017003", produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insert(@RequestBody @Validated Classes classes,BindingResult result){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		classes.setStatus(0);
    		classes.setCreate_date(new Date());
    		classesService.insert(classes);
    		map.put("resPonse", "新增班次成功!");
        }
		return map;
	}
	
	/**
	 * @param classes
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:修改 班次信息对象
	 * 2017年11月9日下午2:14:03 by Chenjw version 0.1
	 */
	@ApiOperation(value = "修改 班次信息对象  code:017004", produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> update(@RequestBody @Validated ClassesBean classes,BindingResult result){
		Map<String, Object> map=new HashMap<String,Object>();
		
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", "修改班次成功!");
    		if(classesService.findById(classes.getClasses_id())!=null){
    			Classes obj=new Classes();
    			obj.setClasses_id(classes.getClasses_id());
    			obj.setClasses_name(classes.getClasses_name());
    			obj.setStatus(classes.getStatus());
				classesService.update(obj);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此班次对象无法更新");
				return map;
			}
        }
		return map;
	}
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:删除班次
	 * 2017年11月9日下午2:14:27 by Chenjw version 0.1
	 */
	@ApiOperation(value = "删除班次信息对象  code:017005",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "班次ID,必填,多个以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteItems(@NotNull(message="ids不能为空") String ids)
	{
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 String[]idss=ids.split(",");
		 int count=0;
		 String str="";
		 String failStr="";
		 for (int i = 0; i < idss.length; i++) {
			if(classesService.findById(Integer.parseInt(idss[i]))==null){
				count++;
				if(StringUtils.isEmpty(str)){
					str=idss[i];
				}else{
					str=str+idss[i];
				}
			}
		 }
		 if(StringUtils.isEmpty(str)){
			 failStr="成功删除"+idss.length+"条记录";
		 }else{
			 failStr="成功删除"+(idss.length-count)+"条记录,失败"+count+"条,删除失败的有:"+str;
		 }
		 int result=classesService.deleteItems(ids);
		 if(result>0){ 
				map.put("resPonse",failStr);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "删除失败!");
			}
		 return map;
	}
	
	
	/**
	 * @param classes_id
	 * @return
	 * Map<String,Object>
	 * describe:查询班次下的班次时间列表
	 * 2017年11月9日下午2:14:57 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询班次下的班次时间列表 code:017006", produces = "application/json")
	@RequestMapping(value = "findTimeList", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "classes_id", dataType = "int", required = false, value = "班次id,必填"), })
	@ResponseBody
	public Map<String, Object> findTimeList(
			@NotNull(message="classes_id不能为空") 
			@Min(value=1,message="必须为大于或者等于1的正整数") 
			@RequestParam Integer classes_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",classesTimeService.findTimeList(classes_id).size()>0?classesTimeService.findTimeList(classes_id):"没有查找到对应数据!" );
		return map;
	}
	
	/**
	 * @param classes_time_id
	 * @return
	 * Map<String,Object>
	 * describe:查询班次时间对象
	 * 2017年11月9日下午2:15:06 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询班次时间对象 code:017007", produces = "application/json")
	@RequestMapping(value = "findTimeObj", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "classes_time_id", dataType = "int", required = false, value = "班次时间id,必填"), })
	@ResponseBody
	public Map<String, Object> findById(
			@NotNull(message="classes_time_id不能为空") 
			@Min(value=1,message="必须为大于或者等于1的正整数") 
			@RequestParam Integer classes_time_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		ClassesTime obj=new ClassesTime();
		obj.setClasses_time_id(classes_time_id);
		map.put("resPonse",classesTimeService.findById(obj)!=null?classesTimeService.findById(obj):"没有查找到对应数据!" );
		return map;
	}

	
	/**
	 * @param classesTime
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:新增班次时间
	 * 2017年11月9日下午2:15:16 by Chenjw version 0.1
	 */
	@ApiOperation(value = "新增班次时间 code:017008", produces = "application/json")
	@RequestMapping(value = "insertTime", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertTimeObj(@RequestBody @Validated ClassesTime classesTime,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()){
			List<ObjectError> errorList = result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse", errorList);
			return map;
		}
		classesTime.setCreate_date(new Date());
		classesTimeService.insertClassesTime(classesTime);
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse","新增班次时间成功");
		return map;
	}
	
	/**
	 * @param classesTimeBean
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:修改班次时间
	 * 2017年11月9日下午2:15:25 by Chenjw version 0.1
	 */
	@ApiOperation(value = "修改班次时间 code:017009", produces = "application/json")
	@RequestMapping(value = "updateTime", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateTimeObj(@RequestBody @Validated ClassesTimeBean classesTimeBean,BindingResult result) {
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
		ClassesTime timeObj=new ClassesTime();
		timeObj.setClasses_time_id(classesTimeBean.getClasses_time_id());
		timeObj.setStart_time(classesTimeBean.getStart_time());
		timeObj.setEnd_time(classesTimeBean.getEnd_time());
		if(classesTimeService.findById(timeObj)==null){
			map.put("result", "0");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse","没有查询到要修改的对象");
		}else{
			int res=classesTimeService.updateClassesTime(timeObj);
			if(res>0){
				map.put("resPonse","修改班次时间成功");
			}else{
				map.put("resPonse","修改班次时间失败");
			}
		}
		return map;
	}
	
	/**
	 * @param classes_time_id
	 * @return
	 * Map<String,Object>
	 * describe:删除班次时间
	 * 2017年11月9日下午2:15:36 by Chenjw version 0.1
	 */
	@ApiOperation(value = "删除班次时间code:017010", produces = "application/json")
	@RequestMapping(value = "deleteTime", method = { RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "classes_time_id", dataType = "int", required = false, value = "班次时间id,必填"), })
	@ResponseBody
	public Map<String, Object> deleteDicObj(
			@NotNull(message="classes_time_id不能为空") 
			@Min(value=1,message="必须为大于或者等于1的正整数") 
			@RequestParam Integer classes_time_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		ClassesTime obj=new ClassesTime();
		obj.setClasses_time_id(classes_time_id);
		if(classesTimeService.findById(obj)!=null){
			int result = classesTimeService.deleteClassesTime(obj);
			if (result > 0) {
				map.put("resPonse", "删除成功!");
			} else {
				map.put("resPonse", "删除失败!");
			}
		}else{
			map.put("resPonse", "删除的对象不存在!");
		}
		return map;
	}
	
}
