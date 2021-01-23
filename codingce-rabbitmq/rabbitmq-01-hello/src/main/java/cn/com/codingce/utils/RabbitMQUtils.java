package cn.com.codingce.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author mxz
 */
public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    // 重量级资源  类加载执行一次(即可)
    static {
        // 创建连接 mq 的连接工厂
        connectionFactory = new ConnectionFactory();
        // 设置 rabbitmq 主机
        connectionFactory.setHost("127.0.0.1");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/codingce");
        // 设置访问虚拟主机用户名密码
        connectionFactory.setUsername("codingce");
        connectionFactory.setPassword("123456");
    }

    /**
     * 定义提供连接对象的方法
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 关闭通道和关闭连接工具方法
     *
     * @param connection
     * @param channel
     */
    public static void closeConnectionAndChannel(Channel channel, Connection connection) {
        try {
            // 先关 channel
            if (channel != null)
                channel.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
