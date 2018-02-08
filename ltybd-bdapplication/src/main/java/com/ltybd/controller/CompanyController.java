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
import com.ltybd.entity.Company;
import com.ltybd.entity.CompanyBean;
import com.ltybd.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * CompanyController.java
 *
 * describe:公司信息
 * 
 * 2017年10月17日 下午7:00:42 created By Chenjw version 0.1
 *
 * 2017年10月17日 下午7:00:42 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "/Company", description = "公司信息")
@RestController
@RequestMapping("/Company/")
@Validated
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	/**
	 * @param company
	 * @param pageNum
	 * @param pageSize
	 * @param isPage
	 * @return
	 * Map<String,Object>
	 * describe:查询公司信息集合
	 * 2017年10月17日上午10:10:19 by Chejw version 0.1
	 */
	@ApiOperation(value = "查询公司集合 code:014001",produces ="application/json")
	@RequestMapping(value="list",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "company_id", dataType = "int", required = false, value = "公司编号,非必填"),
		@ApiImplicitParam(paramType = "query", name = "company_name", dataType = "String", required = false, value = "公司名称,非必填,模糊查询"),
		@ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", required = false, value = "页码,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = false, value = "每页条数,非必填,需分页时所用"),
		@ApiImplicitParam(paramType = "query", name = "isPage", dataType = "boolean", required = false, value = "是否分页,必填")})
	@ResponseBody  
	public Map<String, Object> findListObj(
			Company company,Integer pageNum,Integer pageSize,
			@NotNull(message="isPage不能为空") Boolean isPage)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> mapData=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if(StringUtils.isEmpty(company.getStatus())) company.setStatus(0);
		List<CompanyBean> list=new ArrayList<CompanyBean>();
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
			Page<Company> page=PageHelper.startPage(pageNum, pageSize);//分页
			list=companyService.findListObj(company);
			Map<String, Object> pageMap=new HashMap<String,Object>();
			pageMap.put("pageNum", page.getPageNum());//页码
			pageMap.put("pageSize", page.getPageSize());//每页条数
			pageMap.put("pagetotal", page.getPages());//总页数
			pageMap.put("total", page.getTotal());//总条数
			mapData.put("page", pageMap);
		}else{
			list=companyService.findListObj(company);
		}
		mapData.put("companyList", list);
		map.put("resPonse", mapData);
		return map;
	}
	
	/**
	 * 
	 * @param company_id
	 * @return
	 * Map<String, Object>
	 * describe:查询公司信息对象
	 * 2017年10月17日上午10:10:29 by Chejw version 0.1
	 * @throws Exception 
	 */
	@ApiOperation(value = "查询公司信息对象  code:014002", produces ="application/json")
	@RequestMapping(value="entity",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "company_id", dataType = "int", required = false, value = "公司ID,必填,大于1000正整数")})
	public Map<String, Object> findByCompanyId(
			@NotNull(message="company_id不能为空") 
			@Min(value=1000,message="必须为大于或者等于1000的正整数") 
			@RequestParam Integer company_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Company company=new Company();
		company.setCompany_id(company_id);
		map.put("resPonse",companyService.findByCompanyId(company)!=null?companyService.findByCompanyId(company):"没有查找到对应数据!" );
		return map;
	}
		

	/**
	 * @param company
	 * @return
	 * Map<String, Object>
	 * describe:更新公司信息对象
	 * 2017年10月12日上午10:10:29 by Chenjw version 0.1
	 */
	@ApiOperation(value = "更新公司信息对象  code:014003",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateCompanyObj(@RequestBody @Validated Company company,BindingResult result)
	{
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
    		return map;
        }else{
        	Integer company_id=0;
    		if(StringUtils.isEmpty(company.getCompany_id())){
    			if(StringUtils.isEmpty(company.getCompany_name())){
    				map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "公司名称Company_name不能为空!");
    				return map;
    			}else{
    				company_id=companyService.getSequence("company_code");
        			company.setCompany_id(company_id);
        			company.setStatus(0);
        			company.setCreate_time(new Date());
        			company.setLast_modified_time(new Date());
        			companyService.insert(company);
    			}
    		}else{
    			if(companyService.findByCompanyId(company)!=null){
    				company_id=companyService.findByCompanyId(company).getCompany_id();
    				company.setLast_modified_time(new Date());
    				companyService.update(company);
    			}else{
    				map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "没有查到此公司对象无法更新");
    				return map;
    			}
    		}
    		map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", company_id);
        }
		return map;
	}
	
	/**
	 * @param company_id
	 * @return
	 * Map<String, Object>
	 * describe:删除公司信息对象
	 * 2017年10月12日上午10:10:32 by Chejw version 0.1
	 */
	@ApiOperation(value = "删除公司信息对象  code:014004",produces ="application/json")
	@RequestMapping(value="delete",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "company_id", dataType = "int", required = false, value = "公司ID,必填,大于1000正整数"), })
	@ResponseBody
	public Map<String, Object> deleteCompanyObj(
			@Min(value=1000,message="请输入大于1000的正整数") 
			Integer company_id){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		Company company=new Company();
		company.setCompany_id(company_id);
		if(companyService.findByCompanyId(company)==null){
			map.put("result", "1");
			map.put("resultMsg", "删除的公司对象不存在!");
			map.put("resPonse", "删除失败!");
		}else{
			int result = companyService.delete(company);
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
	 * describe:批量删除公司信息对象
	 * 2017年10月12日上午10:10:35 by Chejw version 0.1
	 */
	@ApiOperation(value = "批量删除公司信息对象  code:014005",produces ="application/json")
	@RequestMapping(value="deleteItems",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "ids", dataType = "String", required = false, value = "公司ID集合,必填,以(,)隔开,例:1,2,3"), })
	@ResponseBody
	public Map<String, Object> deleteItems(@NotNull(message="ids不能为空") String ids)
	{
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("result", "0");
		 map.put("resultMsg", "请求成功!");
		 String[]idss=ids.split(",");
		 int count=0;
		 String str="";
		 for (int i = 0; i < idss.length; i++) {
			 Company company=new Company();
			 company.setCompany_id(Integer.parseInt(idss[i]));
			 if(companyService.findByCompanyId(company)==null){
					count++;
					str+=","+company.getCompany_id();
				}
			 }
		 map.put("resultMsg", "失败的条数为"+count+"条,成功条数为"+(idss.length-count)+"条,失败的是"+str);
		 int result=companyService.deleteItems(ids);
		 if(result>0){ 
				map.put("resPonse","删除成功!");
			}else{
				map.put("result", "1");
				map.put("resPonse", "删除失败!");
			}
		 return map;
	}
	
	/**
	 * 
	 * @param list
	 * @return Map<String,Object>
	 * @describe:批量更新公司信息对象
	 * @2017年10月16日上午10:59:04 by Yancz version 0.1
	 */
	@ApiOperation(value = "批量更新公司信息对象  code:014006", produces = "application/json")
	@RequestMapping(value = "updateList", method = { RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateList(@RequestBody List<Company> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		if (null == list || list.isEmpty()) {
			map.put("result", "1");
			map.put("resultMsg", "请求失败,请传参数");
			map.put("resPonse", "更新失败");
			return map;
		}
		int result = companyService.updateList(list);
		if (result > 0) {
			map.put("resPonse", "更新成功");
		} else {
			map.put("result", "1");
			map.put("resPonse", "更新失败");
		}
		return map;
	}
	
}
