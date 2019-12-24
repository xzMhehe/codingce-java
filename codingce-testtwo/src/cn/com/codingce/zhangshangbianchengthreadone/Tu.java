package cn.com.codingce.zhangshangbianchengthreadone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/9 13:49
 */
public class Tu {

    /**
     * notify之后,t1必须释放锁,t2退出后,也必须notify，通知t1继续执行.
     * 整个通信比较繁琐
     *
     * 使用latch(门闩)代替wait notify来进行通知
     * 好处是处理通信方式简单,同时也可以指定等待时间
     * 使用await 和countdown 方法代替wait 和notify
     * CountDownLatch不涉及锁定,当count的值为零时前线程继续运行.
     * 当不涉及同步,只是涉及线程通信的时候,用synchronized + wait/notify 就显得太重要了.
     * 这是应该考虑   countDownlatch/cyclibarrier/semaphore.
     */

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Ts t = new Ts();

        //门闩1为0的时候就开了
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
                System.out.println("t2启动");
                if (t.size() != 5) {
                    try {
                        latch.await();
                        //也可以指定时间
                      //latch.await(5000,TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
                for (int i=0; i<10; i++) {
                    t.add(new Object());
                    System.out.println("add" + i);

                    if (t.size() == 5) {
                        //打开门闩 让t2得以进行
                        latch.countDown();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"t1").start();

    }

}
