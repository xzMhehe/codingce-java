/**
 * 加和1~100的过程中，如果遇到个位是3的数字，则累加过程中断，输出累加值。
 * @author xzMa
 */
public class TestBreak {
    public static void main(String[] args) {
        int sum = 0, i = 0;
        for (; i < 100; i++) {
            if (i % 10 == 3) {
                break;
            }
            sum += i;
        }
        System.out.println("加和1~100, 直到遇到个位数字是3的数字中断加加操作: " + sum);
    }
}
