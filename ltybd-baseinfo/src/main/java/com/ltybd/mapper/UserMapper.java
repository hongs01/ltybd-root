package com.ltybd.mapper;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.User;
import com.ltybd.entity.UserBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value = "UserMapper", description = "用户Mapper")
public interface UserMapper extends MyMapper<User>{

	@ApiOperation(value = "根据用户ID查询用户对象")
	@Select("<script>"
			+ " select * from op_user"
			+ " where 1 = 1 "
        	+ " and user_id = #{user_id}"
        	+ " and status = #{status} "
			+ "</script>")
	public UserBean findById(User user);

}
