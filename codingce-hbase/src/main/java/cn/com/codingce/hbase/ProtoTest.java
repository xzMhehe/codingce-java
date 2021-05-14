package cn.com.codingce.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * @author williamma
 */
public class ProtoTest {
    public static void main(String[] args) throws IOException {

//        createTable("usertest", "info");
        addRowData("usertest", "1002");

    }

    //获取 Configuration 对象
    public static Configuration conf;

    static {
        //使用 HBaseConfiguration 的单例方法实例化
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "139.9.34.48");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

    }

    /**
     * 获取连接
     *
     * @return
     * @throws IOException
     */
    public static Connection getConnection() throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        System.out.println("创建连接。。。" + connection);
        return connection;
    }

    /**
     * 向表中插入数据
     *
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void addRowData(String tableName,
                                  String rowKey) throws IOException {
        Connection connection = getConnection();

        //查询数据
        //获取指定表对象
        Table table = connection.getTable(TableName.valueOf(tableName));


        User.UserDetail.Builder userDetail = User.UserDetail.newBuilder();
//        userDetail.setUsername("Mike");
//        userDetail.setPassword("Mike");
        userDetail.setUsername("Nick");
        userDetail.setPassword("Nick");

        Put put = new Put(rowKey.getBytes());
        put.addColumn("info".getBytes(), "userDetail".getBytes(), userDetail.build().toByteArray());
        table.put(put);
        System.out.println("插入成功。。。");

    }

    /**
     * 创建表
     *
     * @param tableName
     * @param columnFamily
     * @throws MasterNotRunningException
     * @throws ZooKeeperConnectionException
     * @throws IOException
     */
    public static void createTable(String tableName, String... columnFamily) throws
            MasterNotRunningException, ZooKeeperConnectionException, IOException {

        Connection connection = getConnection();

        Admin admin = connection.getAdmin();


        //判断表是否存在
        if (isTableExist(tableName)) {
            System.out.println("表 " + tableName + "已存在");
            //System.exit(0);
        } else {
            //创建表
            //创建表属性对象,表名需要转字节
            HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
            //创建多个列族
            for (String cf : columnFamily) {
                descriptor.addFamily(new HColumnDescriptor(cf));
            }
            //根据对表的配置，创建表
            admin.createTable(descriptor);

            System.out.println("表 " + tableName + "创建成功!");
        }
    }

    /**
     * 判断表是否存在
     *
     * @param tableName
     * @return
     * @throws MasterNotRunningException
     * @throws ZooKeeperConnectionException
     * @throws IOException
     */
    public static boolean isTableExist(String tableName) throws MasterNotRunningException,
            ZooKeeperConnectionException, IOException {
        //在 HBase 中管理、访问表需要先创建 HBaseAdmin 对象
        //Connection connection = ConnectionFactory.createConnection(conf);
        // HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();
        boolean result = admin.tableExists(TableName.valueOf(tableName));
        System.out.println("表是否存在：" + result);
        return result;
    }
}
