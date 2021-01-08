package cn.campsg.java.experiment;

import cn.campsg.java.experiment.entity.Bank;

import java.util.Scanner;

/**
 * @author xzMa
 */
public class MainClass {
    public static void main(String[] args) {
        long cash = 0L;
        System.out.println("请输入取款金额：");
        Scanner hehe = new Scanner(System.in);
        try {
            cash = Long.parseLong(hehe.nextLine());
            Bank bank = new Bank();
            bank.withDraw(cash);
        } catch (NumberFormatException nfe) {
            System.out.println("输入金额不正确，请重新输入取款金额.");
            hehe.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
