package com.jamindy.concurrent.readwritelock.data;

/**
 * Created by admin on 15-5-17.
 */
public interface Data {

    public String read() throws InterruptedException;

    public void write(char c) throws InterruptedException;
}
