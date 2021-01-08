package testthree;

public class MainClass {
    public static void main(String[] args) {
        for (int i = 100; i < 1000; i++) {
            int j = i / 100;
            int k = i % 100 / 10;
            int L = i % 10;
            if (i == j * j * j + k * k * k + (L * L * L)) {
                System.out.println("水仙花数为：" + i);
            }
        }
    }
}
