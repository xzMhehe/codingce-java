package cn.com.codingce.mashibingthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 16:10
 */
public class Tq {

    /**
     * 曾经的面试题
     * 实现一个容器,提供两个方案 add size
     * 写两个线程,线程添加十个元素到容器中,线程2实现监控元素个数,当个数到5个时,线程2给出提示并结束.
     */

    //添加volatile 使t2能够得到通知

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Tq t = new Tq();

        new Thread(()-> {
           for (int i=0; i<10; i++) {
               t.add(new Object());
               System.out.println("add" + i);
           }

           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        },"t1").start();

        new Thread(()-> {
           while (true) {
               if (t.size() == 5) {
                   break;
               }
           }
            System.out.println("t2结束");
        },"t2").start();

    }

}
