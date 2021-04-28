package cn.com.codince.mywritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 第三步 编写 Reducer 类
 *
 * @author williamma
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    private FlowBean outV = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context
            context) throws IOException, InterruptedException {
        long totalUp = 0;
        long totalDown = 0;

        //1 遍历 values,将其中的上行流量,下行流量分别累加
        for (FlowBean flowBean : values) {
            totalUp += flowBean.getUpFlow();
            totalDown += flowBean.getDownFlow();
        }

        //2 封装 outKV
        outV.setUpFlow(totalUp);
        outV.setDownFlow(totalDown);
        outV.setSumFlow(totalUp + totalDown);

        //3 写出 outK outV
        context.write(key, outV);
    }
}
