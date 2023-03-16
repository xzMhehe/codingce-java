package cn.com.codingce.webrtc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private String operation;
    private Object msg;

    public MessageDto setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public MessageDto setMsg(Object msg) {
        this.msg = msg;
        return this;
    }

    public String getMsgStr() {
        return msg == null ? "" : msg.toString();
    }

}
