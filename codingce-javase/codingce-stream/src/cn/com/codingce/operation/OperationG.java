package cn.com.codingce.operation;

import cn.com.codingce.entity.Bar;
import cn.com.codingce.entity.Foo;
import cn.com.codingce.entity.Outer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 高级操作 FlatMap
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 12:56
 */
public class OperationG {
    public static void main(String[] args) {
        /**
         * 根据OperationF 我们已经学会了如通过 map操作, 将流中的对象转换为另一种类型。
         * 但是， Map只能将每个对象映射到另一个对象。
         * 如果说，我们想要将一个对象转换为多个其他对象或者根本不做转换操作呢？
         * 这个时候， flatMap就派上用场了。
         * FlatMap 能够将流的每个元素, 转换为其他对象的流。
         * 因此，每个对象可以被转换为零个，一个或多个其他对象，
         * 并以流的方式返回。
         * 之后，这些流的内容会被放入 flatMap返回的流中。
         * 在学习如何实际操作 flatMap之前，我们先新建两个类，用来测试：
         * Foo Bar
         */
        List<Foo> foos = new ArrayList<>();

        // 创建 foos 集合
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        // 创建 bars 集合
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.getBars().add(new Bar("Bar" + i + " <- " + f.getName()))));
        /**
         * 我们创建了包含三个 foo的集合，每个 foo中又包含三个 bar。
         * flatMap 的入参接受一个返回对象流的函数。为了处理每个 foo中的 bar，我们需要传入相应 stream 流：
         */
        foos.stream()
                .flatMap(f -> f.getBars().stream())
                .forEach(b -> System.out.println(b.getName()));
        // Bar1 <- Foo1
        // Bar2 <- Foo1
        // Bar3 <- Foo1
        // Bar1 <- Foo2
        // Bar2 <- Foo2
        // Bar3 <- Foo2
        // Bar1 <- Foo3
        // Bar2 <- Foo3
        // Bar3 <- Foo3

        /**
         * 如您所见，我们已经成功地将三个foo对象的流转换为九个bar对象的流。
         *
         * 最后，以上代码示例可以简化为单个流操作管道：
         */
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.getName()))
                        .forEach(f.getBars()::add))
                .flatMap(f -> f.getBars().stream())
                .forEach(b -> System.out.println(b.getName()));

        /**
         * flatMap也可用于Java8引入的 Optional类。
         * Optional的 flatMap操作返回一个 Optional或其他类型的对象。所以它可以用于避免繁琐的 null检查。
         *
         * 接下来，让我们创建层次更深的对象：
         * 在实体类中
         *
         * 为了处理从 Outer 对象中获取最底层的 foo 字符串，
         * 你需要添加多个 null检查来避免可能发生的 NullPointerException，
         * 如下所示：
         */
        Outer outer = new Outer();
        if (outer != null && outer.getNested() != null && outer.getNested().getInner() != null) {
            System.out.println(outer.getNested().getInner().getFoo());
        }
        /**
         * 通过使用可选flatMap操作可以获得相同的行为：
         */
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.getNested()))
                .flatMap(n -> Optional.ofNullable(n.getInner()))
                .flatMap(i -> Optional.ofNullable(i.getFoo()))
                .ifPresent(System.out::println);
        /**
         * 如果不为空的话，每个 flatMap的调用都会返回预期对象的 Optional包装，
         * 否则返回为 null的 Optional包装类。
         * 关于 Optional 可参见另一篇译文《如何在 Java8 中风骚走位避开空指针异常》  https://winterbe.com/posts/2015/03/15/avoid-null-checks-in-java/
         */

    }
}
