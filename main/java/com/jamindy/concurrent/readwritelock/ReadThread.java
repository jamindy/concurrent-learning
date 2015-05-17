package com.jamindy.concurrent.readwritelock;

import com.jamindy.concurrent.readwritelock.data.Data;

/**
 * Created by admin on 15-5-17.
 */
public class ReadThread extends Thread {

    private Data data;

    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run(){
        try {
            while(true){
                long begin = System.currentTimeMillis();
                for(int i=0;i<3;i++){
                    System.out.println(this.currentThread().getName()+"read result:"+data.read());
                }

                long end = System.currentTimeMillis();
                System.out.println("read take time: "+(end-begin)+"ms");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
