package cn.com.codingce.operation;

import java.util.stream.Stream;

/**
 * Stream 流的处理顺序
 * 上小节中，我们已经学会了如何创建不同类型的 Stream 流，
 * 接下来我们再深入了解下数据流的执行顺序。
 * 在讨论处理顺序之前，您需要明确一点，
 * 那就是中间操作的有个重要特性 —— 延迟性。
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 9:59
 */
public class OperationC {
    public static void main(String[] args) {
        /**
         * 下面这个没有终端操作的示例代码：
         * 执行此代码段时，
         * 您可能会认为，将依次打印 "d2", "a2", "b1", "b3", "c" 元素。
         * 然而当你实际去执行的时候，它不会打印任何内容。
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        /**
         * 为什么呢？
         * ---> 原因是： 当且仅当存在终端操作时，中间操作操作才会被执行。 <---
         * 是不是不信？接下来，对上面的代码添加 forEach终端操作：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /**
         * 再次执行，我们会看到输出如下：
         * filter:  d2
         * forEach: d2
         * filter:  a2
         * forEach: a2
         * filter:  b1
         * forEach: b1
         * filter:  b3
         * forEach: b3
         * filter:  c
         * forEach: c
         *
         * 输出的顺序可能会让你很惊讶！
         * 你脑海里肯定会想，应该是先将所有 filter 前缀的字符串打印出来，
         * 接着才会打印 forEach 前缀的字符串。
         * 事实上，输出的结果却是随着链条垂直移动的。
         * 比如说，当 Stream 开始处理 d2 元素时，
         * 它实际上会在执行完 filter 操作后，再执行 forEach 操作，接着才会处理第二个元素。
         * 是不是很神奇？为什么要设计成这样呢？
         *
         * 原因是出于性能的考虑。这种行为可以减少在每个元素上执行的实际操作数，如下例所示：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A"); // 过滤出以 A 为前缀的元素
                });
        /**
         * map:      d2
         * anyMatch: D2
         * map:      a2
         * anyMatch: A2
         *
         * 终端操作 anyMatch()表示任何一个元素以 A 为前缀，
         * 返回为 true，就停止循环。
         * 所以它会从 d2 开始匹配，接着循环到 a2 的时候，返回为 true ，
         * 于是停止循环。
         * 由于数据流的链式调用是垂直执行的，
         * map这里只需要执行两次。
         * 相对于水平执行来说， map会执行尽可能少的次数，
         * 而不是把所有元素都 map 转换一遍。
         */

    }
}
