package cn.com.codingce.mashibingthread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 11:24
 */
public class Th {

    /**
     * 一个同步方法可以调用另外一个同步方法,一个线程已经拥有某个对象,再次申请的时候仍然会得到该对象的锁,
     * 也就是说synchronized 获得锁是可重入的。
     *
     * One synchronized method can call another, and a thread that already owns an object will still get its lock when it requests it again.
     * That is, synchronized acquired locks are reentrant.
     */

    synchronized void m1() {
        System.out.println("m1.start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }

}
