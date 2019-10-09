package cn.com.codingce.mashibingthread;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 10:23
 */
public class Td {

    /**
     * 放在静态方法上面,由于静态没有this可以锁定,不需要new 出对象,运用了反射.
     *
     * On the static method, because static no this can be locked, do not need to new out of the object, the use of reflection.
     */

    private static int count = 10;

    private Object o = new Object();

    public synchronized static void m() {
            count--;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
    }

    public void mm() {
        synchronized(Td.class) {  //考虑一下这里写 synchronized (this)  是否可以  不可以
            count--;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
        }
    }

}
