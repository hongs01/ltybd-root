package com.ltybd.service;


import java.util.Map;

/**
 * @author zhouyongbo 2017/10/30
 */
public interface IPayLogSearchService {


    /**
     * 根据字段名称查询数据
     * @param pares
     * @return
     */
    Map<String, Object> findOne(Map<String,String> pares);


    /**
     * 保存支付记录
     * @return  0为成功  throw PayException异常信息
     */
    int savePayLog(Object obj);

    /**
     * 保存支付记录
     * @return  0为成功  throw PayException异常信息
     */
    int savePayLog(String jsonStr);

}
