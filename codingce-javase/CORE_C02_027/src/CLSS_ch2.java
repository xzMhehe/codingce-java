import java.util.Scanner;

/**
 * 跳数计算
 * 本实验目的是在1-100的累加过程当中，跳过个位是3的数字，输出选择性累加之和。
 * @author xzMa
 */
public class CLSS_ch2 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 10 == 3) {
                continue;
            }
            sum +=i;
        }
        System.out.println("跳过个位数字是3的数字之和: " + sum);

        getChar();
    }

    public static void getChar() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            System.out.println((int)str.charAt(i));
        }

    }
}
