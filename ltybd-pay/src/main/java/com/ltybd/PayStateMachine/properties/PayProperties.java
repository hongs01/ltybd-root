package com.ltybd.PayStateMachine.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouyongbo 2017/10/31
 * 关于 支付数据监控的配置
 */
@Component
@ConfigurationProperties(prefix = "pay")
public class PayProperties {
    private String cacheUseType;
    private PayData data;
    private Map<String,List<String>> serviceIds = new HashMap<String,List<String>>();

    public String getCacheUseType() {
        return cacheUseType;
    }

    public void setCacheUseType(String cacheUseType) {
        this.cacheUseType = cacheUseType;
    }

    public PayData getData() {
        return data;
    }

    public void setData(PayData data) {
        this.data = data;
    }

    public Map<String, List<String>> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Map<String, List<String>> serviceIds) {
        this.serviceIds = serviceIds;
    }
}
