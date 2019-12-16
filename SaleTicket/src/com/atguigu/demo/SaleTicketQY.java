package com.atguigu.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketQY {
    public static void main(String[] args) {
        Tickets ticket = new Tickets();

        new Thread(() ->{ for (int i = 1; i <30 ; i++)ticket.sale();},"a").start();
        new Thread(() ->{ for (int i = 1; i <30 ; i++)ticket.sale();},"b").start();
        new Thread(() ->{ for (int i = 1; i <30 ; i++)ticket.sale();},"c").start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i < 30;i++) {
                    ticket.sale();
                }
            }
        },"aaa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i < 30;i++) {
                    ticket.sale();
                }
            }
        },"bbb").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i < 30;i++) {
                    ticket.sale();
                }
            }
        },"ccc").start();*/
    }
}

class Tickets{
    private int i = 30;
    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if(i>0){
                System.out.println(Thread.currentThread().getName()+"第"+(i--)+"张票"+"还剩"+i+"张票");
            }
        }finally {
            lock.unlock();
        }

    }

    /*public synchronized void sale(){
        if(i>0){
                System.out.println(Thread.currentThread().getName()+"第"+(i--)+"张票"+"还剩"+i+"张票");
            }
    }*/
}
