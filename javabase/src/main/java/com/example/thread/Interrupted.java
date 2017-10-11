package com.example.thread;

/**
 * Created by ifei on 2017/9/7.
 *
 * Daemon线程：支持线程，主要用于程序中后台调度以及支持性的工作，如果一个线程不存在非Daemon线程，则线程退出。
 *
 * Thread.sleep()会抛出InterruptedException，在抛出异常前，会清楚Interrupted
 *
 *
 */

public class Interrupted {

    public static void main(String args[]){
        //不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);

        //不停的运行
        Thread busyThead = new Thread(new BusyRunner(),"BusyThread");
        busyThead.setDaemon(true);

        sleepThread.start();
        busyThead.start();

        SleetUtils.second(5);

        sleepThread.interrupt();
        busyThead.interrupt();

        System.out.println("SleepThread interrupted :"+sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted :"+busyThead.isInterrupted());

        SleetUtils.second(2);
    }

    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while(true){
                SleetUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while(true){

            }
        }
    }
}
