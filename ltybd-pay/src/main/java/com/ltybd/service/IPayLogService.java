package com.ltybd.service;

import com.ltybd.common.exception.PayException;
import com.ltybd.entity.PayRecord;

import java.util.Map;

public interface IPayLogService {

    /**
     *
     * @param payRecord 需要保存的数据
     * @return  0:保存成功    PayException 为保存失败的信息
     */
    int savePayLog(PayRecord payRecord) throws PayException;

    /**
     * 获取
     * @return
     * @throws PayException
     */
    Map<String, Object> getPayLog(String key) throws PayException;
}
