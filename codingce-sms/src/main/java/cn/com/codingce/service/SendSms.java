package cn.com.codingce.service;

import java.util.Map;

/**
 * @author mxz
 */
public interface SendSms {

    public boolean send(String phonNum, String templateCode, Map<String, Object> code);

}

