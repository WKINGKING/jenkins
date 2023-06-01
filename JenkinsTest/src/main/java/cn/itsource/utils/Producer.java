package cn.itsource.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author wangkui
 * @date 2023-06-01 22:21
 * @description:
 * @version:
 */
public class Producer {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {
        Connection connection=ConectionUitl.getConnection();

        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "Hello World!";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}