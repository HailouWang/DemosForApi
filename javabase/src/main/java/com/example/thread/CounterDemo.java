package com.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ifei on 2017/9/7.
 *
 * Volatile:轻量级，线程访问共享变量，volatile保证线程获取的共享变量的值是一致的。
 * synchronized：重量级，
 * 1、对于普通同步方法来说，锁是当前实例对象。
 * 2、对于静态同步方法，锁是当前类的Class对象
 * 3、对于静态同步方法，锁是Synchronzed括号里配置的对象。
 *
 * Synchronzed锁信息存储在Java对象头中。
 *
 * Volatile :用来修饰字段，告知程序任何对该变量的访问均需从共享内存中获取，而对它的改变也必须同步刷新到共享内存。
 *
 */

public class CounterDemo {
    private AtomicInteger atomicT = new AtomicInteger(0);
    private int i=0;

    public static void main(String args[]){
        final CounterDemo counter = new CounterDemo();

        List<Thread> ts = new ArrayList<Thread>(600);

        long start = System.currentTimeMillis();

        for(int i=0;i<100;i++){
            Thread td = new Thread(){
                @Override
                public void run() {
                    super.run();
                    for(int j=0;j<10;j++){
                        counter.count();
                        counter.safeCount();
                    }
                }
            };
            ts.add(td);
        }

        for(Thread td : ts){
            td.start();
        }

        for(Thread td : ts){
            try {
                td.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("counter:"+counter.i);
        System.out.println("counter safely:"+counter.atomicT.get());
        System.out.println("time:"+(System.currentTimeMillis() - start));
    }

    /**
     * 线程安全
     */
    private void safeCount(){
        for(;;){
            int i = atomicT.get();
            boolean suc = atomicT.compareAndSet(i,++i);
            if(suc){
                return;
            }
        }
    }

    /**
     * 非线程安全
     */
    private void count(){
        ++i;
    }
}
