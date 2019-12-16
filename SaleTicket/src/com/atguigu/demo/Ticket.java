package com.atguigu.demo;

public class Ticket implements Runnable{
    private static int i=30;
    @Override
    public void run() {
        for ( ;i>0;i--){
            synchronized ("aaa"){
                if (i>0){
                    System.out.println(Thread.currentThread().getName()+"第"+i+"张票");
                }else {
                    System.out.println("售罄");
                }
            }
        }
    }
}
