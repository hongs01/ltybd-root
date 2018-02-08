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
import com.ltybd.entity.Line;
import com.ltybd.entity.Stationblock;
import com.ltybd.entity.StationblockBean;
import com.ltybd.service.StationblockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * StationblockController.java
 *
 * describe:站台信息
 * 
 * 2017年10月13日 上午11:01:11 created By Chenjw version 0.1
 *
 * 2017年10月13日 上午11:01:11 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value="/Stationblock", description = "站台信息")
@RestController
@RequestMapping("/Stationblock/")
@Validated
public class StationblockController {
	@Autowired
	private StationblockService StationblockService;
	
	/**
	 * @param Stationblock
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询站台信息集合
	 * 2017年10月12日上午10:10:19 by Chejw version 0.1
	 */
	@ApiOperation(value = "查询站台信息集合 code:003001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "bus_station_code", dataType = "int", required = false, value = "站台ID,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "bus_station_name", dataType = "int", required = false, value = "站台名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填"), })
	@ResponseBody
	public Map<String, Object> findStationblockList(Stationblock Stationblock,Integer pageNum,Integer pageSize,Boolean isPage)
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
			if(StringUtils.isEmpty(Stationblock.getStatus())) Stationblock.setStatus(0);
			List<StationblockBean> list=new ArrayList<StationblockBean>();
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
				Page<Stationblock> page=PageHelper.startPage(pageNum, pageSize);//分页
				list=StationblockService.findStationblockList(Stationblock);
				Map<String, Object> pageMap=new HashMap<String,Object>();
				pageMap.put("pageNum", page.getPageNum());//页码
				pageMap.put("pageSize", page.getPageSize());//每页条数
				pageMap.put("pagetotal", page.getPages());//总页数
				pageMap.put("total", page.getTotal());//总条数
				mapData.put("page", pageMap);
			}else{
				list=StationblockService.findStationblockList(Stationblock);
			}
			mapData.put("StationblockList", list);
		}
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * 
	 * @param bus_station_code
	 * @return
	 * Map<String, Object>
	 * describe:查询站台信息对象
	 * 2017年10月12日上午10:10:29 by Chejw version 0.1
	 * @throws Exception 
	 */
	@ApiOperation(value = "查询站台信息对象 code:003002", produces ="application/json")
	@RequestMapping(value="stationblockEntity",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "bus_station_code", dataType = "int", required = false, value = "站台编码,必填"), })
	@ResponseBody
	public Map<String, Object> findByBusStationCode(
			@NotNull(message="bus_station_code不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数") 
			Integer bus_station_code) throws Exception
	{
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(bus_station_code)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数bus_station_code为必填项!");
			map.put("resPonse", "查询失败");
			return map;
		}
		Stationblock Stationblock=new Stationblock();
		Stationblock.setBus_station_code(bus_station_code);
		map.put("resPonse",StationblockService.findByBusStationCode(Stationblock)!=null?StationblockService.findByBusStationCode(Stationblock):"没有查找到对应数据!" );
		return map;
	}
	
	@ApiOperation(value = "查询经过站台的线路 code:003003", produces ="application/json")
	@RequestMapping(value="lineEntity",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "bus_station_code", dataType = "int", required = false, value = "站台编码,必填"), })
	@ResponseBody
	public Map<String, Object> findLinesByBusStationCode(
			@NotNull(message="bus_station_code不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数") 
			Integer bus_station_code) throws Exception
	{
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Stationblock stationblock =new Stationblock();
		stationblock.setBus_station_code(bus_station_code);
		List<Line> lineList=StationblockService.findLinesByBusStationCode(stationblock);
		if(lineList.isEmpty()){
			map.put("result", "1");
			map.put("resultMsg", "没有经过该站台的线路!");
			map.put("resPonse", "查询失败!");
		}else{
			map.put("resPonse",lineList);
		}
		return map;	
	  }
	
	/**
	 * @param Stationblock
	 * @return
	 * Map<String, Object>
	 * describe:更新站台信息对象
	 * 2017年10月12日上午10:10:29 by LinSir version 0.1
	 */
	@ApiOperation(value = "更新站台信息对象 code:003004",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateStationblock(@RequestBody @Validated Stationblock Stationblock,BindingResult error){
		Map<String, Object> map=new HashMap<String,Object>();
		Integer bus_station_code=0;
		if (error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
        }
		if(StringUtils.isEmpty(Stationblock.getBus_station_code())){
			if(StringUtils.isEmpty(Stationblock.getBus_station_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "站台名称Bus_station_name不能为空!");
				return map;
			}
		 }else{
			if(StationblockService.findByBusStationCode(Stationblock)!=null){
			bus_station_code=Stationblock.getBus_station_code();
			Stationblock.setLast_modified_time(new Date());
			StationblockService.updateStationblock(Stationblock);
			}else{
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "没有查到此站台对象无法更新");
				return map;
			}
		}
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", bus_station_code);
		return map;
	}
	
	
	
	/**
	 * @param Stationblock
	 * @param error
	 * @return
	 * Map<String,Object>
	 * describe:
	 * 2017年11月9日下午3:14:53 by chenq version 0.1
	 */
	@ApiOperation(value = "增加站台信息对象 code:003005",produces ="application/json")
	@RequestMapping(value="insert",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> insertStationblock(@RequestBody @Validated Stationblock Stationblock,BindingResult error){
		Map<String, Object> map=new HashMap<String,Object>();
		Integer bus_station_code=0;
		if (error.hasErrors()){
            List<ObjectError> errorList = error.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	if(StringUtils.isEmpty(Stationblock.getBus_station_name())){
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "站台名称Bus_station_name不能为空!");
				return map;
			}else{
				bus_station_code=StationblockService.getSequence("Stationblock_code");
				Stationblock.setBus_station_code(bus_station_code);
				Stationblock.setStatus(0);
				Stationblock.setLast_modified_time(new Date());
				StationblockService.insertStationblock(Stationblock);
			}
			map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", bus_station_code);
		}
		return map;
	}
	
	
	/**
	 * @param bus_station_code
	 * @return
	 * Map<String, Object>
	 * describe:删除站台信息对象
	 * 2017年10月12日上午10:10:32 by Chejw version 0.1
	 */
	@ApiOperation(value = "删除站台信息对象 code:003006",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "bus_station_code", dataType = "int", required = false, value = "站台编码,必填"), })
	@ResponseBody
	public Map<String, Object> deleteStationblock(
			@NotNull(message="bus_station_code不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数") 
			Integer bus_station_code){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(bus_station_code)){
			map.put("result", "1");
			map.put("resultMsg", "请求失败,参数bus_station_code为必填项!");
			map.put("resPonse", "删除失败!");
			return map;
		}
		Stationblock Stationblock=new Stationblock();
		Stationblock.setBus_station_code(bus_station_code);
		if(null==StationblockService.findByBusStationCode(Stationblock)){
			map.put("result", "1");
			map.put("resultMsg", "删除的站台对象不存在!");
			map.put("resPonse", "删除失败!");
		}else{
		   int result=StationblockService.delectStationblock(Stationblock);
		   if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse", "删除失败!");
			}
		}
		return map;
	}
	
	/**
	 * @param ids
	 * Map<String, Object>
	 * describe:批量删除站台信息对象
	 * 2017年10月12日上午10:10:35 by Chejw version 0.1
	 */
	@ApiOperation(value = "批量删除站台信息对象 code:003007",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "站台ID集合,必填,以(,)隔开,例:1,2,3"), })
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
			 Stationblock Stationblock=new Stationblock();
			 Stationblock.setBus_station_code(Integer.parseInt(idss[i]));
			 if(StationblockService.findByBusStationCode(Stationblock)==null){
					count++;
					str+=Stationblock.getBus_station_code()+",";
				}
			 if (str.length()!=0){
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的id为:"+str.substring(0,str.lastIndexOf(","))); 
			 }else{
				 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条");
			 }
		}
		 int result=StationblockService.deleteItems(ids);
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse", "删除失败!");
			}
		 return map;
	}
	
	/***
	 * 
	 * @param list
	 * @return Map<String,Object>
	 * describe:批量更新站台信息对象 code:030003006
	 * 2017年10月16日上午10:59:32 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新站台信息对象 code:003008", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Stationblock> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,请传参数");
			map.put("resPonse", "更新失败");
			return map;
		}
		int result = StationblockService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;
	}
	
	
	/**
	 * @return
	 * Map<String,Object>
	 * describe:批量导入站台信息对象
	 * 2017年11月9日下午3:15:28 by chenq version 0.1
	 */
	@ApiOperation(value = "批量导入站台信息对象  code:003009", produces = "application/json")
	@RequestMapping(value = "batchImportList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> batchImportList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!"); 
		Workbook bWorkbook=null;  
	    String result="";
	    try{
	    	 bWorkbook=Workbook.getWorkbook(new File("D:/book.xls"));  
	         Sheet sheet=bWorkbook.getSheet(0);
	         for(int i=0;i<sheet.getRows()-1;i++){
	        	 Stationblock stationblock=new Stationblock();
	        	//获取单元格对象  
	             Cell cell =sheet.getCell(0,i);
	           //获取单元格的值  
	             stationblock.setBus_station_code(StationblockService.getSequence("stationblock_code"));
	             stationblock.setBus_station_name(sheet.getCell(0,i+1).getContents());
	             stationblock.setStation_type(sheet.getCell(1,i+1).getContents());
	             stationblock.setStatus(Integer.parseInt(sheet.getCell(2, i+1).getContents()));
	             stationblock.setDistrict_id(Integer.parseInt(sheet.getCell(3,i+1).getContents()));
	             stationblock.setOrientation(sheet.getCell(4,i+1).getContents());
	             stationblock.setEnter_azimuth(Integer.parseInt(sheet.getCell(5,i+1).getContents()));
	             stationblock.setLeave_azimuth(Integer.parseInt(sheet.getCell(6,i+1).getContents()));
	             stationblock.setLongitude(Double.valueOf(sheet.getCell(7,i+1).getContents()));
	             stationblock.setLatiude(Double.valueOf(sheet.getCell(8,i+1).getContents()));
	             stationblock.setEnter_longitude(Double.valueOf(sheet.getCell(9,i+1).getContents()));
	             stationblock.setEnter_latitude(Double.valueOf(sheet.getCell(10,i+1).getContents()));
	             stationblock.setLeave_longitude(Double.valueOf(sheet.getCell(11,i+1).getContents()));
	             stationblock.setLeave_latitude(Double.valueOf(sheet.getCell(12,i+1).getContents()));
	             stationblock.setRemark(sheet.getCell(13,i+1).getContents());
	             stationblock.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(14,i+1).getContents()));
	             stationblock.setLast_modified_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(15,i+1).getContents()));
	             stationblock.setStation_type(sheet.getCell(16,i+1).getContents());
	             stationblock.setRoad_id(Integer.parseInt(sheet.getCell(17,i+1).getContents()));
	             stationblock.setAddress(sheet.getCell(18,i+1).getContents());
	             stationblock.setNearby(sheet.getCell(19,i+1).getContents());
	             stationblock.setElectronic_bus_board_number(sheet.getCell(20,i+1).getContents());
	             StationblockService.insertStationblock(stationblock);
	             result+=","+stationblock.getBus_station_code();
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
	
	@ApiOperation(value = "批量导出站台对象  code:003010",produces ="application/json")
	@RequestMapping(value="batchExportList",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "站台编码集合,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> batchExportList(HttpServletRequest request,HttpServletResponse response,String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		List<StationblockBean> list=new ArrayList<StationblockBean>();
		if(StringUtils.isEmpty(ids)){
			 list=StationblockService.findStationblockList(new Stationblock());
		 }else{
			 list=StationblockService.batchfindListObj(ids);
		 }
		try {
            // 创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("表名");
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            String colNames="站台名称,站台类型,状态,区域ID,方位,进站方位角,出站方位角,站台经度,站台纬度,进站经度,进站纬度,出站经度,出站纬度,备注, "
            		+ " 创建时间,上次修改时间,站台状况,所属道路,地址,附近地点名称,电子站牌编号";
            String[] colNameArray = colNames.split(",");
            HSSFRow rowHeader = sheet.createRow(0);
            for (int i = 0; i < colNameArray.length; i++) {
                rowHeader.createCell(i).setCellValue(colNameArray[i]);
            }
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);//从第二行开始创建
                StationblockBean stationblock = list.get(i);
                row.createCell(0).setCellValue(String.valueOf(stationblock.getBus_station_name()));
                row.createCell(1).setCellValue(String.valueOf(stationblock.getStation_type()));
                row.createCell(2).setCellValue(String.valueOf(stationblock.getStatus()));
                row.createCell(3).setCellValue(String.valueOf(stationblock.getDistrict_id()));
                row.createCell(4).setCellValue(String.valueOf(stationblock.getOrientation()));
                row.createCell(5).setCellValue(String.valueOf(stationblock.getEnter_azimuth()));
                row.createCell(6).setCellValue(String.valueOf(stationblock.getLeave_azimuth()));
                row.createCell(7).setCellValue(String.valueOf(stationblock.getLongitude()));
                row.createCell(8).setCellValue(String.valueOf(stationblock.getLatiude()));
                row.createCell(9).setCellValue(String.valueOf(stationblock.getEnter_longitude()));
                row.createCell(10).setCellValue(String.valueOf(stationblock.getEnter_latitude()));
                row.createCell(11).setCellValue(String.valueOf(stationblock.getLeave_longitude()));
                row.createCell(12).setCellValue(String.valueOf(stationblock.getLeave_latitude()));
                row.createCell(13).setCellValue(String.valueOf(stationblock.getRemark()));
                row.createCell(14).setCellValue(String.valueOf(stationblock.getCreate_time()));
                row.createCell(15).setCellValue(String.valueOf(stationblock.getLast_modified_time()));
                row.createCell(16).setCellValue(String.valueOf(stationblock.getStation_condition()));
                row.createCell(17).setCellValue(String.valueOf(stationblock.getRoad_id()));
                row.createCell(18).setCellValue(String.valueOf(stationblock.getAddress()));
                row.createCell(19).setCellValue(String.valueOf(stationblock.getNearby()));
                row.createCell(20).setCellValue(String.valueOf(stationblock.getElectronic_bus_board_number()));
            }
            // 输出Excel文件
            OutputStream output;
            output = response.getOutputStream();
            response.reset();
            String exportName="站台信息列表";
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
