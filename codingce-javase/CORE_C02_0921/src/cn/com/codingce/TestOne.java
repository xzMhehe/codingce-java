package cn.com.codingce;

import java.util.Scanner;

public class TestOne {
    public static void main(String[] args) {
        int[] array = new int[5];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(i<5) {
            System.out.println("请输入第" + (i + 1) + "个数字:  ");
            int num = sc.nextInt();
            array[i] = num;
            i++;
        }
        sc.close();
        StringBuffer result = new StringBuffer("输入的数字中为偶数的是: ");
        for(int j : array) {
            if(j % 2 == 0) {
                result.append(j + " ");
            }
        }
        System.out.println(result);
    }
}
