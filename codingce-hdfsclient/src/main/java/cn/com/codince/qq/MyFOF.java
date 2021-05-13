package cn.com.codince.qq;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 组装MR
 *
 * @author williamma
 */
public class MyFOF {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration(true);
        Job job = Job.getInstance(conf);
        job.setJarByClass(MyFOF.class);

        //map
        job.setMapperClass(FMapper.class);
        job.setMapOutputKeyClass(Text.class);//map输出key的类型是Text
        job.setMapOutputValueClass(IntWritable.class);//map输出vlaue的类型是IntWritable

        //reduce
        job.setReducerClass(FReducer.class);

        //输入输出
        Path intput = new Path("hdfs://139.9.34.48:8020/fof/input");
        FileInputFormat.addInputPath(job, intput);

        Path output = new Path("hdfs://139.9.34.48:8020/fof/output");
        /*
         * 如果输出的结果文件已经存在，那么就先删除同名文件，再生成新的结果文件
         * */
        if (output.getFileSystem(conf).exists(output)) {
            output.getFileSystem(conf).delete(output, true);
        }
        FileOutputFormat.setOutputPath(job, output);

        //submit
        job.waitForCompletion(true);
    }

}
