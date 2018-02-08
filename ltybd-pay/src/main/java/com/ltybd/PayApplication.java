package com.ltybd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * PayApplication.java
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
public class PayApplication {

    public static void main( String[] args ) {
    	SpringApplication.run(PayApplication.class, args);
    }
    
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//        	String[] beans=ctx.getBeanDefinitionNames();
//        	for (String b : beans) {
//				System.out.println(b);
//			}
//            System.out.println("op服务启动中...");
//        };
//    }

}
