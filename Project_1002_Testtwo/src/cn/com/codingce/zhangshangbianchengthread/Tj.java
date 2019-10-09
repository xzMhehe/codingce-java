package cn.com.codingce.zhangshangbianchengthread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 13:30
 */
public class Tj {

    /**
     * 程序在执行过程中,如果出现异常,默认状况锁会被释放
     * 所以,在并发处理过程中,有异常要多加小心,不然会发生不一致的情况,
     * 比如,在一个web app处理过程中,多个servlet线程共同访问同一个资源,这时如果异常处理不适合,
     * 在第一个线程中抛出异常,其他线程进入同步代码区,有可能访问到异常产生的数据.
     * 因此要非常小心的处理同步业务逻辑中的异常
     * 此案例 t1 锁被释放 t2 方可开始
     * 若你不想释放锁请你加入try{}catch{}
     */

    int count = 0;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + "start");
        while (true) {
            count ++;
            System.out.println(Thread.currentThread().getName() + "count: " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                //此处抛出异常,锁将被释放,要想不释放就在此处进行catch,然后循环继续.
                int i = 1/0;
            }

        }
    }

    public static void main(String[] args) {

        Tj t = new Tj();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r,"t2").start();

    }

}
