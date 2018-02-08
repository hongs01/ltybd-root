package com.ltybd.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ltybd.payapi.EchoService;

@Service(version = "1.0.0")
public class EchoServerImpl implements EchoService {

    public String echo(String str) {
        System.out.println(str);
        return str;
    }
}
