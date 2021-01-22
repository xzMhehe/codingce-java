package cn.com.codingce.direct;

import cn.com.codingce.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * <p>
 * 直连模式
 *
 * @author mxz
 */
@Component
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 获取连接中通道
        Channel channel = connection.createChannel();

        // 通道绑定消息队列
        // 参数1 队列的名称, 如果不存在则自动创建
        // 参数2 用来定义队列是否需要持久化, true 持久化队列(mq关闭时, 会存到磁盘中) false 不持久化(关闭即失)
        // 参数3 exclusive 是否独占队列   true 独占队列  false 不独占
        // 参数4 autoDelete 是否在消费后自动删除队列  true 自动删除   false 不删除
        // 参数5 额外的附加参数
        channel.queueDeclare("hello", false, false, false, null);

        // 发布消息

        // 参数1 交换机名称
        // 参数2 队列名称
        // 参数3 传递消息额外设置
        // 参数4 消息的具体内容
        channel.basicPublish("", "hello", null, "hello rabbitMQ".getBytes());

        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
