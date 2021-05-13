package cn.com.codince.mywritable;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 第四步 编写 Driver 驱动类
 *
 * @author williamma
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, InterruptedException {

        //解决org.apache.hadoop.security.AccessControlException: Permission denied: user异常解决方法
        System.setProperty("HADOOP_USER_NAME", "codingce");

        //1 获取 job 对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //2 关联本 Driver 类
        job.setJarByClass(FlowDriver.class);

        //3 关联 Mapper 和 Reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        //4 设置 Map 端输出 KV 类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //5 设置程序最终输出的 KV 类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        //6 设置程序的输入输出路径
        FileInputFormat.setInputPaths(job, new Path("/Users/williamma/mxz_code/github/phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://139.9.34.48:8020/flowoutput"));

        //7 提交 Job
        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 : 1);
    }
}
