package com.ltybd.PayStateMachine;

import com.ltybd.common.domain.PayStateData;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *@author zhouyongbo 2017/10/31
 * 容器 -->队列
 */
public class ContainerQueue {
    private static final LinkedBlockingDeque<PayStateData> QUEUE = new LinkedBlockingDeque<PayStateData>();

    public static boolean add(PayStateData payStateData){
        return QUEUE.add(payStateData);
    }

    public static void addFirst(PayStateData payStateData){
        QUEUE.addFirst(payStateData);
    }

    public static void addLast(PayStateData payStateData){
        QUEUE.addLast(payStateData);
    }

    public static PayStateData poll(){
        return QUEUE.poll();
    }

    public static PayStateData take() throws InterruptedException {
        return QUEUE.take();
    }



}
