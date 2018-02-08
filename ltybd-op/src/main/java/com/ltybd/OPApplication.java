package com.ltybd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * App.java
 *
 * describe:
 * 
 * 2017年10月9日 上午11:18:15 created By Linsir version 0.1
 *
 * 2017年10月9日 上午11:18:15 modifyed By Linsir version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ltybd.mapper")
public class OPApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(OPApplication.class, args);

    }
}
