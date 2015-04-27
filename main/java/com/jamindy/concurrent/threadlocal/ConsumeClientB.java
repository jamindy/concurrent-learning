package com.jamindy.concurrent.threadlocal;

import java.util.Random;

/**
 * Created by admin on 15-4-21.
 */
public class ConsumeClientB implements Consumer {
    private ThreadLocal<Integer> leftNumThreadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 30;
        }
    };

    @Override
    public int consume() {
        int orgLeftNum = leftNumThreadLocal.get();
        Random random  = new Random(System.currentTimeMillis());
        try {
            Thread.sleep(random.nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orgLeftNum = orgLeftNum -1;
        leftNumThreadLocal.set(orgLeftNum);
        return leftNumThreadLocal.get();
    }

    public static void main(String[] args){
        Consumer consumer = new ConsumeClientB();
        Thread thread1 = new Thread(new ComsumeThread(consumer));
        Thread thread2 = new Thread(new ComsumeThread(consumer));
        Thread thread3 = new Thread(new ComsumeThread(consumer));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
