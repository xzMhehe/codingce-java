package cn.com.codince.mywritable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 第一步
 *
 * 编写流量统计的 Bean 对象
 *
 * @author williamma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowBean implements Writable {

    //1 继承 Writable 接口
    private long upFlow; //上行流量
    private long downFlow; //下行流量
    private long sumFlow; //总流量

    //4 实现序列化和反序列化方法,注意顺序一定要保持一致
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }
}
