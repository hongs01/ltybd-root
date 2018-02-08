package com.ltybd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * OfflineApplication.java
 *
 * describe:
 * 
 * 2017年10月17日 下午3:30:25 created By chenq version 0.1
 *
 * 2017年10月17日 下午3:30:25 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ltybd.mapper")
@ServletComponentScan
public class OfflineApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(OfflineApplication.class, args);
    }
}
