package cn.com.codingce;

public class CodeMain {
    public static void main(String[] args) {
        int i = 1, sum = 0;
        while (i <= 100) {
            if(i % 10 == 3) {
                break;
            }
            sum += i;
            i++;
        }
        System.out.println("加和1~100, 直到遇到各位数字是3的数字就中断加和操作: " + sum);
    }
}
