package com.jamindy.concurrent.readwritelock;

/**
 * Created by admin on 15-5-17.
 */
public class ReadWriteLock {
    private int readThreadCount = 0;
    private int waitingWriteCount = 0;
    private int writeThreadCount = 0;
    private boolean writeFlag = true; //是否写线程优先

    public synchronized void readLock() throws InterruptedException{
        //当存在正在写线程，或者写优先时，存在等待写的线程，则阻塞读线程
        while(writeThreadCount > 0 || (writeFlag && waitingWriteCount > 0)){
            wait();
        }

        readThreadCount++;
    }

    public synchronized void unReadLock() throws InterruptedException{
        readThreadCount--;
        //读线程结束，设置对写优先
        writeFlag = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException{
        waitingWriteCount++;
        try {
            while(readThreadCount > 0 || writeThreadCount > 0){
                wait();
            }
        } finally {
            waitingWriteCount--;
        }
        writeThreadCount++;
    }

    public synchronized void unWriteLock(){
        writeThreadCount--;
        writeFlag=false;
        notifyAll();
    }

}
