package com.ltybd.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyMapper.java
 *
 * describe:
 * 
 * 2017年10月9日 下午4:20:44 created By Linsir version 0.1
 *
 * 2017年10月9日 下午4:20:44 modifyed By Linsir version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
