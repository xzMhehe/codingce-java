package cn.com.codingce.zhangshangbianchengthread;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 10:21
 */
public class Tb {

    /**
     * 等同于在方法的代码执行时要synchronized
     *
     * Is equivalent to synchronized when the method's code executes.
     */

    private int count = 10;

    private Object o = new Object();

    public synchronized void m() {
            count--;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
    }

}
