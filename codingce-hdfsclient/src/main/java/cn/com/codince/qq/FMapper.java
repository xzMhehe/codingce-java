package cn.com.codince.qq;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class FMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	Text mkey= new Text();
	IntWritable mval = new IntWritable();
	
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {

		String[] strs = StringUtils.split(value.toString(), ' ');
		
		for(int i=1;i<strs.length;i++){

			mkey.set(fof(strs[0],strs[i]));  
			mval.set(0);//直接关系用0表示
			context.write(mkey, mval);  
			
			for (int j = i+1; j < strs.length; j++) {
				mkey.set(fof(strs[i],strs[j]));  
				mval.set(1);//间接关系用1表示
				context.write(mkey, mval);  
			}
		}

	}
	
	/*
	 * 按字典排序好友关系：
	 * 如：
	 * 	hello tom
	 * 	tom	hello
	 * 	都统一为：hello tom
	 * 因为计算机认为hello tom和tom	hello是不同的。
	 * */
	public static String fof(String str1  , String str2){
		
		if(str1.compareTo(str2) > 0){
			return str2+":"+str1;
		}
		return str1+":"+str2;
	}
}
