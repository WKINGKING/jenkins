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
        //1.获取连接
        Connection connection=ConectionUitl.getConnection();
        //2.从连接中建立通道
        Channel channel=connection.createChannel();
        //3.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4.消息内容
        for(int i=0;i<50;i++){
            String message="task.."+i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 2);

        }
        //5.向指定队列发送消息
        //参数：String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        /**
         * 参数明细
         * 1、queue 队列名称
         * 2、durable 是否持久化，如果持久化，mq重启后队列还在
         * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
         * 4、autoDelete 自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
         * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
         */

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}