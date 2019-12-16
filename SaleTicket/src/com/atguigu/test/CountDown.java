package com.atguigu.test;

public class CountDown {
    //倒计时
    public static void main(String[] args) {
        for (int i = 10; i > 0; i-- ){
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("再见2019，你好2020");
    }
}
