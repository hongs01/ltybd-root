package com.ltybd.PayStateMachine;

import com.ltybd.PayStateMachine.properties.PayProperties;
import com.ltybd.SpringUtil;
import com.ltybd.common.domain.PayStateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据监控池
 */
public class ContainerMonitor  {
    private static Logger logger = LoggerFactory.getLogger(ContainerMonitor.class);
    private final static ExecutorService threadPool = Executors.newFixedThreadPool(5000);

    private static PayProperties payProperties = SpringUtil.getBeanByClass(PayProperties.class);

    private static ICContainerQueue icContainerQueue = SpringUtil.getBeanByClass(ICContainerQueue.class);

    public static void execute(){
        PayStateData take = null;
        //第一步 取数据
        try {
            take = icContainerQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("队列取数据出错", e);
        }
        if (take == null ){
            return;
        }
        //第二步 初始化支付监控
        PayMonitor payMonitor = new PayMonitor(payProperties.getData().getIntervalTime(),
                payProperties.getData().getIntervalNum(), payProperties.getData().getInitTimer(),
                take );
        threadPool.execute(payMonitor);
    }
}
