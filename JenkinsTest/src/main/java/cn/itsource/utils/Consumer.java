package cn.itsource.utils;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author wangkui
 * @date 2023-06-01 22:25
 * @description:
 * @version:
 */
public class Consumer {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConectionUitl.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException, UnsupportedEncodingException {
                //交换机
                String exchange = envelope.getExchange();
                //消息id，mq在channel中用来标识消息的id，可用于确认消息已接收
                long deliveryTag = envelope.getDeliveryTag();
                String msg = new String(body,"utf-8");
                System.out.println(" [x] received : " + msg + "!");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
