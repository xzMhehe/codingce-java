package cn.com.codingce.zhangshangbianchengthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 14:53
 */
public class Tm {

    /**
     * 解决同样的问题的更高效的方法,使用AtomXXX类.原子类
     * AtmXXX类本身方法都是原子性的,但是不能保证多个方法调用是原子性的.
     */

    /*volatile int count = 0;*/

    AtomicInteger count = new AtomicInteger(0);

    synchronized void m(){
        for (int i=0; i<10000; i++)
            //if(count.get()<1000)             如果未加锁,之间还会有其他线程插进来
            count.incrementAndGet(); //count++
    }

    public static void main(String[] args) {

        Tm  t = new Tm();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i=0; i<10; i++) {
            threads.add(new Thread(t::m,"thread" + i));
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
