/**
 * 1-100之和
 * @author xzMa
 */
public class CodeMain {
    private static final int  TARGET_SUM = 100;
    public static void main(String[] args) {
        int i = 1, sum = 0;
        while (i <= TARGET_SUM) {
            sum += i;
            i++;
        }
        System.out.println("1+2+3...+99+100=" + sum);
    }
}
