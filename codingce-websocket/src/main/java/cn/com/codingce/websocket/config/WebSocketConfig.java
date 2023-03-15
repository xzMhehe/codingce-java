package cn.com.codingce.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * @author ma
 * 
 * @EnableWebSocket 开启 WebSocket 支持
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    /**
     * 必须要有的
     *
     * @return serverEndpointExporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * WebSocket 配置信息
     *
     * @return servletServerContainerFactoryBean
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean bean = new ServletServerContainerFactoryBean();

        // 文本缓冲区大小
        bean.setMaxTextMessageBufferSize(8192);
        // 字节缓冲区大小
        bean.setMaxBinaryMessageBufferSize(8192);

        return bean;
    }

}

