package com.jamindy.concurrent.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 15-4-21.
 */
public class MyThreadLocal<T> {
    private Map<Thread,T> myThreadLocalMap = new ConcurrentHashMap<Thread, T>();

    public void set(T value){
        myThreadLocalMap.put(Thread.currentThread(),value);
    }

    public T get(){
        Thread curThread = Thread.currentThread();
        T value;
        if(!myThreadLocalMap.containsKey(curThread)){
            value = initialValue();
            myThreadLocalMap.put(curThread,value);
        }else {
            value = myThreadLocalMap.get(curThread);
        }
        return value;
    }

    public void remove(){
        Thread curThread = Thread.currentThread();
        if(myThreadLocalMap.containsKey(curThread)){
            myThreadLocalMap.remove(curThread);
        }
    }

    protected T initialValue(){
         return null;
    }
}
