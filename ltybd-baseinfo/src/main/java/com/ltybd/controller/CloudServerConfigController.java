package com.ltybd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.ltybd.entity.CloudServerConfig;
import com.ltybd.service.CloudServerConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月7日
 */
@Api(value = "/CloudServerConfig", description = "平台接入")
@RestController
@RequestMapping("/CloudServerConfig/")
@Validated
public class CloudServerConfigController {

	@Autowired
	private CloudServerConfigService cloudServerConfigService;
	
	@ApiOperation(value = "查询平台接入对象  code: 022001", produces ="application/json")
	@RequestMapping(value="entity",method= {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "type", dataType = "int", required = false, value = "平台接入类型,0:大数据云计算接入；1：调度监控系统接入")})
	public Map<String, Object> findPlatform(@RequestParam Integer type){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		CloudServerConfig cloudServerConfig=new CloudServerConfig();
		cloudServerConfig.setType(type);
		map.put("resPonse",cloudServerConfigService.findPlatform(cloudServerConfig)!=null?cloudServerConfigService.findPlatform(cloudServerConfig):"" );
		return map;
	}
	
	@ApiOperation(value = "更新平台接入对象  code: 022002",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updetePlatform(@RequestBody @Validated CloudServerConfig cloudServerConfig,BindingResult result ){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	Integer config_id=0;
        	if(StringUtils.isEmpty(cloudServerConfig.getConfig_id())){
        		if(StringUtils.isEmpty(cloudServerConfig.getName())){
        			map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "IP不能为空!");
    				return map;
        		}else if(StringUtils.isEmpty(cloudServerConfig.getAccess_key())){
        			map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "秘钥不能为空!");
    				return map;
        		}else if(StringUtils.isEmpty(cloudServerConfig.getPort())){
        			map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "端口号不能为空!");
    				return map;
        		}else{
        			
        			config_id = cloudServerConfigService.getSequence("cloudServer_code");
        			cloudServerConfig.setConfig_id(config_id);
        			cloudServerConfigService.insert(cloudServerConfig);
        		}
        	}else{
        		if(cloudServerConfigService.findById(cloudServerConfig) != null){
        			config_id = cloudServerConfig.getConfig_id();
        			cloudServerConfigService.updatePlatform(cloudServerConfig);
        		}else{
        			map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "配置失败");
    				return map;
        		}
        	}
        	map.put("result", "0");
    		map.put("resultMsg", "请求成功!");
    		map.put("resPonse", config_id);
        }
		
		return map;
	}	
}
