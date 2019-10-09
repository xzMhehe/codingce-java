package cn.com.codingce.zhangshangbianchengthread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/9 10:11
 */
public class Tr {

    /**
     * 继上
     * 但是,t2线程死循环很浪费cpu,如果不用死循环,该怎么做呢?
     * 待做
     */

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Tq t = new Tq();
        final Object lock = new Object();

        new Thread(()-> {
            synchronized (lock){
                System.out.println("t2启动");
                if (t.size()!=5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }




    });

    }

}
