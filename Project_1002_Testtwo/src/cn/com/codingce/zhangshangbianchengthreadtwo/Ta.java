package cn.com.codingce.zhangshangbianchengthreadtwo;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/9 16:15
 */
public class Ta {

    /**
     * java高并发编程设计
     * synchronizer / 同步容器 /  ThreadPool 、executor
     *
     * 一个高效的游戏服务器应该如何设计架构
     *
     * reentrantlock 用于代替synchronized
     * 本例中由于m1锁定this,只有m1执行完毕的时候m2才执行.
     * 这是复习synchronized最原始的语义.
     *
     */

    synchronized  void m1() {
        for (int i=0; i<10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        Ta t1 = new Ta();
        new Thread(t1::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t1::m2).start();
    }

}
