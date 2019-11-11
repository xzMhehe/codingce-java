package cn.com.codingce.operation;

import java.util.Arrays;
import java.util.List;

/**
 * Stream 流是如何工作的？
 * 流表示包含着一系列元素的集合，
 * 我们可以对其做不同类型的操作，
 * 用来对这些元素执行计算。
 * 请不要行尾注释 本注释仅仅为了代码可读性
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 9:31
 */
public class OperationA {
    public static void main(String[] args) {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1" ,"c1", "c2");
        myList
                .stream()
                .filter(s -> s.startsWith("c")) // 执行过滤，过滤出以 c 为前缀的字符串
                .map(String::toUpperCase) // 转换成大写
                .sorted()
                .forEach(System.out::println); // for 循环打印
        // c1
        // c2
    }
}
