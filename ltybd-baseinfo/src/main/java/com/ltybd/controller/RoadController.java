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
import com.ltybd.entity.Road;
import com.ltybd.entity.Stationblock;
import com.ltybd.service.RoadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


/**
 * RoadController.java
 *
 * describe:
 * 
 * 2017年11月8日 下午7:54:44 created By chenq version 0.1
 *
 * 2017年11月8日 下午7:54:44 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/road", description = "道路信息")
@RestController
@RequestMapping("/road/")
@Validated
public class RoadController {
	@Autowired
	private RoadService roadService;
	
	/**
	 * @param road
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询道路集合
	 * 2017年11月9日上午10:03:21 by chenq version 0.1
	 */
	@ApiOperation(value = "查询道路集合 code:020001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "road_id", dataType = "int", required = false, value = "道路id,非必填"),
		@ApiImplicitParam(paramType = "query", name = "road_name ", dataType = "String", required = false, value = "道路名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody  
	public Map<String, Object> findRoadList(
			Road road,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		List<Road> list=new ArrayList<Road>();
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
			if (StringUtils.isEmpty(road.getStatus())){
				road.setStatus(0);
			}
			Page<Road> page=PageHelper.startPage(pageNum, pageSize);//分页
			list =roadService.findRoadList(road);
			Map<String, Object> pageMap=new HashMap<String,Object>();
			pageMap.put("pageNum", page.getPageNum());//页码
			pageMap.put("pageSize", page.getPageSize());//每页条数
			pageMap.put("pagetotal", page.getPages());//总页数
			pageMap.put("total", page.getTotal());//总条数
			mapData.put("page", pageMap);
		}else{
			list =roadService.findRoadList(road);
		}
		mapData.put("roadList", list);
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * @param road_id
	 * @return
	 * Map<String,Object>
	 * describe:删除道路信息对象
	 * 2017年11月8日下午7:56:19 by chenq version 0.1
	 */
	@ApiOperation(value = "删除道路信息对象  code:020002",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "road_id", dataType = "int", required = false, value = "道路id,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> deleteRoad(Integer road_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Road road =new Road();
		road.setRoad_id(road_id);
		if(null==roadService.findByRoadId(road)){
			map.put("result", "1");
			map.put("resultMsg", "删除的道路对象不存在!");
			map.put("resPonse", "删除失败!");
		}else{
			int result = roadService.deleteRoad(road);
			if(result > 0){
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse","删除失败!");
			}
		}
		return map;	
	  }
	
	/**
	 * @param road_id
	 * @return
	 * Map<String,Object>
	 * describe:查询道路信息对象
	 * 2017年11月8日下午7:57:45 by chenq version 0.1
	 */
	@ApiOperation(value = "查询道路信息对象  code:020003",produces ="application/json")
	@RequestMapping(value="roadEntity",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "road_id", dataType = "int", required = false, value = "道路id,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> findByRoadId(@NotNull(message="road_id不能为空") 
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	Integer road_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Road road =new Road();
		road.setRoad_id(road_id);
		road=roadService.findByRoadId(road);
		if(null==road){
			map.put("result", "1");
			map.put("resultMsg", "查询道路信息对象不存在!");
			map.put("resPonse", "查询失败!");
		}else{
			map.put("resPonse",road);
		}
		return map;	
	  }
	
	
	/**
	 * @param road_id
	 * @return
	 * Map<String,Object>
	 * describe:通过道路id查询站台信息
	 * 2017年11月10日下午6:08:56 by chenq version 0.1
	 */
	@ApiOperation(value = "通过道路id查询站台信息  code:020004",produces ="application/json")
	@RequestMapping(value="stationblockEntity",method= {RequestMethod.POST})
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "road_id", dataType = "int", required = false, value = "道路id,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> findStationsByRoadId(@NotNull(message="road_id不能为空") 
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	Integer road_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Road road =new Road();
		road.setRoad_id(road_id);
		List<Stationblock> stationblockList=roadService.findStationsByRoadId(road);
		if(stationblockList.isEmpty()){
			map.put("result", "1");
			map.put("resultMsg", "此道路下没有站台!");
			map.put("resPonse", "查询失败!");
		}else{
			map.put("resPonse",stationblockList);
		}
		return map;	
	  }
	
	
	
	/**
	 * @param road
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:更新道路信息对象
	 * 2017年11月10日下午6:06:44 by chenq version 0.1
	 */
	@ApiOperation(value = "更新道路信息对象  code:020005",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateRoad(@RequestBody @Validated Road road,BindingResult result)
	{
		Map<String,Object> map =new HashMap<String,Object>();
		Integer road_id=0;
		if(result.hasErrors()){
			List<ObjectError> errorList =result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败");
			map.put("resPonse", errorList);
		}else{
			if (roadService.findByRoadId(road)!=null){
				road_id = roadService.findByRoadId(road).getRoad_id();
				roadService.updateRoad(road);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此道路对象无法更新");
				return map;
			}
			map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", road_id);
		}
		return map;
	}
	
	
	
	/**
	 * @param road
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:增加道路信息对象
	 * 2017年11月10日下午6:07:04 by chenq version 0.1
	 */
	@ApiOperation(value = "增加道路信息对象  code:020006",produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertRoad(@RequestBody @Validated Road road,BindingResult result)
	{
		Map<String,Object> map =new HashMap<String,Object>();
		Integer road_id=0;
		if(result.hasErrors()){
			List<ObjectError> errorList =result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败");
			map.put("resPonse", errorList);
		}else{
			if(StringUtils.isEmpty(road.getRoad_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "道路road_name不能为空!");
				return map;
			}else{
				road_id =roadService.getSequence("road_code");
				road.setRoad_id(road_id);
				road.setCreate_date(new Date() );
				roadService.insertRoad(road);
			}
			map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", road_id);
		}
		return map;
    }
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量删除道路信息对象
	 * 2017年11月9日上午9:51:00 by chenq version 0.1
	 */
	@ApiOperation(value = "批量删除道路信息对象  code:020007",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "道路ID集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteRoadItems(@NotNull(message="ids不能为空") String ids)
	{
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 String[] idss =ids.split(",");
		 int count=0;
		 String str="";
		 for(int i=0;i<idss.length;i++){
			 Road road=new Road();
			 road.setRoad_id((Integer.parseInt(idss[i])));
			 if(null==roadService.findByRoadId(road)){
				 count++;
				 str+=road.getRoad_id()+",";
			 }
			 if (str.length()!=0){
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的id为:"+str.substring(0,str.lastIndexOf(","))); 
			 }else{
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条");
			 }
		 }
		 int result=roadService.deleteRoadItems(ids);
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("result", "1");
				map.put("resPonse", "删除失败!");
			}
		return map;
	}
	
	/**
	 * @param filePath
	 * @return
	 * Map<String,Object>
	 * describe:批量导入道路对象
	 * 2017年11月9日上午10:24:32 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导入道路对象  code:020008", produces = "application/json")
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
		        	Road road =new Road();
		        	//获取单元格对象  
		             Cell cell =sheet.getCell(0,i);
		           //获取单元格的值  
		             road.setRoad_id(roadService.getSequence("road_code"));
		             road.setRoad_code(sheet.getCell(0,i+1).getContents());
		             road.setRoad_name(sheet.getCell(1,i+1).getContents());
		             road.setDistrict_id(Integer.parseInt(sheet.getCell(2,i+1).getContents()));
		             road.setStatus(Integer.parseInt(sheet.getCell(3,i+1).getContents()));
		             road.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(4,i+1).getContents()));
		             roadService.insertRoad(road);
		             result+=","+road.getRoad_id();
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
	 * describe:批量导出道路对象
	 * 2017年11月10日下午2:01:02 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导出道路对象  code:020009",produces ="application/json")
	@RequestMapping(value="batchExportList",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "道路ID集合,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> batchExportList(HttpServletRequest request,HttpServletResponse response,String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Road> list=new ArrayList<Road>();
		if(StringUtils.isEmpty(ids)){
			 list=roadService.findRoadList(new Road());
		 }else{
			 list=roadService.batchfindListObj(ids);
		 }
		try {
            // 创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("表名");
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            String colNames="道路编码,道路名称,区域id,状态,创建时间";
            String[] colNameArray = colNames.split(",");
            HSSFRow rowHeader = sheet.createRow(0);
            for (int i = 0; i < colNameArray.length; i++) {
                rowHeader.createCell(i).setCellValue(colNameArray[i]);
            }
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);//从第二行开始创建
                Road road = list.get(i);
                row.createCell(0).setCellValue(String.valueOf(road.getRoad_code()));
                row.createCell(1).setCellValue(String.valueOf(road.getRoad_name()));
                row.createCell(2).setCellValue(String.valueOf(road.getDistrict_id()));
                row.createCell(3).setCellValue(String.valueOf(road.getStatus()));
                row.createCell(4).setCellValue(String.valueOf(road.getCreate_date()));
            }

            // 输出Excel文件
            OutputStream output;
            output = response.getOutputStream();
            response.reset();
            String exportName="道路信息列表";
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
