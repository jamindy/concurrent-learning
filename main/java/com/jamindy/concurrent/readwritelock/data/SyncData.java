package com.jamindy.concurrent.readwritelock.data;

/**
 * Created by admin on 15-5-17.
 */
public class SyncData implements Data{

    private final char[] buffer;

    public SyncData(int size) {
        this.buffer = new char[size];
        for(int i=0;i<size;i++){
            buffer[i]='=';
        }
    }

    public synchronized String read(){
        StringBuilder result = new StringBuilder();
        for(char c : buffer){
            result.append(c);
        }
        sleep(1);
        return result.toString();
    }

    public synchronized void write(char c){
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
