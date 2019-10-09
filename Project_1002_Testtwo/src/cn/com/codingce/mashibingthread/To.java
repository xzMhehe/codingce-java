package cn.com.codingce.mashibingthread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 15:27
 */
public class To {

    /**
     * 锁定某个对象o,如果o的属性发生改变,不影响使用.
     * 但是如果o变成另外一个对象,则锁定的对象发生改变.
     * 应该避免将锁定对象的引用变成另外对象
     */

    Object o = new Object();

    void m() {
        synchronized (o) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
       To t = new To();
       //启动线程
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(t::m,"t2");
        //锁定对象发生变化,所以t2线程得以进行,如注释掉这句话,线程2将永远得不到执行机会
        //锁是锁在堆内存  不是锁在栈内存
        t.o = new Object();

        t2.start();

    }

}
