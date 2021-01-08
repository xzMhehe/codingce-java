import java.util.Scanner;

/**
 * 本实验主要是获取用户输入的两个整数，并按照从大到小的顺序输出到控制台上。
 * @author xzMa
 */
public class CodeMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        if (num1 >= num2)
            System.out.println(num1 + " " + num2);
        else
            System.out.println(num2 + " " + num1);
        sc.close();
    }
}
