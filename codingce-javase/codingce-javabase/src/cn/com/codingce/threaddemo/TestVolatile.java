package cn.com.codingce.threaddemo;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 13:15
 */
public class TestVolatile {
    public static void main(String[] args) {

        VolatileDemo vd = new VolatileDemo();

        Thread thread = new Thread(vd);
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VolatileDemo.sign = false;

    }
}
