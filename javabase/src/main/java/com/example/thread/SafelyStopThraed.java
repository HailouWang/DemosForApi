package com.example.thread;

/**
 * Created by ifei on 2017/9/7.
 */

public class SafelyStopThraed {

    public static void main(String args[]){
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();

        SleetUtils.second(2);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();

        SleetUtils.second(2);
        two.cancel();
    }

    private static class Runner implements Runnable{

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while(on&&!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("count i = "+i);
        }

        private void cancel(){
            on = false;
        }
    }
}
