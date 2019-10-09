package cn.com.codingce.mashibingthread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/8 13:52
 */
public class Tk {

    /**
     * volatile 关键字,使一个变量在多个线程之间可见
     * A B 线程都用到一个变量,java默认是A线程中保留一份copy,这样如果B线程修改了该变量,则A线程未必知道.
     * 使用volatile 关键字,会让所有线程都会读到变量值的修改.
     *
     * 在下面代码中,running是存在于堆内存的t对象中,
     * 当线程t1开始运行的时候,会把running的值从内存中读到t1线程的工作区,在运行过程中,直接使用copy,
     * 并不是每次都去使用colatile,将会强制所有线程去堆内存中读取running的值
     *
     * volatile 并不能保证多个线程共同修改running变量时所带来的一直问题,也就说volatile不能代替synchronized
     *
     */

    //对比一下有无volatitle ,整个程序运行结果的区别
    volatile boolean running = true;
    void m(){
        System.out.println("m statr");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        Tk t = new Tk();

        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;

    }

}
