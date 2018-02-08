package com.ltybd.serviceclient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ltybd.payapi.EchoService;
import org.springframework.stereotype.Component;

@Component
public class ServiceClient {
    @Reference(version = "1.0.0")
    public EchoService echoService;
}
