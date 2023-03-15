package cn.com.codingce.websocket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String operation;
    private String msg;

    public MessageDto setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public MessageDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}

