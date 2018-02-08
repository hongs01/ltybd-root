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
import com.ltybd.entity.ConfigSetting;
import com.ltybd.service.ConfigSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月7日
 */
@Api(value = "/ConfigSetting", description = "配置")
@RestController
@RequestMapping("/ConfigSetting/")
@Validated
public class ConfigSettingController {

	@Autowired
	private ConfigSettingService configSettingService;
	
	@ApiOperation(value = "查询配置对象  code:023001", produces ="application/json")
	@RequestMapping(value="entity",method= {RequestMethod.POST})
	public Map<String, Object> findById(){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("result", "0");
		map.put("resultMsg", "请求成功!");
		ConfigSetting configSetting=new ConfigSetting();
		map.put("resPonse",configSettingService.findById(configSetting) !=null?configSettingService.findById(configSetting):"" );
		return map;
	}
	
	@ApiOperation(value = "更新配置对象  code:023002",produces ="application/json")
	@RequestMapping(value="update",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updeteConfig(@RequestBody @Validated ConfigSetting configSetting,BindingResult result ){
		Map<String, Object> map=new HashMap<String,Object>();
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            map.put("result", "1");
    		map.put("resultMsg", "请求失败!");
    		map.put("resPonse", errorList);
        }else{
        	Integer config_id=0;
        	if(StringUtils.isEmpty(configSetting.getConfig_setting_id())){
        		config_id = configSettingService.getSequence("setting_code");
        		configSetting.setConfig_setting_id(config_id);
        		configSettingService.insert(configSetting);	
        	}else{
        		if(configSettingService.findById(configSetting) !=null){
        			config_id = configSetting.getConfig_setting_id();
        			configSettingService.update(configSetting);
        		}else {
        			map.put("result", "1");
    				map.put("resultMsg", "请求失败!");
    				map.put("resPonse", "设置失败");
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
