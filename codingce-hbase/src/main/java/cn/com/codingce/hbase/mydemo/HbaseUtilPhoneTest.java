package cn.com.codingce.hbase.mydemo;

import cn.com.codingce.hbase.dao.HbasePhoneDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * 通话记录测试
 */
public class HbaseUtilPhoneTest {

    private HbasePhoneDao dao = null;

    @Before
    public void init() {
        dao = HbasePhoneDao.getInstance();
    }

    @Test
    public void run() throws IOException {
        String tableName = "phon02";
        //-------------------------通话记录实例
        //dao.createTable(tableName,"cf");//创建phone表
        try {
            dao.insertPhoneRecord(tableName);//插入通话记录测试记录
            //dao.scanPhone();//通过scan全表检索查询某个手机号二月份到三月份的通话记录
            //dao.scanPhone2();//查询某个手机主叫为1的记录（主动拨打的通话记录）
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



