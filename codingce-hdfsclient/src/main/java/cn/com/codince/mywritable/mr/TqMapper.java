package cn.com.codince.mywritable.mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TqMapper extends Mapper<LongWritable, Text, TQ, Text> {

    //放在map方法外，这样当执行过程中，如果map执行很多次，那么两个对象也不会一起new很多次，提高性能
    TQ tq = new TQ();//当作mapper的输出key
    Text vwd = new Text();//当作mapper的输出value

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        //参考分析数据：
        //value:  1949-10-01 14:21:02	34c  >>  TQ

        try {
            String[] strs = StringUtils.split(value.toString(), '\t');//按tab分割字符串成数组

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取简单日期对象
            Date date = null;

            date = sdf.parse(strs[0]);//格式化输入到map的时间：strs[0] = 1949-10-01 14:21:02

            Calendar cal = Calendar.getInstance();//获取指定时间点
            cal.setTime(date);//获取指定时间点

            tq.setYear(cal.get(Calendar.YEAR));//将年设置到天气实体类中

            /*
             * 因为月的这个值的初始值是0，因此我们要用它来表示正确的月份时就需要加1。
             * */
            tq.setMonth(cal.get(Calendar.MONTH) + 1);//将月设置到天气实体类中
            tq.setDay(cal.get(Calendar.DAY_OF_MONTH));//将日设置到天气实体类中

            //将温度34c中的c去掉，只保留数值部分
            int wd = Integer.parseInt(strs[1].substring(0, strs[1].length() - 1));
            tq.setWd(wd);//将温度设置到天气实体类中
            vwd.set(wd + "");//其实这里可以留空的vwd.set("")，因为在key的实体类中已经存在温度了

            context.write(tq, vwd);//map输出

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
