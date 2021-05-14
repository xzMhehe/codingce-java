package cn.com.codingce.hbase.weibo;

import org.apache.hadoop.conf.Configuration;
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
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 微博案例
 *
 * @author williamma
 */
public class WeiBoConf {

    //获取配置conf
    private Configuration conf = HBaseConfiguration.create();

    //获取 Configuration 对象
    private static Configuration myconf;

    static {
        //使用 HBaseConfiguration 的单例方法实例化
        myconf = HBaseConfiguration.create();
        myconf.set("hbase.zookeeper.quorum", "139.9.34.48");
        myconf.set("hbase.zookeeper.property.clientPort", "2181");
    }


    //微博内容表的表名
    private static final byte[] TABLE_CONTENT = Bytes.toBytes("weibo:content");
    //用户关系表的表名
    private static final byte[] TABLE_RELATIONS = Bytes.toBytes("weibo:relations");
    //微博收件箱表的表名
    private static final byte[] TABLE_RECEIVE_CONTENT_EMAIL = Bytes.toBytes("weibo:receive_content_email");


    public static void main(String[] args) throws IOException {
        WeiBoConf weiBoConf = new WeiBoConf();
//        myConfig.initNamespace();
//        myConfig.createTableContent();
//        weiBoConf.createTableRelations();
//        weiBoConf.createTableReceiveContentEmail();
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
     * 创建微博内容表
     * Table Name:weibo:content
     * RowKey:用户ID_时间戳
     * ColumnFamily:info
     * ColumnLabel:标题	内容		图片URL
     * Version:1个版本
     */
    public void createTableContent() throws IOException {
        Connection connection = getConnection();
        Admin admin = null;
        try {
            admin = connection.getAdmin();
            //创建表表述
            HTableDescriptor content = new HTableDescriptor(TableName.valueOf(TABLE_CONTENT));
            //创建列族描述
            HColumnDescriptor info = new HColumnDescriptor(Bytes.toBytes("info"));
            //设置块缓存
            info.setBlockCacheEnabled(true);
            //设置块缓存大小
            info.setBlocksize(2097152);
            //设置压缩方式
//			info.setCompressionType(Algorithm.SNAPPY);
            //设置版本确界
            info.setMaxVersions(1);
            info.setMinVersions(1);

            content.addFamily(info);
            admin.createTable(content);
            System.out.println("微博内容表创建成功");

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
     * 用户关系表
     * Table Name:weibo:relations
     * RowKey:用户ID
     * ColumnFamily:attends,fans
     * ColumnLabel:关注用户ID，粉丝用户ID
     * ColumnValue:用户ID
     * Version：1个版本
     */
    public void createTableRelations() throws IOException {
        Connection connection = getConnection();
        Admin admin = null;
        try {
            admin = connection.getAdmin();
            HTableDescriptor relations = new HTableDescriptor(TableName.valueOf(TABLE_RELATIONS));

            //关注的人的列族
            HColumnDescriptor attends = new HColumnDescriptor(Bytes.toBytes("attends"));
            //设置块缓存
            attends.setBlockCacheEnabled(true);
            //设置块缓存大小
            attends.setBlocksize(2097152);
            //设置压缩方式
//			info.setCompressionType(Algorithm.SNAPPY);
            //设置版本确界
            attends.setMaxVersions(1);
            attends.setMinVersions(1);

            //粉丝列族
            HColumnDescriptor fans = new HColumnDescriptor(Bytes.toBytes("fans"));
            fans.setBlockCacheEnabled(true);
            fans.setBlocksize(2097152);
            fans.setMaxVersions(1);
            fans.setMinVersions(1);


            relations.addFamily(attends);
            relations.addFamily(fans);
            admin.createTable(relations);
            System.out.println("用户关系表创建成功");
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
     * 创建微博收件箱表
     * Table Name: weibo:receive_content_email
     * RowKey:用户ID
     * ColumnFamily:info
     * ColumnLabel:用户ID-发布微博的人的用户ID
     * ColumnValue:关注的人的微博的RowKey
     * Version:1000
     */
    public void createTableReceiveContentEmail() throws IOException {
        Connection connection = getConnection();
        Admin admin = null;
        try {
            admin = connection.getAdmin();
            HTableDescriptor receive_content_email = new HTableDescriptor(TableName.valueOf(TABLE_RECEIVE_CONTENT_EMAIL));
            HColumnDescriptor info = new HColumnDescriptor(Bytes.toBytes("info"));

            info.setBlockCacheEnabled(true);
            info.setBlocksize(2097152);
            info.setMaxVersions(1000);
            info.setMinVersions(1000);

            receive_content_email.addFamily(info);
            ;
            admin.createTable(receive_content_email);
            System.out.println("微博收件箱表创建成功");
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
}
