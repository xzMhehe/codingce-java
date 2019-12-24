package cn.com.codingce.operation;

import cn.com.codingce.entity.Person;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * 并行流
 * 流是可以并行执行的，当流中存在大量元素时，可以显著提升性能。
 * 并行流底层使用的 ForkJoinPool, 它由 ForkJoinPool.commonPool()方法提供。
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 14:42
 */
public class OperationI {
    public static void main(String[] args) {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3

        /**
         * 在我的机器上，默认情况下，公共池的并行度为3。
         * 可以通过设置以下JVM参数来减小或增大此值：
         *
         * -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
         *
         * 集合支持 parallelStream()方法来创建元素的并行流。
         * 或者你可以在已存在的数据流上调用中间方法 parallel()，将串行流转换为并行流，这也是可以的。
         *
         * 为了详细了解并行流的执行行为，我们在下面的示例代码中，打印当前线程的信息：
         */
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
        /**
         * 通过日志输出，我们可以对哪个线程被用于执行流式操作，有个更深入的理解：
         * filter:  b1 [main]
         * filter:  a2 [ForkJoinPool.commonPool-worker-1]
         * map:     a2 [ForkJoinPool.commonPool-worker-1]
         * filter:  c2 [ForkJoinPool.commonPool-worker-3]
         * map:     c2 [ForkJoinPool.commonPool-worker-3]
         * filter:  c1 [ForkJoinPool.commonPool-worker-2]
         * map:     c1 [ForkJoinPool.commonPool-worker-2]
         * forEach: C2 [ForkJoinPool.commonPool-worker-3]
         * forEach: A2 [ForkJoinPool.commonPool-worker-1]
         * map:     b1 [main]
         * forEach: B1 [main]
         * filter:  a1 [ForkJoinPool.commonPool-worker-3]
         * map:     a1 [ForkJoinPool.commonPool-worker-3]
         * forEach: A1 [ForkJoinPool.commonPool-worker-3]
         * forEach: C1 [ForkJoinPool.commonPool-worker-2]
         *
         * 如您所见，并行流使用了所有的 ForkJoinPool中的可用线程来执行流式操作。
         * 在持续的运行中，输出结果可能有所不同，因为所使用的特定线程是非特定的。
         *
         * 让我们通过添加中间操作 sort来扩展上面示例：
         */
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
        /**
         * 起初结果可能看起来很奇怪：
         * filter:  c2 [ForkJoinPool.commonPool-worker-3]
         * filter:  c1 [ForkJoinPool.commonPool-worker-2]
         * map:     c1 [ForkJoinPool.commonPool-worker-2]
         * filter:  a2 [ForkJoinPool.commonPool-worker-1]
         * map:     a2 [ForkJoinPool.commonPool-worker-1]
         * filter:  b1 [main]
         * map:     b1 [main]
         * filter:  a1 [ForkJoinPool.commonPool-worker-2]
         * map:     a1 [ForkJoinPool.commonPool-worker-2]
         * map:     c2 [ForkJoinPool.commonPool-worker-3]
         * sort:    A2 <> A1 [main]
         * sort:    B1 <> A2 [main]
         * sort:    C2 <> B1 [main]
         * sort:    C1 <> C2 [main]
         * sort:    C1 <> B1 [main]
         * sort:    C1 <> C2 [main]
         * forEach: A1 [ForkJoinPool.commonPool-worker-1]
         * forEach: C2 [ForkJoinPool.commonPool-worker-3]
         * forEach: B1 [main]
         * forEach: A2 [ForkJoinPool.commonPool-worker-2]
         * forEach: C1 [ForkJoinPool.commonPool-worker-1]
         *
         * 似乎sort只在主线程上顺序执行。实际上，sort在并行流上，
         * 在后台使用了新的Java 8方法Arrays.parallelSort()。
         * 如Javadoc中所述，此方法决定数组的长度是排序是顺序执行还是并行执行：
         * ===> 如果指定数组的长度小于最小粒度，则使用适当的Arrays.sort方法对其进行排序。<===
         *
         * 回到上reduce一节的示例。我们已经发现，组合器函数仅在并行流中调用，而不在顺序流中调用。
         * 让我们看看实际涉及到哪些线程：
         */
        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                                    sum, p, Thread.currentThread().getName());
                            return sum += p.getAge();
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                                    sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });
        /**
         * 控制台输出显示，累加器和合并器函数在所有可用线程上并行执行：
         * accumulator: sum=0; person=Pamela; [main]
         * accumulator: sum=0; person=Max;    [ForkJoinPool.commonPool-worker-3]
         * accumulator: sum=0; person=David;  [ForkJoinPool.commonPool-worker-2]
         * accumulator: sum=0; person=Peter;  [ForkJoinPool.commonPool-worker-1]
         * combiner:    sum1=18; sum2=23;     [ForkJoinPool.commonPool-worker-1]
         * combiner:    sum1=23; sum2=12;     [ForkJoinPool.commonPool-worker-2]
         * combiner:    sum1=41; sum2=35;     [ForkJoinPool.commonPool-worker-2]
         *
         * 总之，可以说并行流可以为具有大量输入元素的流带来不错的性能提升。
         * 但是，请记住，像一些并行流操作reduce，并collect需要额外的计算（组合操作）时，依次执行其中不需要。
         *
         * 此外，我们了解到所有并行流操作共享相同的JVM范围的common ForkJoinPool。
         * 因此，您可能要避免实施缓慢的阻塞流操作，因为这有可能减慢应用程序中严重依赖并行流的其他部分的速度。
         * 我的Java 8流编程指南到此结束。如果您想了解有关Java 8流的更多信息，
         * 我建议您使用Stream Javadoc
         * 软件包文档(https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#NonInterference)
         * 如果您想了解有关底层机制的更多信息，则可能需要阅读Martin Fowlers的有关Collection Pipelines的文章。
         *
         * 欢迎关注 掌上编程 公众号
         */


    }
}
