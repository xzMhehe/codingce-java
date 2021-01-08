package cn.com.codingce.streamdemo;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/22 15:30
 */
public class DemoA {

    /**
     * 当我第一次阅读 Java8 中的 Stream API 时，
     * 说实话，我非常困惑，因为它的名字听起来与 Java I0 框架中的 InputStream 和 OutputStream 非常类似。但是实际上，它们完全是不同的东西。
     * Java8 Stream 使用的是函数式编程模式，如同它的名字一样，它可以被用来对集合进行链状流式的操作。
     * 我们还将学习终端操作 API reduce， collect 以及 flatMap的详细介绍，最后我们再来深入的探讨一下 Java8 并行流。
     *
     *
     *
     * 源自https://mp.weixin.qq.com/s?lang=zh_CN&mid=2247483784&sn=ba63e1f6f2f9c9d9ea32f731ab44ce22&idx=1&token=259766333&__biz=MzU4MDUyMDQyNQ%3D%3D&chksm=fd54d10eca23581837c5df0ae5ee585d96f84ea53212efc572f555b56a901e4d88f0d3958de5#rd
     */

    public static void main(String[] args) {

        List<String> mylist =
                Arrays.asList("a1","a2","b3","c4");

        mylist
                .stream() //创建流
                .filter(s -> s.startsWith("c"))   //执行过滤,过滤以c为前缀的字符串
                .map(String::toUpperCase)   //转换成大写                               中间操作
                .sorted()  //排序
                .forEach(System.out::println); //for循环            终端操作



    }
}
