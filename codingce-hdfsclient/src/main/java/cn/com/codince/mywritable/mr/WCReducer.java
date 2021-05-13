package cn.com.codince.mywritable.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/*
 * Reducer<Text, LongWritable, Text, LongWritable>
 * 
 * map传入reducer的数据格式：<Text(就是k2), LongWritable(就是v2),
 * reducer处理分析完成后输出到HDFS中保存的文件格式：Text(就是k3), LongWritable(就是v3)>
 * 
 * */
public class WCReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	@Override
	/*
	 * map传递出的k2,v2之所以到了reduce形参中变成了ke,v2s是因为这中间shuffle进行了分组等操作~~~
	 * 
	 * reduce(Text key, Iterable<LongWritable> values, Context context)
	 * 对应：
	 * reduce(Text k2, Iterable<LongWritable> v2s, Context context)
	 * 
	 * 
	 *<hello,{1,1,1,1,1}>
	 * */
	protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context)
			throws IOException, InterruptedException {
		
		//计数器
		long counter = 0;
		
		//因为迭代器无法直接获取长度，所以通过循环计数的方式完成统计，就是计算单词出现的次数。
		for(LongWritable l : v2s){
			counter += l.get();//get方法将LongWritable类型返回成long类型
		}
		
		//写到HDFS中的文件中，就是浏览器看到的分析结果
		context.write(k2, new LongWritable(counter));//new LongWritable(counter)就是{1,1,1,1,1}的求和结果
	}
	

}
