package cn.com.codince.mywritable.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCount {

    public static void main(String[] args) throws Exception {

        //将mapreduce作业抽象成一个job对象，那后面我们提交这个job对象就可以了
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //一定要将main函数所在的类设置到job中
        job.setJarByClass(WordCount.class);

        //通过job将自定义的map和reducer进行组装
        //设置mapper的属性
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);//设置key的类型（就是输出给shuffle的k2，如：hello）
        job.setMapOutputValueClass(LongWritable.class);//设置value的类型（就是输出给shuffle的value，每次都是1）
        FileInputFormat.setInputPaths(job, new Path("/words"));//定义分析的文件地址(我们分析的HDFS系统上的文件words)

        job.setCombinerClass(WCReducer.class);

        //设置reducer的属性
        job.setReducerClass(WCReducer.class);
        job.setOutputKeyClass(Text.class);//设置key的类型（就是最终输出的k3，如：hello）
        job.setOutputValueClass(LongWritable.class);//设置value的类型（就是最终输出的v3，如：5）
        FileOutputFormat.setOutputPath(job, new Path("/nc"));//定义分析结果保存再到HDFS上的地址

        //提交执行
        job.waitForCompletion(true);//true代表执行的同时打印进度和详情
    }

}
