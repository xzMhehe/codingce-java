package cn.com.codingce.zhangshangbianchengthreadtwo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/10 9:06
 */
public class ReentrantLock3 {

    Lock lock = new ReentrantLock();

    void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();  // 相当于  synchronized(this)
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 使用reentrantlock可以进行尝试锁定,tryLock,这样无法锁定,或者在指定时间内无法锁定,线程可以决定是否继续等待.
     * 现在的 synchronized在以前比较重的,效率比较低,到后来做优化了,底层做了很强的优化,所以synchronized现在的效率已经很稳定了,基本没区别,
     * reentrantlock更灵活
     *
     *
     * 使用tryLock 尝试锁定,不管锁定与否,方法都将执行,
     * 可以根据tryLock的的时间,由于tryLock(time) 抛出异常,所以注意unLock的处理,必须放到finally中,
     *
     */

    void m2() {

        /**
         * 两种方式
         * boolean locked = lock.tryLock();
         * System.out.printlm("m2..." + locked);
         * if(locked)
         */

        boolean locked = false;

        try {
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("m2..." + locked);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }

    }

    public static void main(String[] args) {

        ReentrantLock3 r1 = new ReentrantLock3();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();

    }

}
