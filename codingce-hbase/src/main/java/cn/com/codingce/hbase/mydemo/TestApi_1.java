package cn.com.codingce.hbase.mydemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceNotFoundException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 1.4.2 版本写法
 *
 * @author williamma
 */
public class TestApi_1 {

    public static Configuration conf;

    static {
        //使用 HBaseConfiguration 的单例方法实例化
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "139.9.34.48");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
    }

    public static void main(String[] args) throws IOException {

        // 获取连接
        //setClassLoader 类加载器
        //    public static Configuration addHbaseResources(Configuration conf) {
        //        conf.addResource("hbase-default.xml");
        //        conf.addResource("hbase-site.xml");
        //        checkDefaultsVersion(conf);
        //        return conf;
        //    }
        Connection connection = ConnectionFactory.createConnection(conf);

        System.out.println("============ 获取连接对象 ================");
        System.out.println(connection);

        // 获取操作对象 admin
        //new HBaseAdmin(connection);  不推荐使用

        Admin admin = connection.getAdmin();


        //操作数据库 判断数据库中有没有某张表
        //判断命名空间
        try {
            admin.getNamespaceDescriptor("codingce");
        } catch (NamespaceNotFoundException e) {
            //创建命名空间
            NamespaceDescriptor codingce = NamespaceDescriptor.create("codingce").build();
            admin.createNamespace(codingce);
        }

        //判断hbase中是否存在某张表
        TableName tableName = TableName.valueOf("codingce:user");
        boolean b = admin.tableExists(tableName);
        System.out.println("判断hbase中是否存在某张表: codingce:user" + " 是否存在：" + b);

        if (b) {
            //查询数据
            //获取指定表对象
            Table table = connection.getTable(tableName);
            String rowkey = "1006";
            //注意字符编码问题
            //Get get = new Get(rowkey.getBytes());
            Get get = new Get(Bytes.toBytes(rowkey));

            //查询结果
            Result result = table.get(get);
            boolean empty = result.isEmpty();
            System.out.println("是否存在：" + empty);
            if (empty) {
                //新增数据
                Put put = new Put(Bytes.toBytes(rowkey));
                String family = "info";

                String column = "sex";
                String val = "male";
                String column1 = "name";
                String val1 = "Mike";
                String column2 = "age";
                String val2 = "18";
                String column3 = "username";
                String val3 = "adminS";
                String column4 = " ";
                String val4 = "password";

                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(val));
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column1), Bytes.toBytes(val1));
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column2), Bytes.toBytes(val2));
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column3), Bytes.toBytes(val3));
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column4), Bytes.toBytes(val4));

                table.put(put);
                System.out.println("增加数据。。。");
            } else {
                //展示数据
                for (Cell cell : result.rawCells()) {
//                    cell.
                    System.out.println(" value " + Bytes.toString(CellUtil.cloneValue(cell)));
                    System.out.println(" rowkey " + Bytes.toString(CellUtil.cloneRow(cell)));
                    System.out.println(" family " + Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println(" column " + Bytes.toString(CellUtil.cloneQualifier(cell)));
                }
            }
        } else {
            //创建表
            //创建表的描述对象
            HTableDescriptor td = new HTableDescriptor(tableName);
            //增加列族

            HColumnDescriptor cd = new HColumnDescriptor("info");
            td.addFamily(cd);
            admin.createTable(td);
            System.out.println("创建完成");
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
    public static boolean isTableExist(String tableName, Connection connection) throws MasterNotRunningException,
            ZooKeeperConnectionException, IOException {
        //在 HBase 中管理、访问表需要先创建 HBaseAdmin 对象
        //Connection connection = ConnectionFactory.createConnection(conf); //HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        Admin admin = connection.getAdmin();
        //判断命名空间
        try {
            NamespaceDescriptor namespace = admin.getNamespaceDescriptor("xxx");
            if (namespace == null) {
                //创建表空间
                return true;
            }
        } catch (NamespaceNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return admin.tableExists(TableName.valueOf(tableName));
    }

    public static void createTable(String tableName, Connection connection, String... columnFamily) throws
            MasterNotRunningException, ZooKeeperConnectionException, IOException {
        Admin admin = connection.getAdmin();
        //判断表是否存在
        if (isTableExist(tableName, connection)) {
            System.out.println("表" + tableName + "已存在");
            //System.exit(0);
        } else {
            //创建表属性对象,表名需要转字节
            HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
            //创建多个列族
            for (String cf : columnFamily) {
                descriptor.addFamily(new HColumnDescriptor(cf));
            }
            //根据对表的配置，创建表 admin.createTable(descriptor); System.out.println("表" + tableName + "创建成功!");
        }
    }

}
