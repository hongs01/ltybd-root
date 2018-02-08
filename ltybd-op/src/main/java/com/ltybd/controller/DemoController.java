package com.ltybd.controller;

import java.util.List;

import com.ltybd.serviceclient.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ltybd.entity.Demo;
import com.ltybd.service.impl.DemoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中
@Api(value="DemoController", description = "demo实例Controller")
@RestController
@RequestMapping("/demo/")
public class DemoController {

	@Autowired
	private DemoServiceImpl demoServiceImpl;

	@Autowired
	private ServiceClient serviceClient;

	@Value("${demo.env}")
	private String env;

	@ApiOperation(value="查询所有数据列表")
	@RequestMapping(value="list",method= {RequestMethod.GET,RequestMethod.POST})
	public List<Demo> getlist()
	{
		PageHelper.startPage(1,3); //分页  第一页显示3条数据
		return demoServiceImpl.getAll();
	}

	@RequestMapping(value="hi",method = {RequestMethod.GET,RequestMethod.POST})
	public  String  abc()
	{
		return serviceClient.echoService.echo("hi world");
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello! It's " + env;
	}
}
