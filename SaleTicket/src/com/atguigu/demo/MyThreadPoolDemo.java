package com.atguigu.demo;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);//1线程池5线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();//1线程池1线程
//        ExecutorService executorService = Executors.newCachedThreadPool();//1线程池N线程
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            for (int i = 1; i <= 12; i++) {
                final int tempI = i;
                executorService.execute(() ->{System.out.println(Thread.currentThread().getName()+"\t"+tempI);});
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
