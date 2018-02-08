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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.DateType;
import com.ltybd.service.DateTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * DateTypeController.java
 *
 * describe:
 * 
 * 2017年11月6日 下午1:42:26 created By chenq version 0.1
 *
 * 2017年11月6日 下午1:42:26 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/DateType", description = "日期类型")
@RestController
@RequestMapping("/DateType/")
@Validated
public class DateTypeController {
	
	@Autowired
	private DateTypeService dateTypeService;
	
	/**
	 * @param dateType
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询日期类型集合
	 * 2017年11月6日下午4:38:51 by chenq version 0.1
	 */
	@ApiOperation(value = "查询日期类型集合 code:015001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "date_type_id", dataType = "int", required = false, value = "日期类型id,非必填"),
		@ApiImplicitParam(paramType = "query", name = "type_name ", dataType = "String", required = false, value = "日期类型名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody  
	public Map<String, Object> findDateTypeList(
			DateType dateType,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		List<DateType> list=new ArrayList<DateType>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(isPage)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数isPage为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		else if(isPage){
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
			if (StringUtils.isEmpty(dateType.getStatus())){
				dateType.setStatus(0);
			}
			Page<DateType> page=PageHelper.startPage(pageNum, pageSize);//分页
			list =dateTypeService.findDateTypeList(dateType);
			Map<String, Object> pageMap=new HashMap<String,Object>();
			pageMap.put("pageNum", page.getPageNum());//页码
			pageMap.put("pageSize", page.getPageSize());//每页条数
			pageMap.put("pagetotal", page.getPages());//总页数
			pageMap.put("total", page.getTotal());//总条数
			mapData.put("page", pageMap);
		}else{
			list =dateTypeService.findDateTypeList(dateType);
		}
		mapData.put("dataTypeList", list);
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * @param date_type_id
	 * @return
	 * Map<String,Object>
	 * describe:删除日期类型信息对象
	 * 2017年11月6日下午2:39:00 by chenq version 0.1
	 */
	@ApiOperation(value = "删除日期类型信息对象  code:015002",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "date_type_id", dataType = "int", required = false, value = "日期类型ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> deleteDateType(@Min(value=1000,message="请输入大于1000的正整数")Integer date_type_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		DateType dateType =new DateType();
		dateType.setDate_type_id(date_type_id);
		if(dateTypeService.findByDateTypeId(dateType)==null){
			map.put("result", "1");
			map.put("resultMsg", "删除的日期类型对象不存在!");
			map.put("resPonse", "删除失败!");
		}else{
			int result = dateTypeService.deleteDateType(dateType);
			if(result > 0){
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse","删除失败!");
			}
		}
		return map;	
	  }
	
	/**
	 * @param date_type_id
	 * @return
	 * Map<String,Object>
	 * describe:查询日期类型信息对象 
	 * 2017年11月6日下午2:49:00 by chenq version 0.1
	 */
	@ApiOperation(value = "查询日期类型信息对象  code:015003",produces ="application/json")
	@RequestMapping(value="entity",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "date_type_id", dataType = "int", required = false, value = "日期类型ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> findByDateTypeId(@NotNull(message="date_type_id不能为空") 
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	Integer date_type_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		DateType dateType =new DateType();
		dateType.setDate_type_id(date_type_id);
		dateType=dateTypeService.findByDateTypeId(dateType);
		if(null==dateType){
			map.put("result", "1");
			map.put("resultMsg", "查询的日期类型对象不存在!");
			map.put("resPonse", "查询失败!");
		}else{
			map.put("resPonse",dateType);
		}
		return map;	
	  }
	
	/**
	 * @param dateType
	 * @return
	 * Map<String,Object>
	 * describe:
	 * 2017年11月6日下午3:49:00 by chenq version 0.1
	 */
	@ApiOperation(value = "更新日期类型信息对象  code:015004",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateDateType(@RequestBody @Validated DateType dateType,BindingResult result)
	{
		Map<String,Object> map =new HashMap<String,Object>();
		Integer date_type_id=0;
		if(result.hasErrors()){
			List<ObjectError> errorList =result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败");
			map.put("resPonse", errorList);
		}else{
			if (dateTypeService.findByDateTypeId(dateType)!=null){
				date_type_id = dateTypeService.findByDateTypeId(dateType).getDate_type_id();
				dateType.setCreate_date(new Date());
				dateTypeService.updateDateType(dateType);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此日期类型对象无法更新");
				return map;
			}
			map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", date_type_id);
		}
		
		return map;
	}
	
	/**
	 * @param dateType
	 * @return
	 * Map<String,Object>
	 * describe:增加日期类型信息对象
	 * 2017年11月6日下午3:49:00 by chenq version 0.1
	 */
	@ApiOperation(value = "增加日期类型信息对象  code:015005",produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertDateType(@RequestBody @Validated DateType dateType,BindingResult result)
	{
		Map<String,Object> map =new HashMap<String,Object>();
		Integer date_type_id=0;
		if(result.hasErrors()){
			List<ObjectError> errorList =result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败");
			map.put("resPonse", errorList);
		}else{
			if(StringUtils.isEmpty(dateType.getType_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "日期类型Type_name不能为空!");
				return map;
			}else{
				date_type_id =dateTypeService.getSequence("date_code");
				dateType.setDate_type_id(date_type_id);
				dateType.setStatus(0);
				dateType.setCreate_date(new Date());
				dateTypeService.insertDateType(dateType);
			}
			map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", date_type_id);
		}
		return map;
	}
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量删除日期类型信息对象
	 * 2017年11月6日下午4:49:00 by chenq version 0.1
	 */
	@ApiOperation(value = "批量删除日期类型信息对象  code:015006",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "日期类型ID集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteDateTypeItems(@NotNull(message="ids不能为空") String ids)
	{
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 String[] idss = ids.split(",");
		 int count=0;
		 String str="";
		 for(int i=0;i<idss.length;i++){
			 DateType dateType =new DateType();
			 dateType.setDate_type_id(Integer.parseInt(idss[i]));
			 if(dateTypeService.findByDateTypeId(dateType)==null){
				 count++;
				 str+=dateType.getDate_type_id()+",";
			 }
			 if (str.length()!=0){
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的id为:"+str.substring(0,str.lastIndexOf(","))); 
			 }else{
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条");
			 }
		 }
		 int result = dateTypeService.deleteDateTypeItems(ids);
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("result", "1");
				map.put("resPonse", "删除失败!");
			}
		return map;
	}
	
	/**
	 * @return
	 * Map<String,Object>
	 * describe:批量导入日期类型对象 
	 * 2017年11月7日下午5:09:36 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导入日期类型对象  code:015007", produces = "application/json")
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
	         Sheet sheet=bWorkbook.getSheet(0);
	         for(int i=0;i<sheet.getRows()-1;i++){
	        	 DateType dateType =new DateType();
	        	//获取单元格对象  
	             Cell cell =sheet.getCell(0,i);
	           //获取单元格的值  
	             dateType.setDate_type_id(dateTypeService.getSequence("date_code"));
	             dateType.setType_name(sheet.getCell(0,i+1).getContents());
	             dateType.setStart_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(1, i+1).getContents()));
	             dateType.setEnd_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(2, i+1).getContents()));
	             dateType.setPriority(sheet.getCell(3,i+1).getContents());
	             dateType.setStatus(Integer.parseInt(sheet.getCell(4,i+1).getContents()));
	             dateType.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(5, i+1).getContents()));
	             dateTypeService.insertDateType(dateType);
	             result+=","+dateType.getDate_type_id();
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
	 * describe:批量导出日期类型对象
	 * 2017年11月10日下午6:07:35 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导出日期类型对象  code:015008",produces ="application/json")
	@RequestMapping(value="batchExportList",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "道路ID集合,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> batchExportList(HttpServletRequest request,HttpServletResponse response,String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		List<DateType> list=new ArrayList<DateType>();
		if(StringUtils.isEmpty(ids)){
			 list=dateTypeService.findDateTypeList(new DateType());
		 }else{
			 list=dateTypeService.batchfindListObj(ids);
		 }
		try {
            // 创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("表名");
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            String colNames="日期类型名,开始时间,结束时间,优先级,状态,创建时间";
            String[] colNameArray = colNames.split(",");
            HSSFRow rowHeader = sheet.createRow(0);
            for (int i = 0; i < colNameArray.length; i++) {
                rowHeader.createCell(i).setCellValue(colNameArray[i]);
            }
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);//从第二行开始创建
                DateType dateType = list.get(i);
                row.createCell(0).setCellValue(String.valueOf(dateType.getType_name()));
                row.createCell(1).setCellValue(String.valueOf(dateType.getStart_date()));
                row.createCell(2).setCellValue(String.valueOf(dateType.getEnd_date()));
                row.createCell(3).setCellValue(String.valueOf(dateType.getPriority()));
                row.createCell(4).setCellValue(String.valueOf(dateType.getStatus()));
                row.createCell(5).setCellValue(String.valueOf(dateType.getCreate_date()));
            }

            // 输出Excel文件
            OutputStream output;
            output = response.getOutputStream();
            response.reset();
            String exportName="日期类型信息列表";
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
