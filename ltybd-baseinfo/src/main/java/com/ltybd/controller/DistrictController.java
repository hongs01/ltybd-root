package com.ltybd.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.ltybd.entity.District;
import com.ltybd.entity.Road;
import com.ltybd.service.DistrictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * DistrictController.java
 *
 * describe:
 * 
 * 2017年11月8日 上午9:00:52 created By chenq version 0.1
 *
 * 2017年11月8日 上午9:00:52 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/District", description = "区域信息")
@RestController
@RequestMapping("/District/")
@Validated
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;
	
	/**
	 * @param district
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return 
	 * Map<String,Object>
	 * describe:查询区域集合
	 * 2017年11月8日上午10:27:36 by chenq version 0.1
	 */
	@ApiOperation(value = "查询区域集合 code:019001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "district_id", dataType = "int", required = false, value = "区域id,非必填"),
		@ApiImplicitParam(paramType = "query", name = "district_name ", dataType = "String", required = false, value = "区域名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody 
	public Map<String, Object> findDistrictList(
			District district,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage)
	{
		Map<String,Object> map=new HashMap<String,Object>();
		List<District> list=new ArrayList<District>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(isPage)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数isPage为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}else if(isPage){
				if(StringUtils.isEmpty(pageNum)){
					map.put("resultMsg", "请求失败,isPage为true时,参数pageNum必须为大于或等于0的整数!");
					map.put("resPonse", "查询失败");
					return map;
				}
				if(StringUtils.isEmpty(pageSize)){
					map.put("result", "1");
					map.put("resultMsg", "请求失败,isPage为true时,参数pageSize必须为大于或等于0的整数!");
					map.put("resPonse", "查询失败");
					return map;
				}
				if(pageNum==0){
					pageNum=1;//初始页码 
				}
				if(pageSize==0){
					pageSize=15;//初始每页条数 
				}
				if (StringUtils.isEmpty(district.getStatus())){
					district.setStatus(0);
				}
				Page<District> page=PageHelper.startPage(pageNum, pageSize);//分页
				list=districtService.findDistrictList(district);
				Map<String, Object> pageMap=new HashMap<String,Object>();
				pageMap.put("pageNum", page.getPageNum());//页码
				pageMap.put("pageSize", page.getPageSize());//每页条数
				pageMap.put("pagetotal", page.getPages());//总页数
				pageMap.put("total", page.getTotal());//总条数
				mapData.put("page", pageMap);
		}else{
			list=districtService.findDistrictList(district);
		}
		mapData.put("districtList", list);
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * @param district_id
	 * @return
	 * Map<String,Object>
	 * describe:删除区域类型信息对象
	 * 2017年11月8日上午9:11:03 by chenq version 0.1
	 */
	@ApiOperation(value = "删除区域信息对象  code:019002",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "district_id", dataType = "int", required = false, value = "区域ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> deleteDistrict(@Min(value=1000,message="请输入大于1000的正整数") Integer district_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		District district =new District();
		district.setDistrict_id(district_id);
		if(null==districtService.findDistrictById(district)){
			map.put("result", "1");
			map.put("resultMsg", "删除的区域对象不存在!");
			map.put("resPonse", "删除失败!");
		}else{
			int result=districtService.deleteDistrict(district);
			if(result > 0){
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse","删除失败!");
			}
		}
		return map;
	}
	
	
	/**
	 * @param district_id
	 * @return
	 * Map<String,Object>
	 * describe:查询区域类型信息对象
	 * 2017年11月8日上午9:10:43 by chenq version 0.1
	 */
	@ApiOperation(value = "查询区域类型信息对象  code:019003",produces ="application/json")
	@RequestMapping(value="districtEntity",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "district_id", dataType = "int", required = false, value = "区域ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> findDistrictById(@Min(value=1000,message="必须为大于或者等于1000的正整数") Integer district_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		District district =new District();
		district.setDistrict_id(district_id);
		district = districtService.findDistrictById(district);
		if (null==district){
			map.put("result", "1");
			map.put("resultMsg", "查询的区域对象不存在!");
			map.put("resPonse", "查询失败!");
		}else{
			map.put("resPonse",district);
		}
		return map;
	}
	
	
	/**
	 * @param district_id
	 * @return
	 * Map<String,Object>
	 * describe:通过区域id查询道路信息
	 * 2017年11月8日上午9:10:43 by chenq version 0.1
	 */
	@ApiOperation(value = "通过区域id查询道路信息  code:019004",produces ="application/json")
	@RequestMapping(value="RoadEntity",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "district_id", dataType = "int", required = false, value = "区域ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> findRoadsByDistrictId(
	@Min(value=1000,message="必须为大于或者等于1000的正整数") Integer district_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		District district =new District();
		district.setDistrict_id(district_id);
		List<Road> roadList = districtService.findRoadsByDistrictId(district);
		if (roadList.isEmpty()){
			map.put("result", "1");
			map.put("resultMsg", "此区域下没有道路!");
			map.put("resPonse", "查询失败!");
		}else{
			map.put("resPonse",roadList);
		}
		return map;
	}
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量删除区域信息对象
	 * 2017年11月8日上午9:33:42 by chenq version 0.1
	 */
	@ApiOperation(value = "批量删除区域信息对象  code:019005",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "区域ID集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteDistrictItems(@NotNull(message="ids不能为空") String ids)
	{
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 String[] idss =ids.split(",");
		 int count=0;
		 String str="";
		 for(int i=0;i<idss.length;i++){
			 District district=new District();
			 district.setDistrict_id(Integer.parseInt(idss[i]));
			 if(null==districtService.findDistrictById(district)){
				 count++;
				 str+=district.getDistrict_id()+",";
			 }
			 if (str.length()!=0){
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的id为:"+str.substring(0,str.lastIndexOf(","))); 
			 }else{
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条");
			 }
		 }
		 int result=districtService.deleteDistrictItems(ids);
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("result", "1");
				map.put("resPonse", "删除失败!");
			}
		return map;
	}
	
	/**
	 * @param district
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:增加区域信息对象
	 * 2017年11月8日上午9:49:01 by chenq version 0.1
	 */
	@ApiOperation(value = "增加日期类型信息对象  code:019006",produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertDistrict(@RequestBody @Validated District district,BindingResult result)
	{
		 Map<String,Object> map =new HashMap<String,Object>();
		 Integer district_id=0;
		 if(result.hasErrors()){
			 List<ObjectError> errorList=result.getAllErrors();
			 map.put("result", "1");
				map.put("resultMsg", "请求失败");
				map.put("resPonse", errorList);
		 }else{
			 if(StringUtils.isEmpty(district.getDistrict_name())){
				 map.put("result", "1");
				 map.put("resultMsg", "请求失败!");
				 map.put("resPonse", "区域District_name不能为空!");
				 return map;
			 }else{
				 district_id=districtService.getSequence("district_code");
				 district.setDistrict_id(district_id);
				 district.setStatus(0);
				 district.setCreate_date(new Date());
				 districtService.insertDistrict(district);
			 }
			    map.put("result", "0");
	    		map.put("resultMsg", "请求成功!");
	    		map.put("resPonse", district_id);
		 }
		return map;
	}
	
	/**
	 * @param district
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:修改区域信息对象
	 * 2017年11月8日上午9:49:01 by chenq version 0.1
	 */
	@ApiOperation(value = "修改日期类型信息对象  code:019007",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateDistrict(@RequestBody @Validated District district,BindingResult result)
	{
		Map<String,Object> map =new HashMap<String,Object>();
		Integer district_id=0;
	    if(result.hasErrors()){
		 List<ObjectError> errorList=result.getAllErrors();
		 map.put("result", "1");
			map.put("resultMsg", "请求失败");
			map.put("resPonse", errorList);
	    }else{
	    	if(districtService.findDistrictById(district)!=null){
	    		district_id =districtService.findDistrictById(district).getDistrict_id();
	    		district.setCreate_date(new Date());
	    		districtService.updateDistrict(district);
	    	}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此区域对象无法更新");
				return map;
			}
	    	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", district_id);
	     }
		return map;	
	}
	
	/**
	 * @return
	 * Map<String,Object>
	 * describe:批量导入区域类型对象
	 * 2017年11月8日上午11:06:18 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导入区域对象  code:019008", produces = "application/json")
	@RequestMapping(value = "batchImportList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> batchImportList(String filePath) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!"); 
		Workbook bWorkbook=null;  
	    String result="";
	    try{
	    	 bWorkbook=Workbook.getWorkbook(new File(filePath));  
	    	 if (new File(filePath).exists()){
	    		 Sheet sheet=bWorkbook.getSheet(0);
		         for(int i=0;i<sheet.getRows()-1;i++){
		        	 District district =new District();
		        	//获取单元格对象  
		             Cell cell =sheet.getCell(0,i);
		           //获取单元格的值  
		             district.setDistrict_id(districtService.getSequence("district_code"));
		             district.setDistrict_code(sheet.getCell(0,i+1).getContents());
		             district.setDistrict_name(sheet.getCell(1,i+1).getContents());
		             district.setStatus(Integer.parseInt(sheet.getCell(2,i+1).getContents()));
		             district.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(3,i+1).getContents()));
		             districtService.insertDistrict(district);
		             result+=","+district.getDistrict_id();
		         }
	    	 }else{
	    		map.put("result", "1");
	    		map.put("resultMsg", "不存在该文件!");
	    	 }
	    }catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {
			e.printStackTrace();
		}finally {  
            bWorkbook.close();  
        }  
	    map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", result);
		return map;
	}
	

	
	/**
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量导出区域对象
	 * 2017年11月10日下午1:58:56 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导出区域对象  code:019009",produces ="application/json")
	@RequestMapping(value="batchExportList",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "区域ID集合,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> batchExportList(HttpServletRequest request,HttpServletResponse response,String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		List<District> list=new ArrayList<District>();
		if(StringUtils.isEmpty(ids)){
			 list=districtService.findDistrictList(new District());
		 }else{
			 list=districtService.batchfindListObj(ids);
		 }
		try {
            // 创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("表名");
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            String colNames="区域编码,区域名称,状态,创建时间";
            String[] colNameArray = colNames.split(",");
            HSSFRow rowHeader = sheet.createRow(0);
            for (int i = 0; i < colNameArray.length; i++) {
                rowHeader.createCell(i).setCellValue(colNameArray[i]);
            }
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);//从第二行开始创建
                District district = list.get(i);
                row.createCell(0).setCellValue(String.valueOf(district.getDistrict_code()));
                row.createCell(1).setCellValue(String.valueOf(district.getDistrict_name()));
                row.createCell(2).setCellValue(String.valueOf(district.getStatus()));
                row.createCell(3).setCellValue(String.valueOf(district.getCreate_date()));
            }

            // 输出Excel文件
            OutputStream output;
            output = response.getOutputStream();
            response.reset();
            String exportName="区域信息列表";
            String str = new String(exportName.getBytes("UTF-8"),"ISO-8859-1");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8"); // 下载文版类型
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition", "attachment; filename=" + str + ".xls");
            response.setCharacterEncoding("UTF-8");
            wb.write(output);
            output.close();
        } catch(Exception e) {
        	map.put("result", "0");
     		map.put("resultMsg", "请求异常!");
     		map.put("resPonse","导出失败");
     		e.printStackTrace();
        }  
		return map;
	}
}
