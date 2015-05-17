package com.jamindy.concurrent.readwritelock;

import com.jamindy.concurrent.readwritelock.data.Data;

/**
 * Created by admin on 15-5-17.
 */
public class WriteThread extends Thread{

    private Data data;
    private String str;
    private int index = 0;

    public WriteThread(Data data, String str) {
        this.data = data;
        this.str = str;
    }

    @Override
    public void run() {
        try {
            while(true){
                char c = next();
                data.write(c);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char next(){
        char c = str.charAt(index);
        index++;
        if(index >=str.length()){
            index = 0;
        }
        return c;
    }
}
