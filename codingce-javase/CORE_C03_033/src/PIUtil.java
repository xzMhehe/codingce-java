public class PIUtil {
    private static double PI = getPI();
    private static double numOne = 1.0/5;
    private static double numTwo = 1.0/239;


    public static double getPI() {
        return 16 * Math.atan(numOne)- 4 * Math.atan(numTwo);
    }

    public static void main(String[] args) {
        System.out.println(getPI());
    }
}
