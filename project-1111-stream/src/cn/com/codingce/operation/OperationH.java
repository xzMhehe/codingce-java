package cn.com.codingce.operation;

import cn.com.codingce.entity.Person;

import java.util.Arrays;
import java.util.List;

/**
 * 高级操作 Reduce
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 13:57
 */
public class OperationH {
    public static void main(String[] args) {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        /**
         * 规约操作可以将流的所有元素组合成一个结果。Java 8 支持三种不同的 reduce方法。第一种将流中的元素规约成流中的一个元素。
         *
         * 让我们看看如何使用这种方法，来筛选出年龄最大的那个人：
         */
        persons
                .stream()
                .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                .ifPresent(System.out::println);    // Pamela
        /**
         * reduce方法接受 BinaryOperator积累函数。
         * 该函数实际上是两个操作数类型相同的 BiFunction。BiFunction功能和 Function一样，
         * 但是它接受两个参数。示例代码中，我们比较两个人的年龄，来返回年龄较大的人。
         * 第二种 reduce方法接受标识值和 BinaryOperator累加器。
         *
         * 此方法可用于构造一个新的 Person，其中包含来自流中所有其他人的聚合名称和年龄：
         */
        Person result =
                persons
                        .stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.getAge() += p2.getAge();
                            p1.getName() += p2.getName();
                            return p1;
                        });
        System.out.format("name=%s; age=%s", result.getName(), result.getAge());
        /**
         * name=MaxPeterPamelaDavid; age=76
         *
         * 第三种 reduce方法接受三个参数：标识值，
         * BiFunction累加器和类型的组合器函数 BinaryOperator。
         * 由于初始值的类型不一定为 Person，我们可以使用这个归约函数来计算所有人的年龄总和：
         */
        Integer ageSuma = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.getAge(), (sum1, sum2) -> sum1 + sum2);
        System.out.println(ageSuma);  // 76
        /**
         * 结果为76，但是内部究竟发生了什么呢？让我们再打印一些调试日志：
         */
        Integer ageSumb = persons
                .stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.getAge();
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        /**
         * accumulator: sum=0; person=Max
         * accumulator: sum=18; person=Peter
         * accumulator: sum=41; person=Pamela
         * accumulator: sum=64; person=David
         *
         *
         * 你可以看到，累加器函数完成了所有工作。它首先使用初始值 0和第一个人年龄相加。接下来的三步中 sum会持续增加，直到76。
         * 等等？好像哪里不太对！组合器从来都没有调用过啊？
         * 我们以并行流的方式运行上面的代码，看看日志输出：
         */
        Integer ageSumc = persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.getAge();
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        /**
         * accumulator: sum=0; person=Pamela
         * accumulator: sum=0; person=David
         * accumulator: sum=0; person=Max
         * accumulator: sum=0; person=Peter
         * combiner: sum1=18; sum2=23
         * combiner: sum1=23; sum2=12
         * combiner: sum1=41; sum2=35
         *
         *
         * 并行执行此流将导致完全不同的执行行为。现在实际上调用了合并器。由于累加器是并行调用的，因此需要组合器来汇总单独的累加值。
         * 在OperationI中，让我们更深入地研究并行流。
         */
    }
}
