package com.jamindy.concurrent.readwritelock;

import com.jamindy.concurrent.readwritelock.data.*;

/**
 * Created by admin on 15-5-17.
 */
public class Client {

    public static void main(String[] args){
//        Data data = new NoConcurrentData(10);
        Data data = new SyncData(10);
//        Data data = new LockData(10);
//        Data data = new ReentrantLockData(10);

        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();

        new WriteThread(data,"abcdefg").start();
        new WriteThread(data,"0123456").start();
    }
}
