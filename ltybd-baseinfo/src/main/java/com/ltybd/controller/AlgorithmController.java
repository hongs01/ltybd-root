package com.ltybd.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Algorithm;
import com.ltybd.service.AlgorithmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


/**
 * AlgorithmController.java
 *
 * describe:
 * 
 * 2017年11月7日 上午9:06:01 created By xuxf version 0.1
 *
 * 2017年11月7日 上午9:06:01 modifyed By xuxf version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/Algorithm", description = "车辆司乘轮趟算法")
@RestController
@RequestMapping("/Algorithm/")
@Validated
public class AlgorithmController {
	@Autowired
	private AlgorithmService algorithmService; 
	
	/**
	 * @param algorithm
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询车辆司乘轮趟算法集合
	 * 2017年11月6日下午4:13:08 by xuxf version 0.1
	 */
	@ApiOperation(value = "查询车辆司乘轮趟算法集合 code:018001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "algorithm_id", dataType = "int", required = false, value = "车辆司乘轮趟算法编号,非必填"),
		@ApiImplicitParam(paramType = "query", name = "algorithm_name", dataType = "String", required = false, value = "车辆司乘轮趟算法名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody
	public Map<String, Object> findListObj(
			Algorithm algorithm,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage){
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(algorithm.getStatus())) algorithm.setStatus(0);
		List<Algorithm> list=new ArrayList<Algorithm>();
		if(isPage){
			if(StringUtils.isEmpty(pageNum) || pageNum<0){
				map.put("result", "1");
				map.put("resultMsg", "请求失败,isPage为true时,参数pageNum必须为大于等于0的正整数!");
				map.put("resPonse", "查询失败");
				return map;
			}
			if(StringUtils.isEmpty(pageSize) || pageSize<0){
				map.put("result", "1");
				map.put("resultMsg", "请求失败,isPage为true时,参数pageSize必须为大于等于0的正整数!");
				map.put("resPonse", "查询失败");
				return map;
			}
			if(pageNum==0) pageNum=1;//初始页码
			if(pageSize==0) pageSize=15;//初始每页条数
			Page<Algorithm> page=PageHelper.startPage(pageNum, pageSize);//分页
			list=algorithmService.findListObj(algorithm);
			Map<String, Object> pageMap=new HashMap<String,Object>();
			pageMap.put("pageNum", page.getPageNum());//页码
			pageMap.put("pageSize", page.getPageSize());//每页条数
			pageMap.put("pagetotal", page.getPages());//总页数
			pageMap.put("total", page.getTotal());//总条数
			mapData.put("page", pageMap);
		}else{
			list=algorithmService.findListObj(algorithm);
		}
		mapData.put("algorithmList", list);
		map.put("resPonse", mapData);
		return map;
	}
	/**
	 * @param algorithm_id
	 * @return
	 * Map<String,Object>
	 * describe:查询车辆司乘轮趟算法对象
	 * 2017年11月6日下午4:13:29 by xuxf version 0.1
	 */
	@ApiOperation(value = "查询车辆司乘轮趟算法对象   code:018002", produces ="application/json")
	@RequestMapping(value="entity",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "algorithm_id", dataType = "int", required = false, value = "车辆司乘轮趟算法ID,必填,大于1000正整数")})
	public Map<String, Object> findByAlgorithmId(
			@NotNull(message="algorithm_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数") 
			@RequestParam Integer algorithm_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Algorithm algorithm=new Algorithm();
		algorithm.setAlgorithm_id(algorithm_id);
		map.put("resPonse",algorithmService.findByAlgorithmId(algorithm)!=null?algorithmService.findByAlgorithmId(algorithm):"没有查找到对应数据!" );
		return map;
	}
	
	/**
	 * @param algorithm
	 * @param result
	 * @return
	 * Map<String,Object>
	 * describe:更新车辆司乘轮趟算法对象 
	 * 2017年11月6日下午4:14:58 by xuxf version 0.1
	 */
	@ApiOperation(value = "更新车辆司乘轮趟算法对象   code:018003",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateAlgorithmObj(@RequestBody @Validated Algorithm algorithm,BindingResult result)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	Integer algorithm_id=0;
    		if(StringUtils.isEmpty(algorithm.getAlgorithm_id())){
    			if(StringUtils.isEmpty(algorithm.getAlgorithm_name())){
    				map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "车辆司乘轮趟算法名称Algorithm_name不能为空!");
    				return map;
    			}else{
        			algorithm.setStatus(0);
        			algorithm.setCreate_date(new Date());
        			algorithmService.insert(algorithm);
        			map.put("resPonse", "添加成功");
    			}
    		}else{
    			if(algorithmService.findByAlgorithmId(algorithm)!=null){
    				algorithm_id=(algorithmService.findByAlgorithmId(algorithm)).getAlgorithm_id();
    				algorithmService.update(algorithm);
    				map.put("resPonse", "修改成功");
    			}else{
    				map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "没有查到此车辆司乘轮趟对象无法更新");
    				return map;
    			}
    		}
    		map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		
        }
		return map;
	}
	
	/**
	 * @param algorithm_id
	 * @return
	 * Map<String,Object>
	 * describe:删除车辆司乘轮趟算法对象 
	 * 2017年11月6日下午4:13:43 by xuxf version 0.1
	 */
	@ApiOperation(value = "删除车辆司乘轮趟算法对象  code:018004",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "algorithm_id", dataType = "int", required = false, value = "车辆司乘轮趟算法ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> deleteAlgorithmObj(
			@Min(value=1000,message="请输入大于1000的正整数") 
			Integer algorithm_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Algorithm algorithm=new Algorithm();
		algorithm.setAlgorithm_id(algorithm_id);
		if(algorithmService.findByAlgorithmId(algorithm)==null){
			map.put("result", "1");
			map.put("resultMsg", "删除的车辆司乘轮趟对象不存在!");
			map.put("resPonse", "删除失败!");
		}else{
			int result = algorithmService.delete(algorithm);
			if(result > 0){
				map.put("resPonse","删除成功!");
			}else{
				map.put("resPonse","删除失败!");
			}
		}
		return map;
	}
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量删除车辆司乘轮趟算法对象
	 * 2017年11月6日下午4:13:55 by xuxf version 0.1
	 */
	@ApiOperation(value = "批量删除车辆司乘轮趟算法对象  code:018005",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "车辆司乘轮趟算法ID集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteItems(@NotNull(message="ids不能为空") String ids)
	{
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 int result=algorithmService.deleteItems(ids);
		 map.put("resultMsg", "失败的条数为"+(ids.split(",").length-result)+"条,成功条数为"+result+"条");
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("result", "1");
				map.put("resPonse", "删除失败!");
			}
		 return map;
	}
	
	/**
	 * @param ids
	 * @return
	 * Map<String,Object>
	 * describe:批量导出车辆司乘轮趟算法对象 
	 * 2017年11月7日上午9:06:13 by xuxf version 0.1
	 */
	@ApiOperation(value = "批量导出车辆司乘轮趟算法对象  code:018006",produces ="application/json")
	@RequestMapping(value="batchExportItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "车辆司乘轮趟算法ID集合,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> batchExportItems(String ids){
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Algorithm> list=new ArrayList<Algorithm>();
//		if(StringUtils.isEmpty(ids)){
//			 list=algorithmService.findListObj(new Algorithm());
//		 }else{
//			 list=algorithmService.batchfindListObj(ids);
//		 }
//		try {
//			String exportName="轮班轮趟列表";
//	        String str = new String(exportName.getBytes("GBK"),"iso8859-1");
//	        response.setContentType("application/vnd.ms-excel;charset=gb2312"); // 下载文版类型
//	        response.setHeader("Content-disposition","attachment; filename=" + str + ".xls");
//	        response.setCharacterEncoding("gb2312");
//            // 创建HSSFWorkbook对象(excel的文档对象)
//            HSSFWorkbook wb = new HSSFWorkbook();
//            // 建立新的sheet对象（excel的表单）
//            HSSFSheet sheet = wb.createSheet("表 名");
//            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
//            String colNames="名称,简介,周期,方向,类型,状态,创建时间,备注";
//            String[] colNameArray = colNames.split(",");
//            HSSFRow rowHeader = sheet.createRow(0);
//            for (int i = 0; i < colNameArray.length; i++) {
//                rowHeader.createCell(i).setCellValue(colNameArray[i]);
//            }
//            for (int i = 0; i < list.size(); i++) {
//                HSSFRow row = sheet.createRow(i + 1);//从第二行开始创建
//                Algorithm algorithm = list.get(i);
//                row.createCell(0).setCellValue(String.valueOf(algorithm.getAlgorithm_name()));
//                row.createCell(1).setCellValue(String.valueOf(algorithm.getAlgorithm_not()));
//                row.createCell(2).setCellValue(String.valueOf(algorithm.getCycle()));
//                row.createCell(3).setCellValue(String.valueOf(algorithm.getDriection()));
//                row.createCell(4).setCellValue(String.valueOf(algorithm.getType()));
//                row.createCell(5).setCellValue(String.valueOf(algorithm.getStatus()));
//                row.createCell(6).setCellValue(String.valueOf(algorithm.getCreate_date()));
//                row.createCell(7).setCellValue(String.valueOf(algorithm.getRemark()));
//            }
//
//            // 输出Excel文件
//            OutputStream output;
//            output = response.getOutputStream();
//            wb.write(output);
//            output.close();
//        } catch(Exception e) {
//        	map.put("result", "0");
//     		map.put("resultMsg", "请求异常!");
//     		map.put("resPonse","导出失败");
//     		e.printStackTrace();
//        }  
//		return map;
		 Map<String, Object> map=new HashMap<String,Object>();
		 List<Algorithm> list=new ArrayList<Algorithm>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 if(StringUtils.isEmpty(ids)){
			 list=algorithmService.findListObj(new Algorithm());
		 }else{
			 list=algorithmService.batchfindListObj(ids);
		 }
		 WritableWorkbook bWorkbook = null; 
	        try {  
//	        	   OutputStream output;
//		            output = response.getOutputStream();
	            // 创建Excel对象  
	            bWorkbook = Workbook.createWorkbook(new File("D:/book.xls")); 
	            // 通过Excel对象创建一个选项卡对象  
	            WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);
	            //使用循环将数据读出  
	            Label label11=new Label(0,0,"名称");  
                Label label12=new Label(1,0,"简介");
                Label label13=new Label(2,0,"创建时间");
                Label label14=new Label(3,0,"周期");
                Label label15=new Label(4,0,"方向");
                Label label16=new Label(5,0,"备注");
                Label label17=new Label(6,0,"状态");
                Label label18=new Label(7,0,"类型");
                sheet.addCell(label11);  
                sheet.addCell(label12);
                sheet.addCell(label13); 
                sheet.addCell(label14); 
                sheet.addCell(label15); 
                sheet.addCell(label16); 
                sheet.addCell(label17); 
                sheet.addCell(label18);  
	            for (int i = 0; i < list.size(); i++) {  
	            	Algorithm algorithm=list.get(i);
	            	
	            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	String satestr=sdf.format(algorithm.getCreate_date());
	                Label label1=new Label(0,i+1,String.valueOf(algorithm.getAlgorithm_name()));  
	                Label label2=new Label(1,i+1,String.valueOf(algorithm.getAlgorithm_not()));
	                Label label3=new Label(2,i+1,satestr);
	                Label label4=new Label(3,i+1,String.valueOf(algorithm.getCycle()));
	                Label label5=new Label(4,i+1,String.valueOf(algorithm.getDriection()));
	                Label label6=new Label(5,i+1,String.valueOf(algorithm.getRemark()));
	                Label label7=new Label(6,i+1,String.valueOf(algorithm.getStatus()));
	                Label label8=new Label(7,i+1,String.valueOf(algorithm.getType()));
	              
	                sheet.addCell(label1);  
	                sheet.addCell(label2);
	                sheet.addCell(label3); 
	                sheet.addCell(label4); 
	                sheet.addCell(label5); 
	                sheet.addCell(label6); 
	                sheet.addCell(label7); 
	                sheet.addCell(label8);
	             
	            } 
	         
//	            String exportName="轮班轮趟列表";
//	            String str = new String(exportName.getBytes("gbk"),"ISO-8859-1");
//	            response.setContentType("application/vnd.ms-excel;charset=UTF-8"); // 下载文版类型
//	            response.setContentType("application/x-download");
//	            response.setHeader("Content-disposition", "attachment; filename=" + str + ".xls");
//	            response.setCharacterEncoding("UTF-8");
//	            // 创建一个单元格对象，第一个为列，第二个为行，第三个为值  
	            Label label = new Label(0, 2, "test");  
	            // 将创建好的单元格放入选项卡中  
	            //sheet.addCell(label);  
	            // 写如目标路径  
	            bWorkbook.write();  
	  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                bWorkbook.close();  
	            } catch (WriteException | IOException e) {   
	                e.printStackTrace(); 
	            	map.put("result", "0");
	         		map.put("resultMsg", "请求异常!");
	         		map.put("resPonse","导出失败");
	            }  
	        }  
		 
		 return map;
		
	}
	
	 
	
	/**
	 * @return
	 * Map<String,Object>
	 * describe:批量导入车辆司乘轮趟算法对象 
	 * 2017年11月7日下午1:36:05 by xuxf version 0.1
	 */
	@ApiOperation(value = "批量导入车辆司乘轮趟算法对象  code:014007", produces = "application/json")
	@RequestMapping(value = "batchImportList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> batchImportList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");  
	        Workbook bWorkbook=null;  
	        String result="";
	        try {  
	            bWorkbook=Workbook.getWorkbook(new File("D:/book.xls"));  
	            Sheet sheet=bWorkbook.getSheet(0);  
	            for (int i = 0; i < sheet.getRows()-1; i++) {  
	            	Algorithm algorithm=new Algorithm();  
	                //获取单元格对象  
	                Cell cell =sheet.getCell(0,i);  
	                //获取单元格的值  
	                algorithm.setAlgorithm_id(algorithmService.getSequence("algorithm_code"));  
	                algorithm.setAlgorithm_name(sheet.getCell(0,i+1).getContents());  
	                algorithm.setAlgorithm_not(sheet.getCell(1, i+1).getContents());
						algorithm.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sheet.getCell(2, i+1).getContents()));
	                algorithm.setCycle(Integer.parseInt(sheet.getCell(3, i+1).getContents()));
	                algorithm.setDriection(sheet.getCell(4, i+1).getContents());
	                algorithm.setRemark(sheet.getCell(5, i+1).getContents());
	                algorithm.setStatus(Integer.parseInt(sheet.getCell(6, i+1).getContents()));
	                algorithm.setType(Integer.parseInt(sheet.getCell(7, i+1).getContents()));
	                algorithmService.insert(algorithm);
	                result+=algorithm.getAlgorithm_id()+",";
	            }    
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	         finally {  
	            bWorkbook.close();  
	        }  
	        map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", result.substring(0, result.length()-1));
		return map;
	}
}
