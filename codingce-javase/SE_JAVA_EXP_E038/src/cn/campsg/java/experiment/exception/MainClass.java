package cn.campsg.java.experiment.exception;

import cn.campsg.java.experiment.Rose;

import java.util.Scanner;


/**
 * @author xzMa
 */
public class MainClass {
    public static void main(String[] args) {
        Scanner haha = new Scanner(System.in);
        System.out.println("你想送女朋友多少朵玫瑰（数字）：");
        Rose rose = new Rose(100);
        try {
            int userInput = haha.nextInt();
            rose.giveRose(userInput);
        } catch (RoseException e) {
            System.out.println(e.getMessage());
        } finally {
            haha.close();
            System.out.println("无论如何，我都是爱你的！\n");
        }
    }
}
