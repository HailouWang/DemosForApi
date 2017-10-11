package com.example.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ifei on 2017/9/7.
 */

public class WaitNotifyThreadDemo {
    static boolean flag = true;
    static WaitNotifyThreadDemo object = new WaitNotifyThreadDemo();

    public static void main(String args[]){
        Thread wait = new Thread(new Wait(),"WaitThread");
        wait.start();

        SleetUtils.second(1);

        Thread notify = new Thread(new Notify(),"NotifyThread");
        notify.start();
    }

    static class Wait implements Runnable{

        @Override
        public void run() {
            System.out.println("synchronzed wait,,,start..."
                    +",Date:"+getCurrentTimeFormat());
            //获取到锁
            synchronized (object){
                while(flag){
                    System.out.println("WaitNotifyThreadDemo.....wait...,flag:"+flag
                    +",Date:"+getCurrentTimeFormat());

                    try {//释放锁
                        object.wait();
                        SleetUtils.second(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            System.out.println("WaintNotifyThreadDemo doWork,flag:"+flag+",Date:"+getCurrentTimeFormat());
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            System.out.println("synchronzed notify,,,start..."
                    +",Date:"+getCurrentTimeFormat());
            synchronized (object){
                flag = false;
                System.out.println("WaitNotifyThreadDemo......Notify flag:"+flag
                        +",Date:"+getCurrentTimeFormat());
                object.notifyAll();
            }

            //再次获取锁
            synchronized (object){
                System.out.println("WaitNotifyThreadDemo......Notify...synchronzed object again..."
                        +",Date:"+getCurrentTimeFormat());
                SleetUtils.second(5);
            }
            System.out.println("synchronzed notify,,,end..."
                    +",Date:"+getCurrentTimeFormat());
        }
    }

    public static String getCurrentTimeFormat(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date()).toString();
    }
}
