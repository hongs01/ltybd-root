package com.ltybd.controller;

import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Demo;
import com.ltybd.service.impl.DemoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Api(value="DemoController", description = "demo实例Controller")
@RestController
@RequestMapping("/demo/")
public class DemoController {

	@Autowired
	private DemoServiceImpl demoServiceImpl;
	
//	@ApiOperation(value="查询所有数据列表")
	@RequestMapping(value="list",method= {RequestMethod.GET,RequestMethod.POST})
	public List<Demo> getlist()
	{
		PageHelper.startPage(1,3); //分页  第一页显示3条数据
		return demoServiceImpl.getAll();
	}

	@RequestMapping(value="hello",method= {RequestMethod.GET,RequestMethod.POST})
	public Object helloA(){
		return "helloB";
	}
}
