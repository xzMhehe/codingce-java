package cn.com.codince.qq;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FReducer extends Reducer<Text, IntWritable, Text, Text> {

    Text rval = new Text();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> vals, Context context)
            throws IOException, InterruptedException {

        int sum = 0;
        int flg = 0;
        for (IntWritable v : vals) {
            //只要有一次满足==0的条件，就说明两个人存在直接好友关系，那么之前的累加就作废了，不推荐好友
            if (v.get() == 0) {
                flg = 1;
            }
            sum += v.get();//推荐给你的这个好友是你sum个直接好友的直接好友
        }
        //如果循环中一次都没出现v.get()==0的情况，说明两个人不存在直接好友关系，就可以推荐了
        if (flg == 0) {
            rval.set(sum + "");
            context.write(key, rval);
        }
    }
}
