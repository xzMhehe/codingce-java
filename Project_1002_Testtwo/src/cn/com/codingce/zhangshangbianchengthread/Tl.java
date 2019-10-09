package cn.com.codingce.zhangshangbianchengthread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 14:36
 */
public class Tl {

    /**
     * volatile 并不能保证多个线程共同修改running 变量 时所带来的不一致问题,也就是说volatile 不能代替synchronized
     * 运行下面的程序,分析结果
     * 创建10个线程
     *
     * volatile 和 synchronized 的区别
     * volatile  保证可见性并不保证原子性;synchronized 既保证可见性又保证原子性;
     * synchronized的效率要比volatile低不少  面试必出
     */

    volatile int count = 10;
    void m() {
        for (int i=0; i<10000;i++) count++;
    }

    public static void main(String[] args) {
        Tl t = new Tl();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i=0; i<10; i++) {
            threads.add(new Thread(t::m, "thread" + i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }


}
