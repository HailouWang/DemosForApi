package com.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ifei on 2017/9/7.
 *
 * 线程状态：
 * NEW:初始状态，线程被创建，淡水还没有调用start方法
 * RUNNABLE：运行状态，等同于操作系统中的就绪(READY)和运行(RUNNING) 两种状态
 * BLOCKED：阻塞状态，表示线程阻塞于锁
 * WAITING：等待状态，线程进入等待状态。
 * TIMED_WAITING：超时等待状态
 * TERMINATED：终止状态，表示当前线程已经执行完毕。
 *
 *
 * jps：Java Virtual Machine Process Status Tool
 *
 */

public class TheadStateDemo {

    public static void main(String args[]){
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }

    /**
     * 线程不断的进行睡眠
     */
    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while(true){
                SleetUtils.second(100);
            }
        }
    }

    /**
     * 该实例在Waiting等待
     */
    static class Waiting implements Runnable{

        @Override
        public void run() {
            while(true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该实例在Blocked上加锁后，不会释放锁
     */
    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized(Blocked.class){
                while(true){
                    SleetUtils.second(200);
                }
            }
        }
    }

    static class SleetUtils{
        public static final void second(long seconds){
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
