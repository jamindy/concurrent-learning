package com.jamindy.concurrent.readwritelock.data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 15-5-17.
 */
public class ReentrantLockData implements Data{
    private final char[] buffer;
//    private final ReadWriteLock lock = new ReentrantReadWriteLock();
//    private Lock readLock =lock.readLock();
//    private Lock writeLock = lock.writeLock();
    private final Lock lock = new ReentrantLock();


    public ReentrantLockData(int size) {
        this.buffer = new char[size];
        for(int i=0;i<size;i++){
            buffer[i]='=';
        }
    }

    public String read() throws InterruptedException{
//        readLock.lock();
        lock.lock();
        try {
            return doRead();
        } finally {
//            readLock.unlock();
            lock.unlock();
        }
    }

    public void write(char c) throws InterruptedException{
//        writeLock.lock();
        lock.lock();
        try {
            doWrite(c);
        } finally {
//            writeLock.unlock();
            lock.unlock();
        }
    }


    private String doRead(){
        StringBuilder result = new StringBuilder();
        for(char c : buffer){
            result.append(c);
        }
        sleep(1);
        return result.toString();
    }

    private void doWrite(char c){
        for(int i=0;i<buffer.length;i++){
            buffer[i]=c;
            sleep(1);
        }
    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
