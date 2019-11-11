package cn.com.codingce.operation;

import java.util.stream.Stream;

/**
 * 中间操作顺序这么重要？
 * 数据多的情况下，中间操作顺序的重要性。
 * @author 2460798168@qq.com
 * @date 2019/11/11 10:15
 */
public class OperationD {
    public static void main(String[] args) {
        /**
         * 下面的例子由两个中间操作 map和 filter，
         * 以及一个终端操作 forEach组成。
         * 让我们再来看看这些操作是如何执行的：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A"); // 过滤出以 A 为前缀的元素
                })
                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出
        // map:     d2
        // filter:  D2
        // map:     a2
        // filter:  A2
        // forEach: A2
        // map:     b1
        // filter:  B1
        // map:     b3
        // filter:  B3
        // map:     c
        // filter:  C

        /**
         * 学习了上面一小节，您应该已经知道了，
         * map和 filter会对集合中的每个字符串调用五次，
         * 而 forEach却只会调用一次，因为只有 "a2" 满足过滤条件。
         * 如果我们改变中间操作的顺序，
         * 将 filter移动到链头的最开始，就可以大大减少实际的执行次数：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a"); // 过滤出以 a 为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出
        // filter:  d2
        // filter:  a2
        // map:     a2
        // forEach: A2
        // filter:  b1
        // filter:  b3
        // filter:  c

        /**
         * 现在， map仅仅只需调用一次，
         * 性能得到了提升，这种小技巧对于流中存在大量元素来说，
         * 是非常很有用的。
         * 接下来，让我们对上面的代码再添加一个中间操作 sorted：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2); // 排序
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a"); // 过滤出以 a 为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出
        /**
         * sorted 是一个有状态的操作，因为它需要在处理的过程中，
         * 保存状态以对集合中的元素进行排序。
         *
         * 执行此示例将得到以下控制台输出：
         * ort:     a2; d2
         * sort:    b1; a2
         * sort:    b1; d2
         * sort:    b1; a2
         * sort:    b3; b1
         * sort:    b3; d2
         * sort:    c; b3
         * sort:    c; d2
         * filter:  a2
         * map:     a2
         * forEach: A2
         * filter:  b1
         * filter:  b3
         * filter:  c
         * filter:  d2
         *
         * 咦咦咦？这次怎么又不是垂直执行了。
         * 你需要知道的是， sorted是水平执行的。因此，在这种情况下，
         * sorted会对集合中的元素组合调用八次。
         * 这里，我们也可以利用上面说道的优化技巧，
         * 将 filter 过滤中间操作移动到开头部分：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // filter:  d2
        // filter:  a2
        // filter:  b1
        // filter:  b3
        // filter:  c
        // map:     a2
        // forEach: A2
        /**
         * 从上面的输出中，我们看到了 sorted从未被调用过，
         * 因为经过 filter过后的元素已经减少到只有一个，这种情况下，是不用执行排序操作的。因此性能被大大提高了。
         */
    }
}
