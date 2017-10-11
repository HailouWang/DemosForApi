package com.example.thread;

/**
 * Created by ifei on 2017/9/7.
 *
 * 死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，
 *
 * 若无外力作用，它们都将无法推进下去。此时称系统处于死锁状态或系统产生了死锁，这些永远在互相等待的进程称为死锁进程。
 *
 * 死锁案例程序:
 *
 * jstack pid 获取线程dump
 *
 * 避免死锁的建议：
 * 1、避免在一个线程中，获取多个锁。
 * 2、避免一个线程在锁内同时占用多个资源
 *
 */

public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String args[]){
        deadLock();
    }

    private static void deadLock(){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                synchronized (A){
                    try{
                        Thread.sleep(2000);
                    }catch(Exception e){

                    }
                    synchronized (B){
                        System.out.println("Thread id:"+Thread.currentThread().getId()+1);
                    }
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                synchronized (B){
                    synchronized (A){
                        System.out.println("Thread id:"+Thread.currentThread().getId()+2);
                    }
                }
            }
        };


        t1.start();

        t2.start();
    }

}
