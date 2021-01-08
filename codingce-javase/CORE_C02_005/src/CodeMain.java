public class CodeMain {
    public static void main(String[] args) {
        int defaultNum  = 10000;
        for (int i = 0; i < 10; i++) {
            defaultNum = defaultNum / 2;
            System.out.println("第" + (i + 1) + "次反弹, 高度是: " + defaultNum);
        }
    }
}
