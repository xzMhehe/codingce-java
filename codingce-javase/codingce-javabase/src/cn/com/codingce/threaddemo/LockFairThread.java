package cn.com.codingce.threaddemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 15:25
 */
public class LockFairThread implements Runnable{

    //创建公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "获得锁");
        }catch (Exception e){
            System.out.println("异常相关提示");
        }finally {
            lock.unlock();
        }
    }

}
