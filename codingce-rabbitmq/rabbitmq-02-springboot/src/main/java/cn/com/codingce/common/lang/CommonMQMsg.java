package cn.com.codingce.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonMQMsg implements Serializable {

    private String type;

    private String msg;
}
