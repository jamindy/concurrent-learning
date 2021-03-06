package com.jamindy.concurrent.threadlocal;

import java.util.Random;

/**
 * Created by admin on 15-4-21.
 */
public class ConsumeClientA implements Consumer {

    private static int leftNum = 30;

    @Override
    public int consume() {
        int orgLeftNum = leftNum;
        Random random  = new Random(System.currentTimeMillis());
        try {
            Thread.sleep(random.nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orgLeftNum = orgLeftNum -1;
        leftNum = orgLeftNum;
        return leftNum;
    }

    public static void main(String[] args){
        Consumer consumer = new ConsumeClientA();
        Thread thread1 = new Thread(new ComsumeThread(consumer));
        Thread thread2 = new Thread(new ComsumeThread(consumer));
        Thread thread3 = new Thread(new ComsumeThread(consumer));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
