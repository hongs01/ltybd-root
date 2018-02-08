package com.ltybd.mapper;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.YardLine;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value = "YardLineMapper", description = "车场线路接口")
public interface YardLineMapper extends MyMapper<YardLine> {

	@ApiOperation(value = "根据线路ID查询车场线路对象")
	@Select("<script>"
			+ " select * from op_yard_line"
			+ " where 1 = 1 "
        	+ " and line_id = #{line_id}"
        	+ " and status = #{status} "
			+ "</script>")
	YardLine findByLineId(YardLine yardLine);
	
}
