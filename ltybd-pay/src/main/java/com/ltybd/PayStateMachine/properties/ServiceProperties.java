package com.ltybd.PayStateMachine.properties;


/**
 * @author zhouyongbo 2017/11/3
 * 服务配置   abstract 仅仅只是为了spring 方便调用 不通过接口
 */
public abstract class ServiceProperties {
    private String serviceId;


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
