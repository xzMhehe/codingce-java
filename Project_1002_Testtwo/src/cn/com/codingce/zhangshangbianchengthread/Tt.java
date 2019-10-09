package cn.com.codingce.zhangshangbianchengthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/9 13:12
 */
public class Tt {

    /**
     * 这里是当size=5时,t1线程等,释放锁同时叫醒t2,t2执行,t2执行结束,叫醒t1执行
     */

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Ts t = new Ts();

        final Object lock = new Object();

        new Thread(() -> {
            synchronized(lock) {
                System.out.println("t2启动");
                if (t.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                //通知t1继续执行·
                lock.notify();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized(lock) {
                for (int i=0; i<10; i++) {
                    t.add(new Object());
                    System.out.println("add" + i);

                    if (t.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();

    }

}
