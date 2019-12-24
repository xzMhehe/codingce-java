package cn.com.codingce.zhangshangbianchengthreadtwo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/10 9:32
 */
public class ReentrantLock4 {

    /**
     * 使用ReentrantLock还可以调用lockInterruptibly，可以对线程interrupt方法做出响应.
     * 在一个线程等待锁的过程中,可以被打断.
     * 一个方法响应中断,一个方法不响应中断
     *
     */

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
           try {
               lock.lock();
               System.out.println("t1 start");
               TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
               System.out.println("t1 end");
           } catch (InterruptedException e) {
               System.out.println("interrupted");
           } finally {
               lock.unlock();
           }
        });
        t1.start();



        Thread t2 = new Thread(() -> {
            try {

                //lock.lock();

                lock.lockInterruptibly();  //可以对interrupt方法做出反应
                System.out.println("t2 statr");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            } finally {
             lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打断线程2的等待
        t2.interrupt();





    }



}
