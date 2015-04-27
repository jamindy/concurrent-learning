package com.jamindy.concurrent.threadlocal;

/**
 * Created by admin on 15-4-21.
 */
public class ComsumeThread implements Runnable {

    private Consumer consumer;

    public ComsumeThread(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" After Consume left:"+consumer.consume());
        }

    }
}
