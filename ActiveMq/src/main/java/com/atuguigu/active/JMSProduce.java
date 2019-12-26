package com.atuguigu.active;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProduce {
    public static final String MQ_URL = "tcp://192.168.98.133:61616";
    public static final String QUEUE_NAME = "queue0805";
    public static void main(String[] args) throws JMSException {
        //1 获得ActiveMQConnectionFactory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        //2 由ActiveMQConnectionFactory获得Connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //3 启动连接准备建立会话
        connection.start();
        //4 获得Session，两个参数先用默认
        //4.1 是否开启事务
        //4.2 签收模式
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //5 获得目的地，此例是队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //6 获得消息生产者,生产什么内容？生产出来放在哪里？
        MessageProducer messageProducer = session.createProducer(queue);
        //7 生产message内容
        for (int i = 1; i <= 6; i++) {
            TextMessage textMessage = session.createTextMessage("msg*****" + i);
            messageProducer.send(textMessage);
        }
        //8 释放各种连接和资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("***********  O(∩_∩)O哈哈~");
    }
}
