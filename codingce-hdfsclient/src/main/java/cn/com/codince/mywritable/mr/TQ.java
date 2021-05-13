package cn.com.codince.mywritable.mr;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

//天气实体类，WritableComparable实现该接口可以进行自定义排序
public class TQ implements WritableComparable<TQ> {

    private int year;
    private int month;
    private int day;
    private int wd;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    //输出序列化
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(year);
        out.writeInt(month);
        out.writeInt(day);
        out.writeInt(wd);

    }

    //输入反序列化
    @Override
    public void readFields(DataInput in) throws IOException {
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.wd = in.readInt();
    }

    //自定义排序比较规则，最终return返回的int值，就代表两个日期到底谁大
    @Override
    public int compareTo(TQ that) {

        //日期正序排列
        /*
         * compare方法，如果返回值==0，相等
         * 如果返回值<0，参数1大于参数2
         * 如果返回值>0，参数1小于参数2
         * */
        int c1 = Integer.compare(this.getYear(), that.getYear());

        if (c1 == 0) {//如果年相同，则比较月
            int c2 = Integer.compare(this.getMonth(), that.getMonth());
            if (c2 == 0) {//如果月也相同，则比较日
                return Integer.compare(this.getDay(), that.getDay());
            }
            return c2;
        }

        return c1;
    }


}
