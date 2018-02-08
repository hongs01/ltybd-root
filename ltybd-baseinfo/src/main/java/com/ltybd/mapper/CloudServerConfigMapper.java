package com.ltybd.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.ltybd.entity.CloudServerConfig;
import com.ltybd.util.MyMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="CloudServerConfigMapper", description = "平台接入Mapper")
public interface CloudServerConfigMapper extends MyMapper<CloudServerConfig> {

	@ApiOperation(value="查询平台接入对象")
	@Select("<script>"
			+ " select * from op_cloud_server_config "
			+ " where 1=1"
			+ " and type = ${type}"
			+ " limit 0,1"
			+ " </script> ")
	public CloudServerConfig findPlatform(CloudServerConfig cloudServerConfig);
	
	@ApiOperation(value="修改平台接入对象")
	@Update(" <script> "
			+" UPDATE op_cloud_server_config "
			+" SET "
			+ " access_key = #{access_key}, "
			+ " name = #{name}, "
			+ " port = #{port} "
			+" WHERE 1=1 "
			+" and config_id = #{config_id} "
			+ " </script> ")
	public int updatePlatform(CloudServerConfig cloudServerConfig);
	
	@ApiOperation(value="插入平台接入对象")
	@Insert(" <script> "
			+" insert into op_cloud_server_config (access_key,name,port,type) "
			+ " values "
			+ " (#{access_key},#{name},#{port},#{type}) "
			+ " </script> ")
	public int insert(CloudServerConfig cloudServerConfig);

	@ApiOperation(value="根据ID查询平台接入对象")
	@Select(" <script> "
			+ " select * from op_cloud_server_config "
			+ " where 1=1 "
			+ " and config_id = ${config_id} "
			+ " </script> ")
	public CloudServerConfig findById(CloudServerConfig cloudServerConfig);
}
