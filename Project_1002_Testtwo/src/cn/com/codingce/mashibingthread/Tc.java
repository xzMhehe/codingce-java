package cn.com.codingce.mashibingthread;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 10:17
 */
public class Tc {

    /**
     * 任何线程执行下面代码,必须要拿到this 的锁
     * 记住锁定的时对象  不是代码块  表面看着是代码块
     *
     * Any thread that executes the following code must get the lock for this,
     * Remember that the object that is locked is not a block of code and it looks like a block of code
     */

    private int count = 10;

    public void m(){
        synchronized(this) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
        }
    }

}
