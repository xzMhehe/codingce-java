package cn.com.codingce.zhangshangbianchengthreadthree;

import java.util.LinkedList;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/16 16:35
 */
public class MyContainera<T> {

    /**
     * 面试题：写一个固定容器同步容器 拥有 put和get方法,以及getCount 方法,
     * 能支持两个生产者线程以及10个消费者线程的阻塞使用
     *
     * 使用wait和notify/notifyAll来实现.
     *
     */
    final private LinkedList<T> lists = new LinkedList<T>();
    final private int MAX = 0;
    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            /**
             * 想想为什么用while 不用 if?
             */
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lists.add(t);
            ++count;
            /**
             * 通知消费者线程进行消费
             */
            this.notifyAll();
        }


    }

}
