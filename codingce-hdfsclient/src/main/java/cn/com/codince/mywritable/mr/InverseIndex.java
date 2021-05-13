package cn.com.codince.mywritable.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class InverseIndex {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        //设置jar
        job.setJarByClass(InverseIndex.class);

        //设置Mapper相关的属性
        job.setMapperClass(IndexMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));//定义一个目录地址，将我们要分析的三个文件都放进去即可

        //设置Reducer相关属性
        job.setReducerClass(IndexReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setCombinerClass(IndexCombiner.class);

        //提交任务
        job.waitForCompletion(true);
    }

    public static class IndexMapper extends Mapper<LongWritable, Text, Text, Text> {

        /*
         * 定义Text对象，因为我们的输出类型必须是Text
         * */
        private Text k = new Text();
        private Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value,
                           Mapper<LongWritable, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            String line = value.toString();//获取一行数据
            String[] fields = line.split(" ");//按空格进行拆分
            FileSplit inputSplit = (FileSplit) context.getInputSplit();//获取输入文件的信息
            Path path = inputSplit.getPath();//从输入文件的信息中获取路径信息
            String name = path.getName();//从路径信息中获取文件名（a.txt）
            for (String f : fields) {//循环当前行拆分后的信息，并组合成指定的格式
                k.set(f + "->" + name);
                v.set("1");
                context.write(k, v);
            }
        }

    }

    public static class IndexCombiner extends Reducer<Text, Text, Text, Text> {

        private Text k = new Text();
        private Text v = new Text();

        @Override
        protected void reduce(Text key, Iterable<Text> values,
                              Reducer<Text, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            String[] fields = key.toString().split("->");
            long sum = 0;
            for (Text t : values) {
                sum += Long.parseLong(t.toString());
            }
            k.set(fields[0]);
            v.set(fields[1] + "->" + sum);
            context.write(k, v);
        }

    }

    public static class IndexReducer extends Reducer<Text, Text, Text, Text> {

        private Text v = new Text();

        @Override
        protected void reduce(Text key, Iterable<Text> values,
                              Reducer<Text, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            String value = "";
            for (Text t : values) {
                value += t.toString() + " ";
            }
            v.set(value);
            context.write(key, v);
        }

    }

}
