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
        /*
        * 1、corePoolSize：线程池中的常驻核心线程数
        * 2、maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
        * 3、keepAliveTime：多余的空闲线程的存活时间，当前池中线程数量超过corePoolSize时，
        * 当空闲时间达到keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程为止
        * 4、unit：keepAliveTime的单位（存活时间的单位）
        * 5、workQueue：任务队列，被提交但尚未被执行的任务（最多等待线程数new LinkedBlockingDeque<Runnable>(3)）
        * 6、threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程，
        * 一般默认的即可（即Executors.defaultThreadFactory()）
        * 7、handler：拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）
        * 时如何来拒绝请求执行的runnable的策略
         * */

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
