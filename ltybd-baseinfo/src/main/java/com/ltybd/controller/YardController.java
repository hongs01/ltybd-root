package com.ltybd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Dispatch_Screen;
import com.ltybd.entity.Line;
import com.ltybd.entity.Yard;
import com.ltybd.service.YardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Sheet;
import jxl.Workbook;

/**
 * YardController.java
 *
 * describe:车场信息
 * 
 * 2017年10月17日 下午7:00:42 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:00:42 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/Yard", description = "车场信息")
@RestController
@RequestMapping("/Yard/")
@Validated
public class YardController {

	@Autowired
	private YardService yardService;

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:模糊查询车场信息集合 2017年11月7日 by wugj version
	 *         0.1
	 */
	@ApiOperation(value = "查询车场集合 code:024001", produces = "application/json")
	@RequestMapping(value = "findYardListObj", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填") })
	@ResponseBody
	public Map<String, Object> findYardListObj(Yard yard, Integer pageNum, Integer pageSize,
			@NotNull(message = "isPage不能为空") Boolean isPage) {
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
			if (null == yard) {
				yard = new Yard();
			}
			if (null == yard.getStatus()) {
				yard.setStatus(0);
			}
			List<Yard> list = new ArrayList<Yard>();
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
				Page<Yard> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = yardService.findYardList(yard);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = yardService.findYardList(yard);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * @param ids
	 *            Map<String, Object> describe:批量删除车场信息对象 2017年11月8日 by wugj
	 *            version 0.1
	 */
	@ApiOperation(value = "删除车场信息列表 code:024002", produces = "application/json")
	@RequestMapping(value = "deleteYardList", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "yard_ids", dataType = "string", required = false, value = "车场id列表,必填,以逗号分隔"), })
	@ResponseBody
	public Map<String, Object> deleteYardList(String yard_ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (StringUtils.isEmpty(yard_ids)) {
			map.put("result", "1");
			map.put("resultMsg", "请至少选择一条数据!");
			map.put("resPonse", "删除失败!");
		} else {
			int result = yardService.deleteYardList(yard_ids);
			if (result > 0) {
				map.put("resPonse", "删除成功!");
			} else {
				map.put("resPonse", "删除失败!");
			}
		}
		return map;
	}

	/**
	 * 
	 * @param yard
	 * @return Map<String, Object> describe:更新车场信息对象 2017年11月8日 by wugj version
	 *         0.1
	 * @throws Exception
	 */
	@ApiOperation(value = "更新车场信息对象 code:024003", produces = "application/json")
	@RequestMapping(value = "updateYard", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateYard(@RequestBody @Validated Yard yard, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse", errorList);
		} else {
			if (StringUtils.isEmpty(yard.getYard_name())) {
				map.put("result", "1");
				map.put("resultMsg", "请求失败!");
				map.put("resPonse", "车场名称Yard_name不能为空!");
				return map;
			}
			yardService.updateYard(yard);
			map.put("result", "0");
			map.put("resultMsg", "请求成功!");
			map.put("resPonse", yard.getYard_id());
		}
		return map;
	}

	/**
	 * 
	 * @param yard_id
	 * @return Map<String, Object> describe:根据ID查询车场信息对象 2017年11月8日 by wugj
	 *         version 0.1
	 * @throws Exception
	 */
	@ApiOperation(value = "查询车场信息对象 code:024004", produces = "application/json")
	@RequestMapping(value = "findYardById", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "yard_id", dataType = "int", required = false, value = "车场ID,必填,大于1000正整数") })
	public Map<String, Object> findYardById(
			@NotNull(message = "yard_id不能为空") @Min(value = 1000, message = "必须为大于或者等于1000的正整数") @RequestParam Integer yard_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse",
				yardService.findYardById(yard_id) != null ? yardService.findYardById(yard_id) : "没有查找到对应数据!");
		return map;
	}

	/**
	 * @param yard_id
	 * @return Map<String, Object> describe:根据ID删除车场信息对象 2017年11月8日 by wgj version
	 *         0.1
	 */
	@ApiOperation(value = "删除车场信息对象 code:024005", produces = "application/json")
	@RequestMapping(value = "deleteYard", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "yard_id", dataType = "int", required = false, value = "车场ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> deleteYard(@Min(value = 1000, message = "请输入大于1000的正整数") Integer yard_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (yardService.findYardById(yard_id) == null) {
			map.put("result", "1");
			map.put("resultMsg", "删除的车场对象不存在!");
			map.put("resPonse", "删除失败!");
		} else {
			int result = yardService.deleteYard(yard_id);
			if (result > 0) {
				map.put("resPonse", "删除成功!");
			} else {
				map.put("resPonse", "删除失败!");
			}
		}
		return map;
	}

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:模糊查询车场信息集合 2017年11月7日 by wugj version
	 *         0.1
	 */
	@ApiOperation(value = "通过车场ID查询调度屏信息集合 code:024006", produces = "application/json")
	@RequestMapping(value = "findScreenByYardId", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填") })
	@ResponseBody
	public Map<String, Object> findScreenByYardId(Yard yard, Integer pageNum, Integer pageSize,
			@NotNull(message = "isPage不能为空") Boolean isPage) {
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
			if (null == yard) {
				yard = new Yard();
			}
			if (null == yard.getStatus()) {
				yard.setStatus(0);
			}
			List<Dispatch_Screen> list = new ArrayList<Dispatch_Screen>();
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
				Page<Dispatch_Screen> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = yardService.findScreenByYardId(yard);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = yardService.findScreenByYardId(yard);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:新增车场信息 2017年11月7日 by wugj version 0.1
	 */
	@ApiOperation(value = "新增车场信息 code:024007", produces = "application/json")
	@RequestMapping(value = "addYard", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> addYard(@RequestBody @Validated Yard yard, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			map.put("result", "1");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse", errorList);
			return map;
		}
		yardService.addYard(yard);
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		map.put("resPonse", "新增车场成功");
		return map;
	}

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:通过车场id查询线路名称 2017年11月7日 by wugj
	 *         version 0.1
	 */
	@ApiOperation(value = "查线路集合 code:024008", produces = "application/json")
	@RequestMapping(value = "findLineById", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填") })
	@ResponseBody
	public Map<String, Object> findLineById(Yard yard, Integer pageNum, Integer pageSize,
			@NotNull(message = "isPage不能为空") Boolean isPage) {
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
			if (null == yard) {
				yard = new Yard();
			}
			if (null == yard.getStatus()) {
				yard.setStatus(0);
			}
			List<String> list = new ArrayList<String>();
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
				Page<Line> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = yardService.findLineById(yard.getYard_id());
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = yardService.findLineById(yard.getYard_id());
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:导入车场信息列表 2017年11月7日 by wugj version
	 *         0.1 导入模版第一行为表头:车场名称,方向,是否是发车场,创建日期,状态
	 */
	@ApiOperation(value = "导入车场信息列表,(模版第一行为表头,必填,依次为:车场名称,方向,是否是发车场,创建日期,状态) code:024009", produces = "application/json")
	@RequestMapping(value = "importYardList", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> importYardList(@RequestParam("filename") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			InputStream in = null;
			List<Yard> list = new ArrayList<>();
			MultipartFile _file = multipartRequest.getFile("filename");
			in = _file.getInputStream();
			Workbook wb = Workbook.getWorkbook(in);
			Sheet sheet = wb.getSheet(0);
			for (int i = 1; i < sheet.getRows(); i++) {
				Yard yard = new Yard();
				yard.setYard_id(yardService.getSequence("yard_code"));
				yard.setYard_name(sheet.getCell(0, i).getContents());
				yard.setDirection(sheet.getCell(1, i).getContents());
				yard.setIs_yard("是".equals(sheet.getCell(2, i).getContents().trim()));
				String sDate = sheet.getCell(3, i).getContents().trim();
				Date date = new Date();
				if (!StringUtils.isEmpty(sDate)) {
					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
				}
				yard.setCreate_date(date);
				yard.setStatus("正常".equals(sheet.getCell(4, i).getContents().trim())?1:0);
				list.add(yard);
			}
			yardService.addYardList(list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "1");
			map.put("resultMsg", "请求失败!");
			map.put("resPonse", "导入异常");
		}
		return map;
	}

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:导入车场信息列表 2017年11月7日 by wugj version0.1
	 *         导出车场信息列表
	 */
	@ApiOperation(value = "导出车场信息列表 code:024010", produces = "application/json")
	@RequestMapping(value = "exportYardList", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "yard_ids", dataType = "string", required = false, value = "车场id列表,必填,以逗号分隔"),
			@ApiImplicitParam(paramType = "query", name = "filePath", dataType = "string", required = false, value = "导出文件地址") })
	public Map<String, Object> exportYardList(HttpServletRequest request, HttpServletResponse response, String yard_ids,
			String filePath) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		try {
			List<Yard> list = new ArrayList<>();
			if (StringUtils.isEmpty(yard_ids)) {
				list = yardService.findYardList(new Yard());
			} else {
				list = yardService.findYardListByIds(yard_ids);
			}
			HSSFWorkbook hssfworkbook = new HSSFWorkbook();
			HSSFSheet sheet = hssfworkbook.createSheet("sheet1");
			HSSFRow headRow = sheet.createRow(0);
			headRow.createCell(0).setCellValue("车场名称");
			headRow.createCell(1).setCellValue("方向");
			headRow.createCell(2).setCellValue("是否是发车场");
			headRow.createCell(3).setCellValue("创建时间");
			headRow.createCell(4).setCellValue("状态");
			for (Yard _yard : list) {
				HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
				dataRow.createCell(0).setCellValue(_yard.getYard_name());
				dataRow.createCell(1).setCellValue(_yard.getDirection());
				dataRow.createCell(2).setCellValue(_yard.getIs_yard() ? "是" : "否");
				dataRow.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_yard.getCreate_date()));
				dataRow.createCell(4).setCellValue(_yard.getStatus() == 1 ? "正常" : "非正常");
			}
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			hssfworkbook.write(fos);
			fos.close();
			//传入车场信息数
			int a = yard_ids.split(",").length;
			//有效数据条数
			int b = sheet.getLastRowNum();
			map.put("resPonse","导出成功"+b+"条,导出失败"+(a-b)+"条");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "1");
			map.put("resultMsg", "导出异常!");
		}
		return map;
	}
	
	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:导出车场信息列表 2017年11月7日 by wugj version
	 *         0.1
	 */
	/*
	 * @ApiOperation(value = "导出车场信息列表",produces ="application/json")
	 * 
	 * @RequestMapping(value="exportYardList",method= {RequestMethod.POST})
	 * 
	 * @ApiImplicitParams({
	 * 
	 * @ApiImplicitParam(paramType = "query", name = "yard_ids", dataType =
	 * "string", required = false, value = "车场id列表,必填,以逗号分隔"), })
	 * 
	 * @ResponseBody public Map<String, Object>
	 * exportYardList(HttpServletRequest request,HttpServletResponse
	 * response,String yard_ids){ Map<String, Object> map=new
	 * HashMap<String,Object>(); map.put("result", "0"); map.put("resultMsg",
	 * "请求成功!"); String filename="车场数据"; if(StringUtils.isEmpty(yard_ids)){
	 * map.put("result", "1"); map.put("resultMsg", "请至少选择一条数据!");
	 * map.put("resPonse", "删除失败!"); }else{ try{ List<Yard> list =
	 * yardService.findYardListByIds(yard_ids); HSSFWorkbook hssfworkbook =new
	 * HSSFWorkbook(); HSSFSheet sheet = hssfworkbook.createSheet("sheet1");
	 * HSSFRow headRow = sheet.createRow(0);
	 * headRow.createCell(0).setCellValue("车场名称");
	 * headRow.createCell(1).setCellValue("方向");
	 * headRow.createCell(2).setCellValue("是否是发车场");
	 * headRow.createCell(3).setCellValue("创建时间");
	 * headRow.createCell(4).setCellValue("状态"); for (Yard _yard : list) {
	 * HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
	 * dataRow.createCell(0).setCellValue(_yard.getYard_name());
	 * dataRow.createCell(1).setCellValue((_yard.getDirection().equals(0))?"上行":
	 * "下行"); dataRow.createCell(2).setCellValue(_yard.getIs_yard()?"是":"否");
	 * dataRow.createCell(3).setCellValue(_yard.getCreate_date());
	 * dataRow.createCell(4).setCellValue(_yard.getStatus()==0?"正常":"非正常"); }
	 * response.setContentType("application/x-xls");
	 * response.setCharacterEncoding("utf-8");
	 * response.setHeader("Content-disposition", "attachment; filename=" +
	 * filename + ".xls"); ServletOutputStream outputStream =
	 * response.getOutputStream(); hssfworkbook.write(outputStream);
	 * outputStream.close(); response.setCharacterEncoding("utf-8");
	 * response.setContentType("application/vnd.ms-excel"); String
	 * filename="车场数据"; response.setHeader("Content-Disposition",
	 * "attachment;filename="+filename+".xls"); ServletOutputStream outputStream
	 * = response.getOutputStream(); hssfworkbook.write(outputStream);
	 * outputStream.close(); }catch(Exception e){ e.printStackTrace();
	 * map.put("result", "1"); map.put("resultMsg", "请求失败!");
	 * map.put("resPonse", "导出异常"); } } return map; }
	 */
	

	/**
	 * @param yard
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return Map<String,Object> describe:模糊查询车场线路信息集合 2017年11月7日 by wugj version
	 *         0.1
	 */
	/*@ApiOperation(value = "通过车场ID查询线路集合 code:024006", produces = "application/json")
	@RequestMapping(value = "findLinesByYardId", method = { RequestMethod.POST })
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
			@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填") })
	@ResponseBody
	public Map<String, Object> findLinesByYardId(Yard yard, Integer pageNum, Integer pageSize,
			@NotNull(message = "isPage不能为空") Boolean isPage) {
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
			List<Line> list = new ArrayList<Line>();
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
				Page<Line> page = PageHelper.startPage(pageNum.intValue(), pageSize.intValue());// 分页
				list = yardService.findLinesByYardId(yard);
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageNum", page.getPageNum());// 页码
				pageMap.put("pageSize", page.getPageSize());// 每页条数
				pageMap.put("pagetotal", page.getPages());// 总页数
				pageMap.put("total", page.getTotal());// 总条数
				mapData.put("page", pageMap);
			} else {
				list = yardService.findLinesByYardId(yard);
			}
			mapData.put("list", list);
		}
		map.put("resPonse", mapData);
		return map;
	}
*/
}
