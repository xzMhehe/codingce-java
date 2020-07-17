package cn.com.codingce.utils;

import org.junit.Test;

import java.util.UUID;

@SuppressWarnings("all")    //抑制警告
public class IdUtils {
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Test
    public void test() {
        System.out.println(IdUtils.getId());
    }
}
