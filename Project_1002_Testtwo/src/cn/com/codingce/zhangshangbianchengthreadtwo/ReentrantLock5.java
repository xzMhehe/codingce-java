package cn.com.codingce.zhangshangbianchengthreadtwo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/10 9:56
 */
public class ReentrantLock5 extends Thread{

    /**
     * ReentrantLock还可以指定为公平锁
     * ReentrantLock  和synchronized  面试常问
     */

    private static ReentrantLock lock = new ReentrantLock(true);  //参数为true表示为公平锁,请对比输出结果
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {

        ReentrantLock5 r1 = new ReentrantLock5();
        Thread th1 = new Thread(r1);
        Thread th2 = new Thread(r1);
        th1.start();
        th2.start();

    }

}
