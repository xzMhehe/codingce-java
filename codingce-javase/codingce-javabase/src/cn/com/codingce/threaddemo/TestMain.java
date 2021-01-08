package cn.com.codingce.threaddemo;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 11:57
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {

        ThreadDemo td = new ThreadDemo();

        Thread t1 = new Thread(td,"掌上编程");
        Thread t2 = new Thread(td,"哈哈哈");
        t1.start();
        t2.start();


    }
}
