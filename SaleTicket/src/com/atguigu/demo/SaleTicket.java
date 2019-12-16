package com.atguigu.demo;

public class SaleTicket{
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread th1= new Thread(ticket);
        th1.start();
        Thread th2= new Thread(ticket);
        th2.start();
        Thread th3= new Thread(ticket);
        th3.start();
    }
}
