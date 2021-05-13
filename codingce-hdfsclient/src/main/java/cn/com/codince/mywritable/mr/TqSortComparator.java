package cn.com.codince.mywritable.mr;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


/*排序*/
public class TqSortComparator extends WritableComparator {

    public TqSortComparator() {
        /*
         * 将天气实体类传递给父类，true代表将实体类生成对象实例
         * 这一过程将在父类中完成并使用，这里必须这么写。
         * */
        super(TQ.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        //天气实体类之所以类型是WritableComparable，是因为上面构造器传入父类WritableComparator产生的
        TQ t1 = (TQ) a;//向下转型天气实体类对象a
        TQ t2 = (TQ) b;//向下转型天气实体类对象b

        /*
         * 之前在TQ中已经对mapper的输出按照年月日进行了正序排序，然后在这里再次
         * 按照年月温度进行排序（因为我们的题目需求是计算每个月温度最高的两天）所以
         * 这里只比较年月和温度即可，然后按照温度降序排列。
         * */

        int c1 = Integer.compare(t1.getYear(), t2.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(t1.getMonth(), t2.getMonth());
            if (c2 == 0) {
                return -Integer.compare(t1.getWd(), t2.getWd());//-号代表负数，反序的意思
            }
            return c2;
        }

        return c1;
    }
}
