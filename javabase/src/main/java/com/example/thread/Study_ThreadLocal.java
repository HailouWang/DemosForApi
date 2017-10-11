package com.example.thread;

/**
 * Created by ifei on 2017/8/29.
 *
 * 来自：http://blog.csdn.net/lufeng20/article/details/24314381/
 *
 * 1、总结：线程同步/安全机制：一个是锁机制进行时间换空间，一个是存储拷贝进行空间换时间。
 *
 * 2、当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 *
 * 3、ThreadLocal方法：
 *
 * void set(Object value)   设置当前线程的线程局部变量的值。
 * public Object get()      该方法返回当前线程所对应的线程局部变量。
 * public void remove()     将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。
 * 需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，
 * 所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
 * protected Object initialValue()  返回该线程局部变量的初始值，该方法是一个protected的方法，
 * 显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，
 * 并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
 *
 * 4、在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本。
 *
 *     public void set(T value) {
 *          Thread t = Thread.currentThread();
 *          ThreadLocalMap map = getMap(t);
 *          if (map != null)
 *              map.set(this, value);
 *          else
 *              createMap(t, value);
 *     }
 *
 *
 * 5、同步线程的比较：
 *
 * 同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，
 * 使用同步机制要求程序慎密地分析什么时候对变量进行读写，什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，
 * 程序设计和编写难度相对较大。
 *
 * 而ThreadLocal则从另一个角度来解决多线程的并发访问。ThreadLocal会为每一个线程提供一个独立的变量副本，
 * 从而隔离了多个线程对数据的访问冲突。因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。
 * ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
 *
 * 由于ThreadLocal中可以持有任何类型的对象，低版本JDK所提供的get()返回的是Object对象，需要强制类型转换。
 * 但JDK 5.0通过泛型很好的解决了这个问题，在一定程度地简化ThreadLocal的使用，
 *
 * 概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，
 * 而ThreadLocal采用了“以空间换时间”的方式。前者仅提供一份变量，让不同的线程排队访问，
 * 而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
 *
 *
 *
 */

public class Study_ThreadLocal {

    public static void main(String args[]){
        TestThreadClient client1 = new TestThreadClient(3);
        TestThreadClient client2 = new TestThreadClient(4);
        TestThreadClient client3 = new TestThreadClient(5);

        client1.start();
        client2.start();
        client3.start();
    }


    public static class TestThreadClient extends Thread{

        private java.lang.ThreadLocal<Integer> threadLocal = new java.lang.ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        private int mCount = 0;

        public TestThreadClient(int count) {
            mCount = count;
        }

        private int getNumbers(){
            int number = threadLocal.get();
            threadLocal.set(++number);
            return number;
        }

        @Override
        public void run() {
            super.run();

            for(int i=0;i<mCount;i++){
                System.out.println("Thread id:"+getId()+",number:"+getNumbers());
            }
        }
    }
}


