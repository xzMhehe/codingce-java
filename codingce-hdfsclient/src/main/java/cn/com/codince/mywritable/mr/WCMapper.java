package cn.com.codince.mywritable.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * Mapper<LongWritable, Text, Text, LongWritable>
 * 对应上一章图中的：
 * Mapper<k1, v1, k2, v2>
 * 其中LongWritable是Long数据类型对应在hadoop中的序列化类型，比serlizable序列化接口效率更高
 * 其中Text是String数据类型对应在hadoop中的序列化类型。
 * 
 * */
public class WCMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	@Override
	protected void map(LongWritable k1, Text v1, Context context)
			throws IOException, InterruptedException {
		//接收key,value(k1,v1)
		String line = v1.toString();//获取一行内容，一行调用一次map    hello tom
		
		//拆分key,value
		String[] words = line.split(" ");//[hello,tom]
		
		//组成新的key,value(k2,v2s)
		for(String w : words){
			//发送给reduce进行汇总
			/*
			 * 注意：所有的String类型必须序列化成Text类型，所有的Long类型必须
			 * 序列化成LongWritable类型，才可以在map和reduce之间进行数据的传递。
			 * */
			context.write(new Text(w), new LongWritable(1));//hello 1   tom 1
		}
	}

	
	
	

}
