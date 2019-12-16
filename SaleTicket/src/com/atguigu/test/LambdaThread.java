package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LambdaThread {
    public static void main(String[] args) {
        Count count = new Count();

        new Thread(() ->{
            for (int i = 0; i <= 10; i++) {
                try {
                    count.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i <= 10; i++) {
                try {
                    count.red();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i <= 10; i++) {
                try {
                    count.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() ->{
            for (int i = 0; i <= 10; i++) {
                try {
                    count.red();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class Count{
    private int i=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (i != 0){
                condition.await();
            }
            ++i;
            System.out.println(Thread.currentThread().getName()+"   "+i);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void red() throws InterruptedException {
        lock.lock();
        try {
            while (i == 0){
                condition.await();
            }
            --i;
            System.out.println(Thread.currentThread().getName()+"   "+i);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /*public synchronized void add() throws InterruptedException {
        while (i != 0){
            this.wait();
        }
        ++i;
        System.out.println(Thread.currentThread().getName()+"   "+i);
        this.notifyAll();
    }

    public synchronized void red() throws InterruptedException {
        while (i == 0){
            this.wait();
        }
        --i;
        System.out.println(Thread.currentThread().getName()+"   "+i);
        this.notifyAll();
    }*/
}