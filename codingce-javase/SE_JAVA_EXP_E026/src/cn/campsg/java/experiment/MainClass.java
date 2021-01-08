package cn.campsg.java.experiment;

public class MainClass {
    public static int[] getAges() {
        int[] ages = new int[200];
        for (int i = 0; i < 200; i++) {
            int age = (int) (Math.random() * 61);
            ages[i] = age;
        }
        return ages;
    }

    public static int getAdultCount(int[] ages) {
        int adult = 0;
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] >= 18) {
                adult++;
            }
        }
        return adult;
    }

    public static void main(String[] args) {
        int[] A = new int[200];
        A = getAges();
        int num;
        num = getAdultCount(A);
        System.out.println("200个用户中，共有" + num + "个成年人");
    }
}
