package cn.com.codingce.zhangshangbianchengthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/9 11:48
 */
public class Ts {

    /**
     * 这里使用wait 和notify做到,wait会释放锁,而notify不会释放锁
     * 需要注意的是这种方法必须保证t2先执行,也就是让t2监听才可以.
     * 阅读下面的程序，并分析输出结果.
     * 可以读到输出结果并不是size=5 t2退出,而是t1结束时t2才可以接收到通知推出
     * 思考为什么
     *
     * wait 是调用被锁定对象的wait方法 notify
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
