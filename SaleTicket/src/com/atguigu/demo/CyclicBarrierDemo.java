package com.atguigu.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //CyclicBarrier循环屏障做加法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("***********集齐龙珠，召唤神龙");});
            for (int i = 1; i <= 7; i++) {
                final int tempI = i;
                new Thread(() ->{
                    System.out.println(Thread.currentThread().getName()+"收集到第"+tempI+"颗龙珠");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                },String.valueOf(i)).start();
            }

    }
}
