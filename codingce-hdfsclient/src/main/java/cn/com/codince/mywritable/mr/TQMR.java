package cn.com.codince.mywritable.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TQMR {

    public static void main(String[] args) throws Exception {

        //conf
        Configuration conf = new Configuration(true);

        //job
        Job job = Job.getInstance(conf);
        job.setJarByClass(TQMR.class);

        //map阶段开始
        job.setMapperClass(TqMapper.class);
        job.setMapOutputKeyClass(TQ.class);
        job.setMapOutputValueClass(Text.class);
        //other:sort,part..,group...
        job.setPartitionerClass(TqPartitioner.class);
        job.setSortComparatorClass(TqSortComparator.class);
        //map阶段结束

        //reduce阶段开始
        job.setGroupingComparatorClass(TqGroupingComparator.class);
        job.setReducerClass(TqReducer.class);

        //input,output
        Path input = new Path("/tq/input");//待分析文件所在目录
        FileInputFormat.addInputPath(job, input);

        Path output = new Path("/tq/bcd");//分析结果文件保存目录
        if (output.getFileSystem(conf).exists(output)) {
            output.getFileSystem(conf).delete(output, true);
        }
        FileOutputFormat.setOutputPath(job, output);
        job.setNumReduceTasks(2);//启动reducer个数

        job.waitForCompletion(true);//执行分析


    }


}
