package cn.com.codince.mywritable.mr;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TqReducer extends Reducer<TQ, Text, Text, Text> {

    /*
     * 年	月     日     温度
     * 1970 01 01 33
     * 1970 01 02 30
     * 1970 01 21 28
     * 1970 01 22 27
     * 相同年月为一组就是这样，也就是最后map的输出，即reducer的输入
     *
     * 下面就是shuffle后的结果，传递到reducer中。
     * <1949 10,{38,36,34}>、<1950 10,{38,36,34}>
     * ...
     * 我们只需要对01月的所有温度{33,30,28,27}进行循环即可，前两个温度一定是最高的，
     * 对应的日就是我们要的结果，01月最热的两天，其他月同理。
     * */

    Text rkey = new Text();
    Text rval = new Text();

    @Override
    protected void reduce(TQ key, Iterable<Text> vals, Context context)
            throws IOException, InterruptedException {

        int flg = 0;
        int day = 0;

        //vals就是{38,36,34}温度
        for (Text v : vals) {

            //如果是第一次循环，比如：1949年10月的第一次循环（这就是01月温度最高的那天）
            if (flg == 0) {
                day = key.getDay();

                rkey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay());
                rval.set(key.getWd() + "");//这里也可以写v，就是当前温度，但是在你循环vals的时候，其实key是相应变化的，对应当前的vals的，所以也可以直接从key取温度
                context.write(rkey, rval);
                flg++;//当月已经循环了一天
            }

            //如果1970年01月循环过一次了，那么看看当前的日是否循环过，如果当前的日没循环过，那么继续记录（这就是01月温度第二高的那天）
            //然后break退出循环，因为我们只记录最高温度的两天
            if (flg != 0 && day != key.getDay()) {

                rkey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay());
                rval.set(key.getWd() + "");
                context.write(rkey, rval);
                break;
            }

            //最终通过reducer得到当月温度最高的两天。
        }

    }
}
