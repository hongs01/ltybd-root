package com.ltybd.controller;

import java.io.InputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.GroupCompany;
import com.ltybd.entity.Line;
import com.ltybd.entity.RescueConvoys;
import com.ltybd.entity.Yard;
import com.ltybd.service.RescueConvoysService;
import com.ltybd.service.YardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Api(value = "/RescueConvoys", description = "施救车队信息")
@RestController
@RequestMapping("/RescueConvoys/")
@Validated
public class RescueConvoysController {

		@Autowired
		private RescueConvoysService rescueConvoysService;
		
		
		/**
		 * @param RescueConvoys
		 * @param pageNum
		 * @param pageSize
		 * @param isPage
		 * @return
		 * Map<String,Object>
		 * describe:模糊查询施救车队信息集合
		 * 2017年11月7日 by wugj version 0.1
		 */
		@ApiOperation(value = "查询施救车队列表 code:023001",produces ="application/json")
		@RequestMapping(value="findRescueListObj",method= {RequestMethod.POST})
		@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
		@ResponseBody  
		public Map<String, Object> findRescueListObj(RescueConvoys rescueConvoys,Integer pageNum,Integer pageSize,
				@NotNull(message="isPage不能为空") Boolean isPage)
		{
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
				if (null == rescueConvoys) {
					rescueConvoys = new RescueConvoys();
				}
				if (null == rescueConvoys.getStatus()) {
					rescueConvoys.setStatus(0);
				}
				List<RescueConvoys> list = new ArrayList<RescueConvoys>();
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
					Page<RescueConvoys> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
					list = rescueConvoysService.findRescueListObj(rescueConvoys);
					Map<String, Object> pageMap = new HashMap<String, Object>();
					pageMap.put("pageNum", page.getPageNum());// 页码
					pageMap.put("pageSize", page.getPageSize());// 每页条数
					pageMap.put("pagetotal", page.getPages());// 总页数
					pageMap.put("total", page.getTotal());// 总条数
					mapData.put("page", pageMap);
				} else {
					list = rescueConvoysService.findRescueListObj(rescueConvoys);
				}
				mapData.put("list", list);
			}
			map.put("resPonse", mapData);
			return map;
		}
		
		/**
		 * 
		 * @param RescueConvoys
		 * @return
		 * Map<String, Object>
		 * describe:更新施救车队信息对象
		 * 2017年11月8日  by wugj version 0.1
		 * @throws Exception 
		 */
		@ApiOperation(value = "更新施救车队信息对象 code:023002",produces ="application/json")
		@RequestMapping(value="updateRescue",method= {RequestMethod.POST})
		@ResponseBody
		public Map<String, Object> updateRescue(@RequestBody @Validated RescueConvoys rescueConvoys,BindingResult result)
		{
			Map<String, Object> map=new HashMap<String,Object>();
			if (result.hasErrors()){
	            List<ObjectError> errorList = result.getAllErrors();
	            map.put("result", "1");
	    		map.put("resultMsg", "请求失败!");
	    		map.put("resPonse", errorList);
	        }else{
				if(StringUtils.isEmpty(rescueConvoys.getCompany_id())){
					map.put("result", "1");
					map.put("resultMsg", "请求失败!");
					map.put("resPonse", "公司Conmpany_id不能为空!");
					return map;
				}
				rescueConvoysService.updateRescue(rescueConvoys);
	    		map.put("result", "0");
	    		map.put("resultMsg", "请求成功!");
	    		map.put("resPonse", rescueConvoys.getRescue_id());
	        }
			return map;
		}
		
		/**
		 * @param RescueConvoys_id
		 * @return
		 * Map<String, Object>
		 * describe:根据ID删除施救车队信息对象
		 * 2017年11月8日  by wgj version 0.1
		 */
		@ApiOperation(value = "根据ID删除施救车队信息对象 code:023003",produces ="application/json")
		@RequestMapping(value="deleteRescue",method= {RequestMethod.POST})
		@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "rescue_id", dataType = "int", required = false, value = "施救车队ID,必填,大于1000正整数"), })
		@ResponseBody
		public Map<String, Object> deleteRescue(
				@Min(value=1000,message="请输入大于1000的正整数") 
				Integer rescue_id){
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("result", "0");
			map.put("resultMsg", "请求成功!");
			if(rescueConvoysService.findRescueById(rescue_id)==null){
				map.put("result", "1");
				map.put("resultMsg", "删除的施救车队对象不存在!");
				map.put("resPonse", "删除失败!");
			}else{
				int result = rescueConvoysService.deleteRescue(rescue_id);
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
		 * Map<String, Object>
		 * describe:批量删除施救车队信息对象列表
		 * 2017年11月8日  by wugj version 0.1
		 */
		@ApiOperation(value = "删除施救车队信息列表列表 code:023004",produces ="application/json")
		@RequestMapping(value="deleteRescueList",method= {RequestMethod.POST})
		@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "rescue_ids", dataType = "string", required = false, value = "施救车队id列表,必填,以逗号分隔"), })
		@ResponseBody
		public Map<String, Object> deleteRescueList(String rescue_ids){
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("result", "0");
			map.put("resultMsg", "请求成功!");
			if(StringUtils.isEmpty(rescue_ids)){
				map.put("result", "1");
				map.put("resultMsg", "请至少选择一条数据!");
				map.put("resPonse", "删除失败!");
			}else{
				int result = rescueConvoysService.deleteRescueList(rescue_ids);
				if(result > 0){
					map.put("resPonse","删除成功!");
				}else{
					map.put("resPonse","删除失败!");
				}
			}
			return map;
		}
		
		
		/**
		 * 
		 * @param RescueConvoys_id
		 * @return
		 * Map<String, Object>
		 * describe:根据ID查询施救车队信息对象
		 * 2017年11月8日  by wugj version 0.1
		 * @throws Exception 
		 */
		@ApiOperation(value = "根据ID查询施救车队信息对象 code:023005", produces ="application/json")
		@RequestMapping(value="findRescueById",method= {RequestMethod.POST})
		@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "rescue_id", dataType = "int", required = false, value = "施救车队ID,必填,大于1000正整数")})
		public Map<String, Object> findRescueById(
				@NotNull(message="rescue_id不能为空") 
				@Min(value=1000,message="必须为大于或者等于1000的正整数") 
				@RequestParam Integer rescue_id){
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("result", "0");
			map.put("resultMsg", "请求成功!");
			map.put("resPonse",rescueConvoysService.findRescueById(rescue_id)!=null?rescueConvoysService.findRescueById(rescue_id):"没有查找到对应数据!" );
			return map;
		}
		
		
		/**
		 * 
		 * @param RescueConvoys_id
		 * @return
		 * Map<String, Object>
		 * describe:新增施救车队信息
		 * 2017年11月8日  by wugj version 0.1
		 * @throws Exception 
		 */
		@ApiOperation(value = "新增施救车队信息 code:023006", produces = "application/json")
		@RequestMapping(value = "addRescue", method = { RequestMethod.POST})
		@ResponseBody
		public Map<String, Object> addRescue(@RequestBody @Validated RescueConvoys rescue,BindingResult result) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (result.hasErrors()){
				List<ObjectError> errorList = result.getAllErrors();
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", errorList);
				return map;
			}
			rescueConvoysService.addRescue(rescue);
			map.put("result", "0");
			map.put("resultMsg", "请求成功!");
			map.put("resPonse","新增车场成功");
			return map;
		}
		
		/**
		 * 
		 * @param RescueConvoys_id
		 * @return
		 * Map<String, Object>
		 * describe:批量导入施救车队信息列表
		 * 2017年11月8日  by wugj version 0.1
		 * @throws Exception 
		 */
		/*@ApiOperation(value = "导出施救车队信息列表",produces ="application/json")
		@RequestMapping(value="importRescueList",method= {RequestMethod.POST})
		@ResponseBody
		public Map<String, Object> importRescueList(@RequestParam("filename") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("result", "0");
			map.put("resultMsg", "请求成功!");
				try{ 
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
					InputStream in =null;  
					List<RescueConvoys> list = new ArrayList<>();
					MultipartFile _file = multipartRequest.getFile("filename");  
					in = _file.getInputStream();  
					Workbook wb=Workbook.getWorkbook(in);  
		            Sheet sheet = wb.getSheet(0);  
		            for (int i = 1; i < sheet.getRows(); i++) {  
		            	RescueConvoys rescue=new RescueConvoys();  
		                //获取单元格的值  
		            	rescue.setRescue_id(rescueConvoysService.getSequence("rescue_code"));
		            	//rescue.setCompany_id();
		            	rescue.setResponsible_person();
		            	rescue.setResponsible_telephone();
		            	rescue.setService_date();
		            	rescue.setService_line();
		            	rescue.setStatus();
		            	rescue.setStop_address();
		            	rescue.setVehicle_no();
		                //String sDate = sheet.getCell(4,i).getContents().trim();
		                Date date = new Date();
		                if(!StringUtils.isEmpty(sDate)){
		                	date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
		                }
		                yard.setCreate_date(date);
		                list.add(yard);
		            }
					yardService.addYardList(list);
				}catch(Exception e){
					e.printStackTrace();
					map.put("result", "1");
					map.put("resultMsg", "请求失败!");
					map.put("resPonse", "导入异常");
				}
			return map;
		}*/
}
