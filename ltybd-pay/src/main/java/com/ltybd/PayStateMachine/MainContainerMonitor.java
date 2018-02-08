package com.ltybd.PayStateMachine;


/**
 * @author zhouyongbo 2017/10/31
 * 数据监控启动
 */
public class MainContainerMonitor implements Runnable{

    @Override
    public void run() {
        while (true){
            ContainerMonitor.execute();
        }
    }
}
