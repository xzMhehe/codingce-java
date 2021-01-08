/**
 * 本实验使用循环嵌套输出10*10的表格，每个表格内显示表格当前行列值。
 * @author xzMa
 */
public class PrintTable {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("(" + i + "," + j + ")");
            }
            System.out.println();
        }
    }
}
