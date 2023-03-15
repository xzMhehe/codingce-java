package cn.com.codingce.websocket.conrtoller;

import cn.com.codingce.websocket.entity.dto.MessageDto;
import com.alibaba.fastjson.JSONObject;
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
        // 使用 fastjson 解析 json 字符串
        final MessageDto data = JSONObject.parseObject(message, MessageDto.class);
        // 响应的信息
        final MessageDto response = MessageDto.builder()
                // 将请求的 operation 放入
                .operation(data.getOperation())
                .build();
        // 根据不同的 operation 执行不同的操作
        switch (data.getOperation()) {
            // 进入聊天室后保存用户名
            case "tip":
                session.getUserProperties().put("username", data.getMsg());
                SESSION_MAP.put(session.getId(), session);
                response.setMsg("[" + data.getMsg() + "]进入房间");
                sendAll(JSONObject.toJSONString(response));
                break;
            // 发送消息
            case "msg":
                final String username = (String) session.getUserProperties().get("username");
                response.setMsg("[" + username + "]" + data.getMsg());
                sendAll(JSONObject.toJSONString(response));
                break;
            default:
                break;
        }
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


