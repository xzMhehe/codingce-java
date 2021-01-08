package cn.com.codingce.zhangshangbianchengthreadone;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 10:11
 */
public class Ta {

    /**
     * synchronized 关键字
     * 对某个对象加锁
     *
     * Locks an object
     */

    private int count = 10;

    private Object o = new Object();

    public void m(){
        synchronized(o){
            count--;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
        }
    }

}
