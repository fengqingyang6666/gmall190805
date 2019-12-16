package com.atguigu.test;

import java.util.Scanner;

public class TestJoin {
    public static void main(String[] args) {
        ChatThread t = new ChatThread();
        t.start();

        for (int i = 1; i < 10 ;i++){
            System.out.println(i);
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(i == 5){
                try {
                    t.join();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

class ChatThread extends Thread {
    public void run(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("是否结束？Y/N");
            String in = input.next();
            if(in.equals("Y") || in.equals("y")){
                break;
            }
        }
        input.close();
    }
}