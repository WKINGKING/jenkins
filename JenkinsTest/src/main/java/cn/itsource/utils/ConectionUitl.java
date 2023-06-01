package cn.itsource.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

/**
 * @author wangkui
 * @date 2023-06-01 22:16
 * @description: 连接工具类
 * @version:
 */
public class ConectionUitl {

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("127.0.0.1");
        //端口
        connectionFactory.setPort(5672);
        Connection connection=connectionFactory.newConnection();
        return connection;
    }
}
