package com.jamindy.concurrent.threadlocal;

/**
 * Created by admin on 15-4-21.
 */
public class ConsumeClientD implements Consumer {

    private MyThreadLocal<Integer> leftNumThreadLocal = new MyThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 30;
        }
    };

    @Override
    public int consume() {
        leftNumThreadLocal.set(leftNumThreadLocal.get()-1);
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
