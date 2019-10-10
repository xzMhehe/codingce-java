package cn.com.codingce.zhangshangbianchengthreadone;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 11:00
 */
public class Tg {

    /**
     * 对业务写方法加锁.
     * 对业务读方法不加锁.
     * 容易产生脏读问题(dirtyRead).
     * 不加两秒休眠,没有问题,加上读取存在脏读,两秒期间的代码块那里存在有可能被别的程序执行的,被非锁定的方法执行.
     * 加锁与不加锁,依据业务而定.
     *
     * Tg 为账户类  里面有  名称和余额
     *
     * 本业务解决方案  就是在读的方法上加锁
     *
     * Locks the business write method.
     * Business read methods are not locked.
     * DirtyRead is easy to produce.
     * No two seconds sleep, no problem, plus there's a dirty read on the read,
     * there's a block of code between two seconds that might be executed by some other program that's not locked.
     * Lock or no lock depends on the business.
     * Tg For the account class  It has a name and a balance
     *
     */

    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Tg t = new Tg();
        new Thread(()->t.set("掌上编程",100.0)).start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getBalance("掌上编程"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getBalance("掌上编程"));

    }

}
