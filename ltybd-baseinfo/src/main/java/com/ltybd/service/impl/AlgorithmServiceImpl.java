package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Algorithm;
import com.ltybd.mapper.AlgorithmMapper;
import com.ltybd.service.AlgorithmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="AlgorithmServiceImpl", description = "轮趟接口实现类")
@Service
public class AlgorithmServiceImpl implements AlgorithmService {
	@Autowired
	private AlgorithmMapper AlgorithmDao;
	@Override
	@ApiOperation(value="查询轮趟集合")
	public List<Algorithm> findListObj(Algorithm algorithm) {
		return AlgorithmDao.findListObj(algorithm);
	}

	@ApiOperation(value="查询轮趟对象")
	@Override
	public Algorithm findByAlgorithmId(Algorithm algorithm) {
		return AlgorithmDao.findByAlgorithmId(algorithm);
	}
	
	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String code) {

		return AlgorithmDao.getSequence(code);
	}

	@ApiOperation(value="插入轮趟对象")
	@Override
	public int insert(Algorithm algorithm) {
		
		return AlgorithmDao.insert(algorithm);
		
		
	}

	@ApiOperation(value="修改轮趟对象")
	@Override
	public int update(Algorithm algorithm) {
		
		return AlgorithmDao.updateByPrimaryKeySelective(algorithm);
	
		
	}
	@ApiOperation(value="删除轮趟对象")
	@Override
	public int delete(Algorithm algorithm) {
		
		return AlgorithmDao.delete(algorithm);
	}

	@ApiOperation(value="批量删除轮趟对象")
	@Override
	public int deleteItems(String ids) {
		
		return AlgorithmDao.deleteItems(ids);
	}
	
	@ApiOperation(value="批量查询轮趟对象")
	@Override
	public List<Algorithm> batchfindListObj(String ids) {
		
		return AlgorithmDao.batchfindListObj(ids);
		
	}

}
