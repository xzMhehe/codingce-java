package cn.com.codingce.zhangshangbianchengthreadtwo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/9 16:46
 */
public class ReentrantLock2 {

    /**
     * reentrantlock 用于代替synchronized
     * 本例中由于m1锁定this,只有m1执行完毕的时候m2才执行.
     * 这是复习synchronized最原始的语义.
     *
     * 使用reentrantLock可以完成同样功能
     * 需要注意的是,必须要必须要必须要手动释放锁(重要的事情说三遍)
     * 使用synchronized锁定的话如果遇到异常,jvm就会自动释放锁,但是lock必须手动释放,因此经常在finally中进行锁的释放
     */

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();  // 相当于 synchronized(this)
            for (int i=0; i<10; i++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2...");
        lock.unlock();
    }

    public static void main(String[] args) {

        ReentrantLock2 r1 = new ReentrantLock2();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();

    }

}
