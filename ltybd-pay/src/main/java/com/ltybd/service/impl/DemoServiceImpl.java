package com.ltybd.service.impl;

import com.ltybd.entity.Demo;
import com.ltybd.mapper.DemoMapper;
import com.ltybd.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoDao;
	
	@Override
	public List<Demo> getAll() {
		return demoDao.selectAll();
	}
}
