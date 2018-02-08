package com.ltybd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltybd.entity.Demo;
import com.ltybd.mapper.DemoMapper;
import com.ltybd.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoDao;
	
	@Override
	public List<Demo> getAll() {
		return demoDao.selectAll();
	}
}
