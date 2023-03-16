# 【Java】Spring Boot整合WebSocket

## WebSocket简介

WebSocket是一种协议，用于实现客户端和服务器之间的双向通信。它可以在单个TCP连接上提供全双工通信，避免了HTTP协议中的请求-响应模式，从而实现更高效的数据交换。WebSocket协议最初由HTML5规范提出，现在已成为一种通用的网络协议，被广泛用于Web应用程序中。

WebSocket协议的主要特点包括：

1. 建立在TCP上：WebSocket协议使用单个TCP连接进行全双工通信，避免了HTTP协议中的多次连接建立和断开操作，从而减少了网络延迟和带宽消耗。
2. 双向通信：WebSocket协议支持双向通信，即客户端和服务器可以同时向对方发送和接收数据，实现更加灵活和高效的数据交换。
3. 实时性：WebSocket协议可以实现实时通信，支持消息推送、实时聊天等功能，为Web应用程序带来更好的用户体验。
4. 协议标准化：WebSocket协议已经被标准化，并且被广泛支持，几乎所有的现代浏览器都支持WebSocket协议。

WebSocket协议在Web应用程序中广泛使用，例如实现实时通信、在线游戏、即时消息等功能。开发者可以使用JavaScript编写客户端代码，使用Java、Node.js等语言编写服务器端代码，实现WebSocket协议的双向通信。

## Pom
本次Spring Boot版本 `2.7.8`，WebSocket 版本 `5.3.25`.

**parent**

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.8</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

**dependency**

```xml
<!--WebSocket -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
<!--...-->
```

## 配置文件

WebsocketConfig

```java
package cn.com.codingce.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
// 开启 WebSocket 支持
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
```

## 使用

WebSocket 的注解：

| 注解            | 作用                                           | 备注                                             |
| --------------- | ---------------------------------------------- | ------------------------------------------------ |
| @ServerEndpoint | 用于声明WebSocket响应类，有点像@RequestMapping | @ServerEndpoint(“/websocket”)                    |
| @OnOpen         | WebSocket连接时触发                            | 参数有：Session session, EndpointConfig config   |
| @OnMessage      | 有消息时触发                                   | 参数很多，一会再说                               |
| @OnClose        | 连接关闭时触发                                 | 参数有：Session session, CloseReason closeReason |
| @OnError        | 有异常时触发                                   | 参数有：Session session, Throwable throwable     |

## Controller

**BaseWebSocketController**

```java
package cn.com.codingce.demo.conrtoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ma
 */
@Controller
@ServerEndpoint("/websocket")
public class BaseWebSocketController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // ConcurrentHashMap, 保证线程安全, static全局共享 session

    // 这里之所以static，是因为这个类不是单例的！！
    // 它虽然有@Controller注解，但是不适用Ioc容器中拿对象，每一次请求过来都是一个新的对象

    /**
     * 存放 session
     */
    private final static Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * OnOpen 在连接创建(用户进入聊天室)时触发
     *
     * @param session session
     * @param config  config
     */
    @OnOpen
    public void openSession(Session session, EndpointConfig config) {
        // 将session存起来, 用于服务器向浏览器发送消息
        SESSION_MAP.put(session.getId(), session);
        String res = "OnOpen [" + session.getId() + "]进入房间";
        sendAll(res);
        logger.info(res);
    }


    /**
     * 响应字符串
     *
     * @param session session
     * @param message message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        String res = "OnMessage [" + session.getId() + "]" + message;
        sendAll(res);
        logger.info(res);
    }

    /**
     * 响应字节流
     *
     * @param session session
     * @param message message
     */
    @OnMessage
    public void onMessage(Session session, byte[] message) {
        // 这个以后再说
    }

    /**
     * OnClose 在连接断开(用户离开聊天室)时触发
     *
     * @param session     session
     * @param closeReason closeReason
     */
    @OnClose
    public void closeSession(Session session, CloseReason closeReason) {
        //记得移除相对应的session
        SESSION_MAP.remove(session.getId());

        String res = "OnClose [" + session.getId() + "]离开了房间";
        sendAll(res);
        logger.info(res);
    }

    @OnError
    public void sessionError(Session session, Throwable throwable) {
        // 通常有异常会关闭 session
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sendAll
     *
     * @param message message
     */
    private void sendAll(String message) {
        for (Session s : SESSION_MAP.values()) {
            // 获得session发送消息的对象
            // Basic是同步, 会阻塞
            // Async是异步, 这个会有多线程并发导致异常, 发送消息太快也会有并发异常, 需要有 消息队列 来辅助使用
            final RemoteEndpoint.Basic remote = s.getBasicRemote();

            try {
                // 发送消息
                remote.sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
```

