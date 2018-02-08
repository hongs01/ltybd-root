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
import com.ltybd.entity.Shift;
import com.ltybd.entity.ShiftBean;
import com.ltybd.entity.ShiftClasses;
import com.ltybd.service.ShiftService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * ShiftController.java
 *
 * describe:班制信息
 * 
 * 2017年11月6日 下午5:34:53 created By Chenjw version 0.1
 *
 * 2017年11月6日 下午5:34:53 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/shift", description = "班制信息")
@RestController
@RequestMapping("/shift/")
@Validated
public class ShiftController {
	@Autowired
	private ShiftService shiftService;
	
	/**
	 * @param shift
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询班次集合
	 * 2017年11月10日上午10:59:50 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询班次集合 code:016001",produces ="application/json")
	@RequestMapping(value="shiftlist",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "shift_name", dataType = "String", required = false, value = "班制名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody  
	public Map<String, Object> findShiftList(
			Shift shift,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		List<Shift> list=new ArrayList<Shift>();
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
			Page<Shift> page=PageHelper.startPage(pageNum, pageSize);//分页
			list=shiftService.findShiftList(shift);
			Map<String, Object> pageMap=new HashMap<String,Object>();
			pageMap.put("pageNum", page.getPageNum());//页码
			pageMap.put("pageSize", page.getPageSize());//每页条数
			pageMap.put("pagetotal", page.getPages());//总页数
			pageMap.put("total", page.getTotal());//总条数
			mapData.put("page", pageMap);
		}else{
			list=shiftService.findShiftList(shift);
		}
		mapData.put("shiftList", list);
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * @param shift_id
	 * @return
	 * Map<String,Object>
	 * describe:查询班制信息对象
	 * 2017年11月10日上午11:00:00 by Chenjw version 0.1
	 */
	@ApiOperation(value = "查询班制信息对象  code:016002", produces ="application/json")
	@RequestMapping(value="object",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "shift_id", dataType = "int", required = false, value = "班制ID,必填,大于等于1正整数")})
	public Map<String, Object> findByShiftId(
			@NotNull(message="shift_id不能为空") 
			@Min(value=1,message="必须为大于或者等于1的正整数") 
			@RequestParam Integer shift_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",shiftService.findById(shift_id)!=null?shiftService.findById(shift_id):"没有查找到对应数据!" );
		return map;
	}
	
	/**
	 * @param shift
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:插入班制信息对象
	 * 2017年11月10日上午11:00:10 by Chenjw version 0.1
	 */
	@ApiOperation(value = "插入班制信息对象  code:016003", produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insert(@RequestBody @Validated Shift shift,BindingResult result){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		if(StringUtils.isEmpty(shift.getStatus())){
    			shift.setStatus(0);
    		}
    		shift.setCreate_date(new Date());
    		shiftService.insert(shift);
    		map.put("resPonse", "新增班次成功!");
        }
		return map;
	}
	
	/**
	 * @param shift
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:修改 班制信息对象
	 * 2017年11月10日上午11:00:20 by Chenjw version 0.1
	 */
	@ApiOperation(value = "修改 班制信息对象  code:016004", produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> update(@RequestBody @Validated ShiftBean shift,BindingResult result){
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
    		if(shiftService.findById(shift.getShift_id())!=null){
    			Shift obj=new Shift();
    			obj.setShift_id(shift.getShift_id());
    			obj.setShift_name(shift.getShift_name());
    			obj.setStatus(shift.getStatus());
    			shiftService.update(obj);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此班制对象无法更新");
				return map;
			}
        }
		return map;
	}
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:删除班制信息对象 
	 * 2017年11月10日上午11:00:29 by Chenjw version 0.1
	 */
	@ApiOperation(value = "删除班制信息对象  code:016005",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "班制ID,必填,多个以(,)隔开,例:1,2,3"), })
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
			if(shiftService.findById(Integer.parseInt(idss[i]))==null){
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
		 int result=shiftService.deleteItems(ids);
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
	 * @param shiftClasses
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:插入班制与班次对象
	 * 2017年11月10日上午11:00:38 by Chenjw version 0.1
	 */
	@ApiOperation(value = "插入班制与班次对象  code:016006", produces ="application/json")
	@RequestMapping(value="insertShiftClasses",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertShiftClasses(@RequestBody @Validated ShiftClasses shiftClasses,BindingResult result){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		shiftClasses.setCreate_date(new Date());
    		shiftService.insertShiftClasses(shiftClasses);
    		map.put("resPonse", "新增班次成功!");
        }
		return map;
	}
	
	/**
	 * @param classes_id
	 * @param seq_id
	 * @return
	 * Map<String,Object>
	 * describe:修改 班制与班次对象
	 * 2017年11月10日上午11:00:48 by Chenjw version 0.1
	 */
	@ApiOperation(value = "修改 班制与班次对象  code:016007", produces ="application/json")
	@RequestMapping(value="updateShiftClasses",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "classes_id", dataType = "int", required = false, value = "班次ID,必填"),
		@ApiImplicitParam(paramType = "query", name = "seq_id", dataType = "int", required = false, value = "主键ID,必填"),})
	@ResponseBody
	public Map<String, Object> updateShiftClasses(
			@NotNull(message="班次ID不能为空")
			@Min(value=1,message="必须为大于或者等于1的正整数")
			@RequestParam Integer classes_id,
			@NotNull(message="主键ID不能为空")
			@Min(value=1,message="必须为大于或者等于1的正整数")
			@RequestParam Integer seq_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", "修改班次成功!");
		if(shiftService.selectShiftClasse(seq_id)!=null){
			shiftService.updateShiftClasses(classes_id,seq_id);
		}else{
			map.put("result", "1");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse", "没有查到此班制对象无法更新");
			return map;
		}
		return map;
	}
	
	
	/**
	 * @param shift_id
	 * @return
	 * Map<String,Object>
	 * describe:修改 班制与班次对象
	 * 2017年11月10日上午11:00:59 by Chenjw version 0.1
	 */
	@ApiOperation(value = "修改 班制与班次对象  code:016008", produces ="application/json")
	@RequestMapping(value="selectShiftClassesList",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "shift_id", dataType = "int", required = false, value = "班制ID,必填,大于等于1正整数")})
	@ResponseBody
	public Map<String, Object> selectShiftClassesList(
			@NotNull(message="班制ID不能为空")
			@Min(value=1,message="必须为大于或者等于1的正整数")
			@RequestParam Integer shift_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", shiftService.selectShiftClassesList(shift_id));
		return map;
	}
}
