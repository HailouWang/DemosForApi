package com.example.thread;

/**
 * Created by ifei on 2017/9/7.
 *
 * Thread.Join 如果线程A调用了B.join，那么当前线程A就必须等到线程B终止之后，
 *
 * A才会返回。
 *
 * 内部实现:
 *
 * 当线程终止时，会执行自身的nofityAll方法，会通知所有等待该线程完成的对象线程。
 *
 */

public class ThreadJoinDemo {

    private static int count = 0;

    public static void main(String args[]){
        Thread previous = Thread.currentThread();

        for(int i=0;i<10;i++){
            Thread td = new Thread(new Count(previous));
            td.start();
            previous = td;
        }

        System.out.println("Name:"+Thread.currentThread().getName()
                +",count:"+count);
    }

    static class Count implements Runnable{

        private Thread thread;

        public Count(Thread td){
            thread = td;
        }

        @Override
        public void run() {
            try {
                thread.join();
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Name:"+Thread.currentThread().getName()
                +",count:"+count);
        }
    }
}
