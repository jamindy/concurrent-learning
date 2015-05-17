package com.jamindy.concurrent.readwritelock.data;

import com.jamindy.concurrent.readwritelock.ReadWriteLock;

/**
 * Created by admin on 15-5-17.
 */
public class LockData implements Data{

    private final char[] buffer;
    private ReadWriteLock readWriteLock = new ReadWriteLock();

    public LockData(int size) {
        this.buffer = new char[size];
        for(int i=0;i<size;i++){
            buffer[i]='=';
        }
    }

    public String read() throws InterruptedException{
        readWriteLock.readLock();
        try {
            return doRead();
        } finally {
            readWriteLock.unReadLock();
        }
    }

    public void write(char c) throws InterruptedException{
        readWriteLock.writeLock();
        try {
            doWrite(c);
        } finally {
            readWriteLock.unWriteLock();
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
