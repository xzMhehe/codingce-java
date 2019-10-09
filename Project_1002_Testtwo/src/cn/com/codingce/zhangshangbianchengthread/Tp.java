package cn.com.codingce.zhangshangbianchengthread;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 16:01
 */
public class Tp {

    /**
     * 不要以字符串常量作为锁定对象
     * 在下面m1 m2 其实锁定的是同一个对象
     * 这种情况下还会发生比较诡异的现象,比如你用到了一个类库,在该类库中的代码锁定了"Hello",
     * 但是你都不到源码,所以你在自己的代码中锁定了"Hello",这时候有可能发生非常诡异的死锁阻塞,
     * 因为你的程序和你用到的类库不经意间使用了同一把锁.
     */

    String s1 = "Hello";
    String s2 = "Hello";

    void m1() {
        synchronized (s1) {

        }
    }

    void m2() {
        synchronized (s2) {

        }
    }

}
