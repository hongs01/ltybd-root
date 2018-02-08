package com.ltybd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.YardLine;
import com.ltybd.mapper.YardLineMapper;
import com.ltybd.service.YardLineService;

import io.swagger.annotations.Api;

/**
 * @author wangquan
 *
 *2017年11月8日
 */
@Api(value = "YardLineServiceImpl", description = "车场线路接口实现类")
@Service
public class YardLineServiceImpl implements YardLineService {
	
	@Autowired
	private YardLineMapper yardLineDao;

	@Override
	public YardLine findByLineId(YardLine yardLine) {
		if(null == yardLine.getStatus()){
			yardLine.setStatus(0);
		}
		return yardLineDao.findByLineId(yardLine);
	}
	
	

}
