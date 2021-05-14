package cn.com.codingce.hbase.weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyWeiBo {

    //获取 Configuration 对象
    private static Configuration myconf;

    //微博内容表的表名
    private static final byte[] TABLE_CONTENT = Bytes.toBytes("weibo:content");
    //用户关系表的表名
    private static final byte[] TABLE_RELATIONS = Bytes.toBytes("weibo:relations");
    //微博收件箱表的表名
    private static final byte[] TABLE_RECEIVE_CONTENT_EMAIL = Bytes.toBytes("weibo:receive_content_email");

    static {
        //使用 HBaseConfiguration 的单例方法实例化
        myconf = HBaseConfiguration.create();
        myconf.set("hbase.zookeeper.quorum", "139.9.34.48");
        myconf.set("hbase.zookeeper.property.clientPort", "2181");
    }

    /**
     * 测试发布微博内容
     */
    public void testPublishContent(MyWeiBo wb) {
        wb.publishContent("0001", "今天买了一包空气，送了点薯片，非常开心！！");
        wb.publishContent("0001", "今天天气不错。");
    }

    /**
     * 测试添加关注
     */
    public void testAddAttend(MyWeiBo wb) {
        wb.publishContent("0008", "准备下课！");
        wb.publishContent("0009", "准备关机！");
//        wb.addAttends("0001", "0008", "0009");
    }

    /**
     * 测试取消关注
     */
    public void testRemoveAttend(MyWeiBo wb) {
//        wb.removeAttends("0001", "0008");
    }

    /**
     * 测试展示内容
     */
    public void testShowMessage(MyWeiBo wb) {
        List<Message> messages = wb.getAttendsContent("0001");
        System.out.println(messages.toString());
        for (Message message : messages) {
            System.out.println(message);
        }
        System.out.println("========testShowMessage==========");
    }

    public static void main(String[] args) {
        MyWeiBo weibo = new MyWeiBo();
//        weibo.initTable();

//        weibo.testPublishContent(weibo);
//        weibo.testAddAttend(weibo);
//        weibo.testShowMessage(weibo);
//        weibo.testRemoveAttend(weibo);
        weibo.testShowMessage(weibo);
    }

    /**
     * 获取连接
     *
     * @return
     * @throws IOException
     */
    public static Connection getConnection() {
        try {
            Connection connection = ConnectionFactory.createConnection(myconf);
            System.out.println("创建连接。。。" + connection);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 发布微博
     * a、微博内容表中数据+1
     * b、向微博收件箱表中加入微博的Rowkey
     */
    public void publishContent(String uid, String content) {
        Connection connection = getConnection();
        try {
            //a、微博内容表中添加1条数据，首先获取微博内容表描述
            Table contentTBL = connection.getTable(TableName.valueOf(TABLE_CONTENT));
//            HTableInterface contentTBL = connection.getTable(TableName.valueOf());

            //组装Rowkey
            long timestamp = System.currentTimeMillis();
            String rowKey = uid + "_" + timestamp;

            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("content"), timestamp, Bytes.toBytes(content));

            contentTBL.put(put);
            System.out.println("插入数据成功。。。");
            System.out.println("新增一条微博。。。");


            //b、向微博收件箱表中加入发布的Rowkey
            //b.1、查询用户关系表，得到当前用户有哪些粉丝
            Table relationsTBL = connection.getTable(TableName.valueOf(TABLE_RELATIONS));
//            HTableInterface relationsTBL = connection.getTable(TableName.valueOf(TABLE_RELATIONS));

            //b.2、取出目标数据
            Get get = new Get(Bytes.toBytes(uid));
            get.addFamily(Bytes.toBytes("fans"));

            Result result = relationsTBL.get(get);
            List<byte[]> fans = new ArrayList<byte[]>();

            //遍历取出当前发布微博的用户的所有粉丝数据
            for (Cell cell : result.rawCells()) {
                fans.add(CellUtil.cloneQualifier(cell));
            }
            //如果该用户没有粉丝，则直接return
            if (fans.size() <= 0) return;

            //开始操作收件箱表
            Table recTBL = connection.getTable(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
//            HTableInterface recTBL = connection.getTable(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
            List<Put> puts = new ArrayList<Put>();
            for (byte[] fan : fans) {
                Put fanPut = new Put(fan);
                fanPut.addColumn(Bytes.toBytes("info"), Bytes.toBytes(uid), timestamp, Bytes.toBytes(rowKey));
                puts.add(fanPut);
            }
            recTBL.put(puts);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 取消关注（remove)
     * a、在微博用户关系表中，对当前主动操作的用户删除对应取关的好友
     * b、在微博用户关系表中，对被取消关注的人删除粉丝（当前操作人）
     * c、从收件箱中，删除取关的人的微博的rowkey
     */
//    public void removeAttends(String uid, String... attends) {
//        //过滤数据
//        if (uid == null || uid.length() <= 0 || attends == null || attends.length <= 0) return;
//
//        Connection connection = getConnection();
//
//        try {
//            //a、在微博用户关系表中，删除已关注的好友
//            Table relationsTBL = connection.getTable(TableName.valueOf(TABLE_RELATIONS));
////            HTableInterface relationsTBL = connection.getTable(TableName.valueOf(TABLE_RELATIONS));
//
//            //待删除的用户关系表中的所有数据
//            List<Delete> deletes = new ArrayList<Delete>();
//            //当前取关操作者的uid对应的Delete对象
//            Delete attendDelete = new Delete(Bytes.toBytes(uid));
//            //遍历取关，同时每次取关都要将被取关的人的粉丝-1
//            for (String attend : attends) {
//                attendDelete.deleteColumn(Bytes.toBytes("attends"), Bytes.toBytes(attend));
//                //b
//                Delete fansDelete = new Delete(Bytes.toBytes(attend));
//                fansDelete.deleteColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid));
//
//
//                deletes.add(fansDelete);
//            }
//
//            deletes.add(attendDelete);
//            relationsTBL.delete(deletes);
//
//            //c、删除取关的人的微博rowkey 从 收件箱表中
//            Table recTBL = connection.getTable(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
////            HTableInterface recTBL = connection.getTable(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
//
//            Delete recDelete = new Delete(Bytes.toBytes(uid));
//            for (String attend : attends) {
//                recDelete.deleteColumn(Bytes.toBytes("info"), Bytes.toBytes(attend));
//            }
//            recTBL.delete(recDelete);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 获取微博实际内容
     * a、从微博收件箱中获取所有关注的人的发布的微博的rowkey
     * b、根据得到的rowkey去微博内容表中得到数据
     * c、将得到的数据封装到Message对象中
     */
    public List<Message> getAttendsContent(String uid) {
        Connection connection = getConnection();
        try {
            Table recTBL = connection.getTable(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
//            HTableInterface recTBL = connection.getTable(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
            //a、从收件箱中取得微博rowKey
            Get get = new Get(Bytes.toBytes(uid));
            //设置最大版本号
            get.setMaxVersions(5);
            List<byte[]> rowkeys = new ArrayList<byte[]>();
            Result result = recTBL.get(get);
            for (Cell cell : result.rawCells()) {
                rowkeys.add(CellUtil.cloneValue(cell));
            }
            //b、根据取出的所有rowkey去微博内容表中检索数据
            Table contentTBL = connection.getTable(TableName.valueOf(TABLE_CONTENT));
//            HTableInterface contentTBL = connection.getTable(TableName.valueOf(TABLE_CONTENT));
            List<Get> gets = new ArrayList<Get>();
            //根据rowkey取出对应微博的具体内容
            for (byte[] rk : rowkeys) {
                Get g = new Get(rk);
                gets.add(g);
            }
            //得到所有的微博内容的result对象
            Result[] results = contentTBL.get(gets);

            List<Message> messages = new ArrayList<Message>();
            for (Result res : results) {
                for (Cell cell : res.rawCells()) {
                    Message message = new Message();

                    String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                    String userid = rowKey.substring(0, rowKey.indexOf("_"));
                    String timestamp = rowKey.substring(rowKey.indexOf("_") + 1);
                    String content = Bytes.toString(CellUtil.cloneValue(cell));

                    message.setContent(content);
                    message.setTimestamp(timestamp);
                    message.setUid(userid);

                    messages.add(message);
                }
            }
            System.out.println("=========getAttendsContent============");
            System.out.println("=========获取微博实际内容成功============");
            return messages;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
