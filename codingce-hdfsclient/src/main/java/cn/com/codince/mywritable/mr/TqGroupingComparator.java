package cn.com.codince.mywritable.mr;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/*
 * reducer阶段按照年、月两个维度进行分组
 *
 * reducer阶段是负责汇总map阶段的输出结果的，既然要汇总，就要按照得到的
 * 输入进行分组，又因为我们题目需求是计算每一年每个月最热的两天，所以我们按照
 * 相同年和相同月为一组的规则进行分组。如：下面。输出就按照
 * 方法的规则进行分组，相同年月分到一个组里，其值就是相同年月的所有天的降序排列好的温度。
 *
 * 年	月     日     温度(已经降序)
 * 1970 01 01 33
 * 1970 01 02 30
 * 1970 01 21 28
 * 1970 01 22 27
 * 相同年月为一组就是这样，也就是最后map的输出，即reducer的输入
 * */
public class TqGroupingComparator extends WritableComparator {


    public TqGroupingComparator() {
        //把要比较的类型传递到父类
        super(TQ.class, true);//按照TQ实体类中进行分组，TqMapper输出的key就是TQ
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        TQ t1 = (TQ) a;
        TQ t2 = (TQ) b;

        int c1 = Integer.compare(t1.getYear(), t2.getYear());
        if (c1 == 0) {
            return Integer.compare(t1.getMonth(), t2.getMonth());
        }
        return c1;
    }

}
