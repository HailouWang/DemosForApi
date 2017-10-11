package com.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ifei on 2017/9/7.
 */

public class SleetUtils {
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
