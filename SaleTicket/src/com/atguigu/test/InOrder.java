package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Sequential{
    private int flag = 1;
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            if (name.equals("AA")){
                while (flag != 1){
                    c1.await();
                }
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag = 2;
                c2.signal();
            }
            if (name.equals("BB")) {
                while (flag != 2){
                    c2.await();
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag = 3;
                c3.signal();
            }
            if (name.equals("CC")) {
                while (flag != 3){
                    c3.await();
                }
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag = 1;
                c1.signal();
            }
        }finally {
            lock.unlock();
        }

    }

    /*public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }*/

}

public class InOrder {
    public static void main(String[] args) {
        //多线程的顺序调用
        Sequential sequential = new Sequential();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                try {
                    sequential.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                try {
                    sequential.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                try {
                    sequential.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}
