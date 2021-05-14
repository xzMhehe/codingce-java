package cn.com.codingce.hbase.weibo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 1.4.2 版本写法
 *
 * @author williamma
 */
public class WeiBo {

    private Configuration conf = HBaseConfiguration.create();

    private static final byte[] CONTENT = Bytes.toBytes("weibo:content");

    private static final byte[] RELATIONS = Bytes.toBytes("weibo:relations");

    private static final byte[] RECEIVE_CONTENT_EMAIL = Bytes.toBytes("weibo:receive-content-email");

    //微博内容表的表名
    private static final byte[] TABLE_CONTENT = Bytes.toBytes("weibo:content");
    //用户关系表的表名
    private static final byte[] TABLE_RELATIONS = Bytes.toBytes("weibo:relations");
    //微博收件箱表的表名
    private static final byte[] TABLE_RECEIVE_CONTENT_EMAIL = Bytes.toBytes("weibo:receive_content_email");


    //获取 Configuration 对象
    private static Configuration myconf;

    static {
        //使用 HBaseConfiguration 的单例方法实例化
        myconf = HBaseConfiguration.create();
        myconf.set("hbase.zookeeper.quorum", "139.9.34.48");
        myconf.set("hbase.zookeeper.property.clientPort", "2181");
    }

    /**
     * 获取连接
     *
     * @return
     * @throws IOException
     */
    public static Connection getConnection() throws IOException {
        Connection connection = ConnectionFactory.createConnection(myconf);
        System.out.println("创建连接。。。" + connection);
        return connection;
    }

