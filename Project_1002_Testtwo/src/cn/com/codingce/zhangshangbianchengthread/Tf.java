package cn.com.codingce.zhangshangbianchengthread;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 10:37
 */
public class Tf {

    /**
     * m1不影响m2,同步方法不影响其他方法,m2 不需要锁.
     *
     * M1 does not affect m2, synchronous methods do not affect other methods, and m2 does not need a lock.
     */

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "m1.start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1.end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m2");
    }

    public static void main(String[] args) {
        Tf t = new Tf();

        new Thread(()->t.m1()).start();
        new Thread(()->t.m2()).start();

        /*new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();*/

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        });*/

    }

}
