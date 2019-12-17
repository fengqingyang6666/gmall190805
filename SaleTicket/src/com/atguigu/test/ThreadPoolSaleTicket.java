package com.atguigu.test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolSaleTicket {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Tickets ticket = new Tickets();
        try {
            for (int i = 1; i <= 30; i++) {
                executorService.execute(() ->{ticket.sale();});
            }
        }finally {
            executorService.shutdown();
        }
    }
}
class Tickets {
    private int i = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (i > 0) {
                System.out.println(Thread.currentThread().getName() + "第" + (i--) + "张票" + "还剩" + i + "张票");
            }
        } finally {
            lock.unlock();
        }

    }
}
