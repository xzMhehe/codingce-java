package cn.com.codingce.streamdemo;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Date 2019/10/29 8:59
 */
public class DemoB {
    public static void main(String[] args) {
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        /**
         * 当且仅当存在终端操作时，中间操作操作才会被执行。
         */
        Stream.of("d1","d2","d3")
                .filter(s -> {
                    System.out.println("filter" + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        Stream.of("d1","d2","d3","a1")
                .map(s -> {
                    System.out.println("map" + s);
                    return s.toUpperCase();
                })
                .allMatch(s -> {
                    System.out.println("anyMacth" + s);
                    return s.startsWith("a");
                });

    }
}
