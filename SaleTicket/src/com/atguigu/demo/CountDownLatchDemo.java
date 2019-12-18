package com.atguigu.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);//做减法
        for (int i = 1; i <= 5; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                latch.countDown();
            },String.valueOf(i)).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长关门");
    }
}
