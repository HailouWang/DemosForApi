package com.example.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by ifei on 2017/9/7.
 *
 * 通信在线程间存在 共享内存和消息传递两种方式。
 * 共享内存：线程之间共享程序的公共状态，通过写-读内存中的公共状态进行隐式通信。
 * 消息传递：线程之间没有公共状态，线程之间通过发送消息来显式的通信。
 *
 * Java总的实例域、静态域和数组元素存储在堆内存中，堆内存在线程之间共享。
 *
 * Java中线程的共享变量存储在主内存(MainMemory)中，
 * 还有一个本地内存(Local Memory)，虚拟抽象的，并不真实存在。
 * LocalMemory中存储了共享变量的副本。
 *
 */

public class JavaMemoryDemos {

    public static void main(String args[]){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);

        for(ThreadInfo info:threadInfos){
            System.out.println("JavaMemoryDemos threadInfo："+info);
        }
    }

}
