package cn.com.codingce.mashibingthread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 15:06
 */
public class Tn {

    /**
     * synchonized优化.
     * 同步代码块中的语句越少越好.
     * 比较m1和m2
     */

    int count = 0;
    synchronized void m1() {

        //do sth need sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要synchronized,这时不应该给整个方法上都上锁
        count++;

        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void m2() {
        //do sth need not sync
        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync 时不应该给整个方法上锁
        //采用细粒度的锁,可以使线程争用时间变短,从而提高效率  细粒度锁要比粗粒度锁效率要高
        synchronized (this) {
            count++;
        }
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //do sth not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
