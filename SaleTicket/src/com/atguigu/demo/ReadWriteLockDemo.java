package com.atguigu.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCache{
    private volatile Map<String,String> map = new HashMap<>();

    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key,String value){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t"+"写入结束");
        }finally {
            rwl.writeLock().unlock();
        }
    }

    public synchronized void get(String key){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t"+"读取结束");
        }finally {
            rwl.readLock().unlock();
        }

    }
}