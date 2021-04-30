package cn.com.codince.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author williamma
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    int sum;

    IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //汇总各个key的个数

        // 1 累加求和
        sum = 0;
        for (IntWritable count : values) {
            sum += count.get();
        }
        System.out.println(sum + "测试");
        // 2 输出
        v.set(sum);
        context.write(key, v);
    }
}