    /**
     * 创建命名空间以及表名的定义
     *
     * @throws IOException
     */
    public void initNamespace() throws IOException {
        Connection connection = getConnection();
        Admin admin = null;
        try {
            admin = connection.getAdmin();
            //命名空间类似于关系型数据库中的schema，可以想象成文件夹
            NamespaceDescriptor weibo = NamespaceDescriptor
                    .create("weibo")
                    .addConfiguration("creator", "Jinji")
                    .addConfiguration("create_time", System.currentTimeMillis() + "")
                    .build();
            admin.createNamespace(weibo);
            System.out.println("命名空间创建成功");
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 建表
     */
    public void initTable() throws IOException {
        createContent();
        createRelations();
        createReceiveContentEmails();
    }

    /**
     * a.微博内容表：  xxx 发布 xx 内容
     * table name : weibo:content
     * rowkey: 用户ID_时间戳
     * columnfamily: cf
     * colunmnlabel:
     * 图片
     * 内容
     * 标题
     * version:	只需要一个版本
     */
    private void createContent() throws IOException {
        Connection connection = getConnection();
        Admin admin = null;
        try {
            admin = connection.getAdmin();
            HTableDescriptor content = new HTableDescriptor(TableName.valueOf(CONTENT));
            HColumnDescriptor c_cf = new HColumnDescriptor(Bytes.toBytes("cf"));
            c_cf.setBlockCacheEnabled(true);
            //推荐是计算后的值
            c_cf.setBlocksize(2097152);
            // 一定事先配置好
            //			c_cf.setCompressionType(Algorithm.SNAPPY);
            c_cf.setMaxVersions(1);
            c_cf.setMinVersions(1);

            content.addFamily(c_cf);
            admin.createTable(content);

        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * b.用户关系表：
     * 用户id fans attends
     * table name : weibo:relations
     * rowkey: 用户ID
     * columnfamily: attends、fans
     * colunmnlabel:
     * 关注用户ID
     * 粉丝用户ID
     * colunmnvalue: 用户ID
     * version:	只需要一个版本
     */
    private void createRelations() throws IOException {

        Connection connection = getConnection();

        Admin admin = null;

        try {
            admin = connection.getAdmin();

            HTableDescriptor relations = new HTableDescriptor(TableName.valueOf(RELATIONS));

            HColumnDescriptor attends = new HColumnDescriptor(Bytes.toBytes("attends"));
            attends.setBlockCacheEnabled(true);
            attends.setBlocksize(2097152);

            attends.setMaxVersions(1);
            attends.setMinVersions(1);

            HColumnDescriptor fans = new HColumnDescriptor(Bytes.toBytes("fans"));
            fans.setBlockCacheEnabled(true);
            fans.setBlocksize(2097152);

            fans.setMaxVersions(1);
            fans.setMinVersions(1);

            relations.addFamily(attends);
            relations.addFamily(fans);

            admin.createTable(relations);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 用户微博内容接收邮件箱表
     *
     * @throws IOException
     */
    private void createReceiveContentEmails() throws IOException {

        Connection connection = getConnection();

        Admin admin = null;

        /**
         * c.用户微博内容接收邮件箱表：
         * table name : weibo:receive-content-email
         * rowkey: 用户ID
         * columnfamily: cf
         * colunmnlabel:
         * 用户ID
         * colunmnvalue:
         * 取微博内容的rowkey
         * version: 	1000
         */

        try {
            admin = connection.getAdmin();
            HTableDescriptor receive_content_email = new HTableDescriptor(TableName.valueOf(RECEIVE_CONTENT_EMAIL));

            HColumnDescriptor rce_cf = new HColumnDescriptor(Bytes.toBytes("cf"));

            rce_cf.setBlockCacheEnabled(true);
            rce_cf.setBlocksize(2097152);

            rce_cf.setMaxVersions(1000);
            rce_cf.setMinVersions(1000);

            receive_content_email.addFamily(rce_cf);

            admin.createTable(receive_content_email);

        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发布微博
     * a、微博内容表中数据+1
     * b、向微博收件箱表中加入微博的Rowkey
     */
    public void publishContent(String uid, String content) throws IOException {
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
     * 添加关注用户：
     * a. 在微博用户关系表中 添加新的好友关注(attends)
     * b. 从被关注用户角度来说， 新增粉丝用户(fans)
     * c. 微博邮件箱表添加关注用户发布的微博内容
     *
     * @param uid     粉丝 ID
     * @param attends 被关注用户ID
     */
    public void addAttends(String uid, String... attends) throws IOException {

        //参数过滤
        if (attends == null || attends.length <= 0) return;
        /**
         * a.在微博用户关系表中 添加新的好友关注(attends)
         * (0001) ,(0002,0003)
         * rowkey   column      value
         * 0001    attends:0002,0002
         * 0001    attends:0003,0003
         *
         * rowkey  column    value
         * 0003    fans:0001,0001
         * 0002    fans:0001,0001
         *
         */
        Connection connection = getConnection();
        try {

            Table realtionsTBL = connection.getTable(TableName.valueOf(RELATIONS));
//            HTableInterface realtionsTBL = connection.getTable(RELATIONS);

            List<Put> puts = new ArrayList<Put>();
            //a. 在微博用户关系表中 添加新的好友关注(attends)
            Put attendsPut = new Put(Bytes.toBytes(uid));
            for (String attend : attends) {
                attendsPut.addColumn(Bytes.toBytes("attends"), Bytes.toBytes(attend), Bytes.toBytes(attend));
                //b. 从被关注用户角度来说， 新增粉丝用户(fans)
                Put fansPut = new Put(Bytes.toBytes(attend));
                fansPut.addColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid), Bytes.toBytes(uid));
                puts.add(fansPut);
            }
            puts.add(attendsPut);
            realtionsTBL.put(puts);

            //c. 微博邮件箱表添加关注用户发布的微博内容的rowkey
            /**
             * 1. 首先查询被关注用户ID发布的微博内容的rowkey
             *     单个被关注用户ID,  --查询content ->微博内容的rowkey
             *     0001_xxx
             *     0001_aaa
             *     0002_yyy
             *     0002_zzz
             * 2. 将前面获取的rowkey列表 遍历出来在微博内容邮件表中正式添加数据
             *
             */
            Table contentTBL = connection.getTable(TableName.valueOf(CONTENT));
//            HTableInterface contentTBL = connection.getTable(CONTENT);
            Scan scan = new Scan();
            List<byte[]> rowkeys = new ArrayList<byte[]>();
            for (String attend : attends) {
                //扫描表的rowkey,含有字符串("被关注用户ID_")
                RowFilter filter = new RowFilter(CompareOp.EQUAL, new SubstringComparator(attend + "_"));
                scan.setFilter(filter);
                ResultScanner result = contentTBL.getScanner(scan);
                // 迭代器遍历
                Iterator<Result> itearor = result.iterator();
                while (itearor.hasNext()) {
                    Result r = itearor.next();
                    for (Cell cell : r.rawCells()) {
                        rowkeys.add(CellUtil.cloneRow(cell));
                    }
                }
            }
            if (rowkeys.size() <= 0) return;
            // 2. 将前面获取的rowkey列表 遍历出来在微博内容邮件表中正式添加数据
            Table rceTBL = connection.getTable(TableName.valueOf(RECEIVE_CONTENT_EMAIL));
//            HTableInterface rceTBL = connection.getTable(RECEIVE_CONTENT_EMAIL);
            List<Put> rcePuts = new ArrayList<Put>();
            for (byte[] rk : rowkeys) {
                Put put = new Put(Bytes.toBytes(uid));
                String rowkey = Bytes.toString(rk);
                // substring 包前不包后
                String attend = rowkey.substring(0, rowkey.indexOf("_"));
                long timestamp = Long.parseLong(rowkey.substring(rowkey.indexOf("_") + 1));
                put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes(attend), timestamp, rk);
                rcePuts.add(put);
            }
            rceTBL.put(rcePuts);

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
     * 移除或者取消关注用户
     * a. 在微博用户关系表中 移除新的好友关注(attends)
     * b. 从被关注用户角度来说， 删除粉丝用户（fans）
     * c. 微博邮件箱表删除关注用户发布的微博内容
     *
     * @param uid     粉丝用户ID
     * @param attends 被关注用户ID
     */
    public void removeAttends(String uid, String... attends) throws IOException {
        //参数过滤
        if (attends == null || attends.length <= 0) return;
        Connection connection = getConnection();
        try {
            //a. 在微博用户关系表中 移除新的好友关注(attends)
            Table relationsTBL = connection.getTable(TableName.valueOf(RELATIONS));
//            HTableInterface relationsTBL = connection.getTable(RELATIONS);

            List<Delete> deletes = new ArrayList<Delete>();

            Delete attendsDelete = new Delete(Bytes.toBytes(uid));

            for (String attend : attends) {
                attendsDelete.deleteColumn(Bytes.toBytes("attends"), Bytes.toBytes(attend));

                //b. 从被关注用户角度来说， 删除粉丝用户（fans）
                Delete fansDelete = new Delete(Bytes.toBytes(attend));
                fansDelete.deleteColumn(Bytes.toBytes("fans"), Bytes.toBytes(uid));
                deletes.add(fansDelete);
            }

            deletes.add(attendsDelete);
            relationsTBL.delete(deletes);

            //c. 微博邮件箱表删除关注用户发布的微博内容
            Table rceTBL = connection.getTable(TableName.valueOf(RECEIVE_CONTENT_EMAIL));
//            HTableInterface rceTBL = connection.getTable(RECEIVE_CONTENT_EMAIL);
            Delete rceDelete = new Delete(Bytes.toBytes(uid));
            for (String attend : attends) {
                /**
                 * Delete the latest version of the specified column.
                 */
                // rceDelete.deleteColumn(Bytes.toBytes("cf"), Bytes.toBytes(attend));
                // Delete all versions of the specified column with a timestamp less than
                rceDelete.deleteColumns(Bytes.toBytes("cf"), Bytes.toBytes(attend), System.currentTimeMillis() + Integer.MAX_VALUE);

            }
            rceTBL.delete(rceDelete);

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
     * 粉丝用户 去获取关注用户发布的微博内容：
     * a. 从微博内容邮件表中获取该用户其关注用户的微博内容的rowkey
     * b. 根据上面获取到的微博内容的rowkey 获取微博内容
     *
     * @param uid 粉丝用户ID
     */
    public List<Message> getAttendsContens(String uid) throws IOException {
        Connection connection = getConnection();

        List<Message> messages = new ArrayList<Message>();
        try {
            //a. 从微博内容邮件表中获取该用户其关注用户的微博内容的rowkey
            Table rceTBL = connection.getTable(TableName.valueOf(RECEIVE_CONTENT_EMAIL));
//            HTableInterface rceTBL = connection.getTable(RECEIVE_CONTENT_EMAIL);
            Get get = new Get(Bytes.toBytes(uid));
            get.setMaxVersions(5);
            List<byte[]> rowkeys = new ArrayList<byte[]>();
            Result result = rceTBL.get(get);
            for (Cell cell : result.rawCells()) {

                //CellUtil.cloneValue      获取value
                //CellUtil.cloneRow        获取rowkey
                //CellUtil.cloneQualifier  获取列名
                //CellUtil.cloneFamily     获取到列族名
                rowkeys.add(CellUtil.cloneValue(cell));
            }
            //b. 根据上面获取到的微博内容的rowkey 获取微博内容
            Table contentTBL = connection.getTable(TableName.valueOf(CONTENT));
//            HTableInterface contentTBL = connection.getTable(CONTENT);
            List<Get> gets = new ArrayList<Get>();
            for (byte[] rk : rowkeys) {
                Get g = new Get(rk);
                gets.add(g);
            }
            Result[] results = contentTBL.get(gets);
            for (Result res : results) {
                for (Cell cell : res.rawCells()) {
                    Message message = new Message();
                    String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
                    String userid = rowkey.substring(0, rowkey.indexOf("_"));
                    String timestamp = rowkey.substring(rowkey.indexOf("_") + 1);
                    String content = Bytes.toString(CellUtil.cloneValue(cell));
                    message.setUid(userid);
                    message.setTimestamp(timestamp);
                    message.setContent(content);
                    messages.add(message);
                }
            }
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
        return messages;


    }

    public static void testInit(WeiBo wb) throws IOException {
        wb.initNamespace();
        wb.initTable();
    }

    public static void testPublishContent(WeiBo wb) throws IOException {
        wb.publishContent("0001", "Tomorrow will be better!");
        wb.publishContent("0001", "Tomorrow will be better!");
    }

    public static void testAddAttends(WeiBo wb) throws IOException {
        wb.publishContent("0008", "今天天气真不错！！！");
        wb.publishContent("0009", "今天天气真不错！！！");
        wb.addAttends("0001", "0008", "0009");
    }

    public static void testRemoveAttends(WeiBo wb) throws IOException {
        wb.removeAttends("0001", "0009");
    }

    public static void testGetAttendsContents(WeiBo wb) throws IOException {
        List<Message> messages = wb.getAttendsContens("0001");
        for (Message message : messages) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) throws IOException {
        WeiBo wb = new WeiBo();
//        testInit(wb);
        //testPublishContent(wb);
        testGetAttendsContents(wb);
//        testAddAttends(wb);
        //testRemoveAttends(wb);

    }
}
