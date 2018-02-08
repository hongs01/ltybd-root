package com.ltybd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * BdappApplication.java
 *
 * describe:大數據對外提供的應用接口服務入口
 * 
 * 2017年10月26日 下午1:41:08 created By Linsir version 0.1
 *
 * 2017年10月26日 下午1:41:08 modifyed By Linsir version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@SpringBootApplication
@ComponentScan("com.ltybd")
@MapperScan(basePackages = "com.ltybd.mapper")
@ServletComponentScan
@ImportResource(locations = {"classpath:/hbase-spring.xml"})
public class BdappApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(BdappApplication.class, args);       
    }
    
}
