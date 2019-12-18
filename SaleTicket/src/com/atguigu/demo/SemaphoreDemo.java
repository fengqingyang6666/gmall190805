package com.atguigu.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //信号量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() ->{
                boolean flag = false;
                try {
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag){
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
