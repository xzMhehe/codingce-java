import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数: ");
        int oneNum = sc.nextInt();
        System.out.println("请输入第二个数: ");
        int twoNum = sc.nextInt();
        System.out.println("两个数的差值为: " + Math.abs(oneNum - twoNum));
        sc.close();
    }
}
