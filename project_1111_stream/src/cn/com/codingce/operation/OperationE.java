package cn.com.codingce.operation;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 数据流复用问题
 *
 * @author 2460798168@qq.com
 * @date 2019/11/11 10:47
 */
public class OperationE {
    public static void main(String[] args) {
        /**
         * Java8 Stream 流是不能被复用的， -->  一旦你调用任何终端操作，流就会关闭： <--
         */
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
        stream.noneMatch(s -> true);   // exception
        /**
         * 在同一流上调用noneMatchafter会anyMatch导致以下异常：
         * java.lang.IllegalStateException: stream has already been operated upon or closed
         *     at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:229)
         *     at java.util.stream.ReferencePipeline.noneMatch(ReferencePipeline.java:459)
         *     at com.winterbe.java8.Streams5.test7(Streams5.java:38)
         *     at com.winterbe.java8.Streams5.main(Streams5.java:28)
         *
         * 为了克服此限制，我们必须为要执行的每个终端操作创建一个新的流链，
         * 例如，我们可以创建一个流提供程序以构造一个已经设置了所有中间操作的新流：
         */
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
        /**
         * 通过构造一个新的流，来避开流不能被复用的限制, 这也是取巧的一种方式。
         */
    }

}
