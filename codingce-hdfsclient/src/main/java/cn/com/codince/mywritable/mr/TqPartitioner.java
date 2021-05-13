package cn.com.codince.mywritable.mr;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class TqPartitioner extends Partitioner<TQ, Text> {

    /*
     * partition的输入就是mapper的输出
     * TQ实体类中包含所有信息，value中就是温度
     * 返回值int，就是返回的分组数，比如返回2，那么就会启动2个reducer，生成两个part结果文件
     *
     * getPartition方法的目的就是对key也就是TQ做分组，每一条mapper的输出都会调用一次getPartition
     *
     * numPartitions的值是2，就是我们在TQMR中自定义的reducer的启动个数
     * */
    @Override
    public int getPartition(TQ key, Text value, int numPartitions) {

        /*
         * 这一步主要是解决数据倾斜的问题，也就是说保证每个启动的reducer中处理的
         * 数据的个数基本差不多，所有的reducer都能基本同时完成任务，不会造成某个
         * 结果文件数据很多，而某个结果文件数据却极少的情况出现。
         * */
        return key.getYear() % numPartitions;
    }


}
