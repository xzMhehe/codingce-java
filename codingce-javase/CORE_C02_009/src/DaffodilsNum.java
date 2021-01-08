import java.util.Scanner;

/**
 * 本实验要求：按用户输入的数值，判断该数值是否为“水仙花数”。
 * @author xzMa
 */
public class DaffodilsNum {
    public static void main(String[] args) {
        for (int i = 100; i < 1000; i++) {
            if (i == (Math.pow(i % 10, 3) + Math.pow(i / 10 % 10, 3) + Math.pow(i / 100, 3))) {
                System.out.println("水仙花数: " + i);
            }
        }
    }
}
