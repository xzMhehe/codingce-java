package cn.com.codingce.myenum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyRead {
    public static void main(String args[]) throws IOException {
        char c;
        BufferedReader bd = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符: ");
        do {
            c = (char) bd.read();
            System.out.println(c);
        } while (c != 'q');
    }
}
