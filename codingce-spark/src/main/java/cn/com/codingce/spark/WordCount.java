package cn.com.codingce.spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class WordCount {
    public static void main(String[] args) {
        // 输入文件
        String wordFile = "/Users/williamma/mxz_code/github/idea.txt";
        SparkSession spark = SparkSession.builder().appName("wordcount")
                .config("spark.hadoop.mapreduce.output.fileoutputformat.compress", false)
                .getOrCreate();
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        JavaRDD<String> hdfstext = jsc.textFile(wordFile);
        // 切分（转化操作）
        JavaRDD<String> words = hdfstext.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String x) {
                return Arrays.asList(x.split(" ")).iterator();
            }
        });
        // 单次计 1（转化操作）
        JavaPairRDD<String, Integer> pairs = words.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String word) {
                        return new Tuple2<>(word, 1);
                    }
                });
        // 累加 1（转化操作）
        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) {
                        return v1 + v2;
                    }
                }).repartition(1);
        // 输出目录
        String outDir = "/Users/williamma/mxz_code/github";
        wordCounts.saveAsTextFile(outDir);
        jsc.close();
    }
}
