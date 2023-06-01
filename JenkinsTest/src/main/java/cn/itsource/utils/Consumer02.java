package cn.itsource.utils;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer02 {
    //消息队列名
    private static final String QUEUE_SMS ="queueSms";

    //交换机名
    private static final String EXCHANGE = "messageChange";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            //定义连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            //设置服务地址
            connectionFactory.setHost("127.0.0.1");
            //端口
            connectionFactory.setPort(5672);
            //1.通过工厂建立连接
            connection = connectionFactory.newConnection();
            //2.从连接中创建通道，使用通道才能完成消息等相关操作
            channel = connection.createChannel();
            //3.通道绑定交换机
            /**
             * 参数明细
             * param1:交换机名称
             * param2:交换机类型，fanout、topic、direct、headers
             */
            //Publish/subscribe发布订阅模式
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT);
            //通道绑定队列
            /**
             * 声明队列，如果Rabbit中没有此队列将自动创建
             * param1:队列名称
             * param2:是否持久化
             * param3:队列是否独占此连接
             * param4:队列不再使用时是否自动删除此队列
             * param5:队列参数
             * String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
             *
             */
            channel.queueDeclare(QUEUE_SMS,true,false,false,null);
            //5.消息内容
            String message = "Hello World!";
            // 向指定的队列中发送消息
            //参数：String exchange, String routingKey, BasicProperties props, byte[] body
            /**
             * 参数明细：
             * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
             * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
             * 3、props，消息的属性
             * 4、body，消息内容
             */
          channel.basicPublish("",QUEUE_SMS,null,message.getBytes());



            //交换机和队列绑定
            /**
             * 参数明细
             * 1、队列名称
             * 2、交换机名称
             * 3、路由key
             */
            //Publish/subscribe发布订阅模式
            channel.queueBind(QUEUE_SMS,EXCHANGE,"");
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                /**
                 * 消费者接收消息调用此方法
                 * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
                 * @param envelope 消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志
                (收到消息失败后是否需要重新发送)
                 * @param properties
                 * @param body
                 * @throws IOException
                 * String consumerTag, Envelope envelope, BasicProperties properties, byte[] body
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //交换机
                    String exchange = envelope.getExchange();
                    //路由key
                    String routingKey = envelope.getRoutingKey();
                    envelope.getDeliveryTag();
                    String msg = new String(body,"utf-8");
                    System.out.println("mq收到的消息是："+msg );
                }

            };
            System.out.println("消费者启动成功！");
            // 监听队列，第二个参数：是否自动进行消息确认。
            //参数：String queue, boolean autoAck, Consumer callback
            /**
             * 参数明细：
             * 1、queue 队列名称
             * 2、autoAck 自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为tru表示会自动回复mq，如果设置为false要通过编程实现回复
             * 3、callback，消费方法，当消费者接收到消息要执行的方法
             */
            channel.basicConsume(QUEUE_SMS,true,consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}