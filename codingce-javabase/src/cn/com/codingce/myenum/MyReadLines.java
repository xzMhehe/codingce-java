package cn.com.codingce.myenum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyReadLines {
    public static void main(String[] args) throws IOException {
        String str;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do {
            str = bf.readLine();
            System.out.println(str);
        } while (!str.equals("end"));
    }
}
