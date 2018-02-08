package com.ltybd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

/**
 * ConfigServerApplication.java
 *
 * describe:大数据管理项目配置管理入口
 * 
 * 2017年10月7日 下午12:45:47 created By Linsir version 0.1
 *
 * 2017年10月7日 下午12:45:47 modifyed By Linsir version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigServer
public class ConfigServerApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ConfigServerApplication.class, args);
    }
}
