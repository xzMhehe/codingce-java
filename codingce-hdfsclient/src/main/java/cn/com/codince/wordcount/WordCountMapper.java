package cn.com.codince.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * WordCount 案例实操
 *
 * WordCount 案例实操按照 MapReduce 编程规范，分别编写 Mapper，Reducer，Driver。
 *
 *
 * 本地测试
 * (1)需要首先配置好 HADOOP_HOME 变量以及 Windows 运行依赖
 * (2)在 IDEA/Eclipse 上运行程序
 *
 *
 * 服务器
 * hadoop jar hdfs.jar cn.com.codince.wordcount.WordCountDriver /user/codingce/wcinput  /user/codingce/wcoutput
 *
 *
 *
 * @author williamma
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text k = new Text();
    IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行
        String line = value.toString();
        // 2 切割
        String[] words = line.split(" ");
        // 3 输出
        for (String word : words) {
            k.set(word);
            context.write(k, v);
        }


    }
}

