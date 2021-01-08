package cn.com.codingce.myenum;

import java.util.Scanner;

public class MyScanner {
    public static void main(String[] args) {
        String str;
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入: ");

        if (sc.hasNextLine()) {
            str = sc.nextLine();
            System.out.println("==" + str + "==");
        }
        if (sc.hasNext()) {
            str = sc.next();
            System.out.println(str + "==");
        }

        if (sc.hasNextInt()) {
            i = sc.nextInt();
            System.out.println(i + "==");
        }
    }
}
