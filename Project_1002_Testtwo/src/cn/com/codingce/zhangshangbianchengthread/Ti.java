package cn.com.codingce.zhangshangbianchengthread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 13:13
 */
public class Ti {

    /**
     * 一个同步的方法可以调用另外一个同步方法.一个线程已经拥有某个对象的锁,再次申请的时候仍然会得到该对象的锁.
     * 也就是说 synchronized获得的锁是可重入的.
     * 这里是继承中有可能发生的情形,子类调用父类的同步方法.
     */

    synchronized void m() {
        System.out.println("m.start");
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m.end");
    }

    public static void main(String[] args) {
        new TiTi().m();
    }

}

class TiTi extends Ti {

    @Override
    synchronized void m(){
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }

}
