package com.example.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;


/**
 * Created by ifei on 2017/9/7.
 */

public class PipedReaderWriterDemo {

    public static void main(String args[]){
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();
        try {
            writer.connect(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Print(reader));
        thread.start();


        int receive = 0;
        try {
            while((receive = System.in.read())!=0){
                writer.write(receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Print implements Runnable{

        private PipedReader mPipedReader;

        public Print(PipedReader reader){
            mPipedReader = reader;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while((receive = mPipedReader.read())!=-1){
                    System.out.print((char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
