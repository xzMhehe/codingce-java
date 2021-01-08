package cn.com.codingce.threaddemo;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 13:13
 */
public class VolatileDemo implements Runnable{

    /**
     * 添加 volatile
     * 可见性：主要强调当一个线程修改了变量的值，其他的线程是否立即感知这种变化
     * 禁止重新排序：比如两个，没有依赖关系的代码,JVM在执行的时候为了充分利用CPU的处理能力，可能会先执行下面这句
     * 也就是重排序，但是线程内是无法感知的
     * 是一种最轻量级用来进行线程安全控制的一个点，具体运用就是用它修饰一下容易被线程修改的变量。
     */

    public static volatile boolean sign = true;

    @Override
    public void run() {
        while (sign){

        }
    }
}
