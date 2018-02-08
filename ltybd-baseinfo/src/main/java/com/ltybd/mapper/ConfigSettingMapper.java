package com.ltybd.mapper;

import org.apache.ibatis.annotations.Select;
import com.ltybd.entity.ConfigSetting;
import com.ltybd.util.MyMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ConfigSettingMapper", description = "配置Mapper")
public interface ConfigSettingMapper extends MyMapper<ConfigSetting> {

	@ApiOperation(value=" 查询配置对象 ")
	@Select(" <script> "
			+ " select * from config_setting "
			+ " where 1=1 "
			+ " <if test='config_setting_id != null'> " 
			+ " and config_setting_id = ${config_setting_id} "
			+ " </if> "
			+ " limit 0,1 "
			+ " </script> ")
	public ConfigSetting findById(ConfigSetting configSetting);
	
	
}
