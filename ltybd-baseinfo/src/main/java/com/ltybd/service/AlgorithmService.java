package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Algorithm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="AlgorithmService", description = "轮趟接口")
public interface AlgorithmService {
	@ApiOperation(value="查询轮趟集合")
	List<Algorithm> findListObj(Algorithm algorithm);

	@ApiOperation(value="查询轮趟对象")
	Algorithm findByAlgorithmId(Algorithm algorithm);

	@ApiOperation(value="获取Sequence")
	Integer getSequence(String code);

	@ApiOperation(value="插入轮趟对象")
	int insert(Algorithm algorithm);

	@ApiOperation(value="修改轮趟对象")
	int update(Algorithm algorithm);

	@ApiOperation(value="删除轮趟对象")
	int delete(Algorithm algorithm);

	@ApiOperation(value="批量删除轮趟对象")
	int deleteItems(String ids);

	@ApiOperation(value="批量查询轮趟对象")
	List<Algorithm> batchfindListObj(String ids);

	

}
