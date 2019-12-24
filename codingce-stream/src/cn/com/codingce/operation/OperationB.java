package cn.com.codingce.operation;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 不同类型的 Stream 流
 * 我们可以从各种数据源中创建 Stream 流，
 * 其中以 Collection 集合最为常见。如 List 和 Set 均支持 stream() 方法来创建顺序流或者是并行流。
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 9:40
 */
public class OperationB {
    public static void main(String[] args) {
        Arrays.asList("a1", "a2", "a3")
                .stream() // 创建流
                .findFirst() // 找到第一个元素
                .ifPresent(System.out::println); // 如果存在，即输出 a1

        /**
         * 您大可不必刻意地创建一个集合，再通过集合来获取 Stream 流，您还可以通过如下这种方式：
         */
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);  // a1

        // IntStreams可以使用以下方法替换常规的for循环IntStream.range()：
        IntStream.range(1, 4)
                .forEach(System.out::println);
        // 1
        // 2
        // 3

        /**
         * 上面这些原始类型流的工作方式与常规对象流基本是一样的，但还是略微存在一些区别：
         * |- 1.原始类型流使用其独有的函数式接口，例如 IntFunction代替 Function， IntPredicate代替 Predicate。
         * |- 2.原始类型流支持额外的终端聚合操作， sum()以及 average()，如下所示：
         */
        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1) // 对数值中的每个对象执行 2*n + 1 操作
                .average() // 求平均值
                .ifPresent(System.out::println);  // 如果值不为空，则输出 5.0

        /**
         * 但是，偶尔我们也有这种需求，
         * 需要将常规对象流转换为原始类型流，这个时候，
         * 中间操作 mapToInt()， mapToLong()
         * 以及 mapToDouble就派上用场了：
         */
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1)) // 对每个字符串元素从下标1位置开始截取
                .mapToInt(Integer::parseInt) // 转成 int 基础类型类型流
                .max() // 取最大值
                .ifPresent(System.out::println);  // 不为空则输出3

        /**
         * 如果说，您需要将原始类型流装换成对象流，您可以使用 mapToObj()来达到目的：
         */
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
        // a1
        // a2
        // a3

        /**
         * 下面是一个组合示例，我们将双精度流首先转换成 int 类型流，然后再将其装换成对象流：
         */
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue) // double 类型转 int
                .mapToObj(i -> "a" + i) // 对值拼接前缀 a
                .forEach(System.out::println); // for 循环打印
        // a1
        // a2
        // a3
    }
}
