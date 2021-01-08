package cn.campsg.java.experiment;

import java.util.Scanner;

public class TestPost {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入包裹的数据：");
        System.out.println("输入包裹的重量(kg)：");
        float weight = sc.nextFloat();
        System.out.println("输入包裹长度(cm)：");
        float length = sc.nextFloat();
        System.out.println("输入包裹宽度(cm)：");
        float width = sc.nextFloat();
        System.out.println("输入包裹高度(cm)：");
        float height = sc.nextFloat();
        if (checkPost(weight, length, width, height)) {
            System.out.println("邮寄重量是：" + weight + "（kg）");
            System.out.println("邮寄费用是：" + getMoney(weight) + "元");
        } else {
            System.out.println("该包裹不符邮寄规定！");
        }
    }

    public static boolean checkPost(float weight, float length, float width, float height) {
        if (weight > 200 || length > 200 || width > 200 || height > 200)
            return false;
        return true;
    }

    public static double getMoney(double weight) {
        if(weight <= 100) {
            return 2 + (weight - 1) * 0.8;
        } else if (weight <= 150) {
                return 2 + (weight - 1) * 0.75;
        } else {
            return 2 + (weight - 1) * 0.70;
        }
    }
}
