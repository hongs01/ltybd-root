package com.ltybd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * BaseInfoApplication.java
 *
 * describe:程序启动类
 * 
 * 2017年10月9日 上午11:18:15 created By Chejw version 0.1
 *
 * 2017年10月9日 上午11:18:15 modifyed By Chejw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.ltybd.mapper")
@ServletComponentScan
public class BaseInfoApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(BaseInfoApplication.class, args);
    }
}
