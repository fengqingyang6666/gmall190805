package com.atuguigu.active;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {
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
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5 获得目的地，此例是队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //6 获得消息消费者,消费什么内容？从哪里消费？
        MessageConsumer messageConsumer = session.createConsumer(queue);

        while (true){
            TextMessage message = (TextMessage)messageConsumer.receive();
//            TextMessage message = (TextMessage)messageConsumer.receive(4000);
            if (message != null){
                System.out.println("*************messageConsumer  "+message.getText());
            }else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
