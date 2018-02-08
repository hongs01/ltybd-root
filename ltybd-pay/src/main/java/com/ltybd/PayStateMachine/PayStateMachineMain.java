package com.ltybd.PayStateMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PayStateMachineMain implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(PayStateMachineMain.class);

    @Autowired
    ICContainerQueue icContainerQueue;

    @Override
    public void run(String... strings) throws Exception {
        //初始化容器
        icContainerQueue.init();
        logger.info("容器初始化完成");

        //启动数据监控
        new Thread(new MainContainerMonitor()).start();
        logger.info("数据监控启动完毕");
    }
}
