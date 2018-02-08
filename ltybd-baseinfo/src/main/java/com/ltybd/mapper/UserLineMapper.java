package com.ltybd.mapper;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.UserLine;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserLineMapper", description = "用户线路Mapper")
public interface UserLineMapper extends MyMapper<UserLine>{

	@ApiOperation(value = "根据线路ID查询用户线路对象")
	@Select("<script>"
			+ " select * from op_user_line"
			+ " where 1 = 1 "
        	+ " and line_id = #{line_id}"
        	+ " and status = #{status} "
			+ "</script>")
	UserLine findByLineId(UserLine userLine);

}