## 前端

**conrtoller**

```java
package cn.com.codingce.demo.conrtoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String helloThymeleaf(Model model) {
        return "index";
    }

}
```

**web**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>websocket-demo</title>

    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div class="container py-3">

    <div class="row">

        <div class="col-6">
            <div>
                <label for="messageArea">聊天信息:</label>
            </div>
            <div>
                <textarea id="messageArea" readonly class="w-100" style="height: 75vh;"></textarea>
            </div>
        </div>

        <div class="col">

            <div class="my-1">
                <label for="messageArea">用 户 名:</label>
            </div>

            <div class="my-1">
                <input type="text" id="username" autocomplete="off">
            </div>

            <div class="my-1">
                <button class="btn-info" id="joinRoomBtn">进入聊天室</button>
                <button class="btn-warning" id="leaveRoomBtn">离开聊天室</button>
            </div>

            <hr/>

            <div class="my-1">
                <label for="sendMessage">输入消息:</label>
            </div>
            <div>
                <textarea id="sendMessage" rows="5" class="w-100" style="max-height: 50vh"></textarea>
            </div>

            <div class="my-1">
                <button class="btn-primary" id="sendBtn">发送消息</button>
            </div>

        </div>

    </div>

</div>
]

<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>

<script>
    let webSocket;
    // ip和端口号用自己项目的
    // {websocket}: 其实是刚刚那个@ServerEndpoint("/websocket")中定义的
    let url = 'ws://127.0.0.1:8091/websocket';

    $('#username').keyup(function (e) {
        let keycode = e.which;
        if (keycode === 13) {
            $('#joinRoomBtn').click();
        }
    });

    // 进入聊天室
    $('#joinRoomBtn').click(function () {
        let username = $('#username').val();
        webSocket = new WebSocket(url);
        webSocket.onopen = function () {
            console.log('webSocket连接创建。。。');
        }
        webSocket.onclose = function () {
            console.log('webSocket已断开。。。');
            $('#messageArea').append('websocket已断开\n');
        }
        webSocket.onmessage = function (event) {
            $('#messageArea').append(event.data + '\n');
        }
        webSocket.onerror = function (event) {
            console.log(event)
            console.log('webSocket连接异常。。。');
        }
    });

    // 退出聊天室
    $('#leaveRoomBtn').click(function () {
        if (webSocket) {
            // 关闭连接
            webSocket.close();
        }
    });

    // 发送消息
    $('#sendBtn').click(function () {
        var msg = $('#sendMessage').val();
        if (msg.trim().length === 0) {
            alert('请输入内容');
            return;
        }
        webSocket.send($('#sendMessage').val());

        $('#sendMessage').val('');
    });

</script>

</body>
</html>
```



## 测试

**页面**

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/static/img/gf/20230305172308.png)

**用户一**：

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/static/img/gf/20230305173315.png)

**用户二**：

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/static/img/gf/20230305173412.png)

**用户一收到的信息**：

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN@main/static/img/gf/20230305173455.png)

## 欢迎关注

关注我不迷路  **后端码匠**

更多精彩关注**后端码匠**公众号,更多资源等你来发掘

期待与你一起进步😋

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img/202108311552149.png)
