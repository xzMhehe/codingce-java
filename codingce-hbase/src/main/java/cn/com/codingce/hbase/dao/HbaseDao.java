package cn.com.codingce.hbase.dao;

import cn.com.codingce.hbase.conf.HbaseConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceNotFoundException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Hbase API 简单封装
 *
 * 2.1.5 版本写法
 */
public class HbaseDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseDao.class);
    private static final HbaseDao INSTANCE = new HbaseDao();
    private static volatile Connection connection = null;

    private static volatile Admin admin = null;

    /*
     * 初始化数据：配置信息；获取 connection 对象
     */
    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", HbaseConfig.QUORUM);
        configuration.set("hbase.zookeeper.property.clientPort", HbaseConfig.CLIENT_PORT);
        try {
            connection = ConnectionFactory.createConnection(
                    configuration,
                    new ThreadPoolExecutor(
                            20,
                            100,
                            60,
                            TimeUnit.MINUTES,
                            new LinkedBlockingQueue<Runnable>(20),
                            new ThreadFactoryImpl("org.feng.hbase.util")
                    )
            );
        } catch (IOException e) {
            LOGGER.error("Create connection or admin error! " + e.getMessage(), e);
        }
    }

    private HbaseDao() {
    }

    public static HbaseDao getInstance() {
        return INSTANCE;
    }


    /**
     * 初始化命名空间：若命名空间存在则不创建
     *
     * @param namespace 命名空间
     */
    public void createNameSpace(String namespace) throws IOException {
        try {
            admin = connection.getAdmin();
            admin.getNamespaceDescriptor(namespace);
            LOGGER.error("NameSpace {} is exist!", namespace);
        } catch (NamespaceNotFoundException e) {
            admin.createNamespace(NamespaceDescriptor.create(namespace).build());
            LOGGER.info("Created namespace: {}", namespace);
        }
    }

    /**
     * 删除表：先禁用再删除
     *
     * @param name 表名
     * @throws IOException io操作
     */
    public void deleteTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        admin = connection.getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
        LOGGER.info("Deleted table {} !", name);
    }

    /**
     * 创建表：表存在时，先删除再创建；
     * 分区数为默认
     *
     * @param tableName    表名
     * @param columnFamily 列族
     * @throws IOException io操作
     */
    public void createTable(String tableName, String... columnFamily) throws IOException {
        createTable(tableName, 0, columnFamily);
    }


    /**
     * 创建表：表存在时，先删除再创建
     *
     * @param tableName    表名
     * @param regionCount  分区数
     * @param columnFamily 列族
     * @throws IOException io操作
     */
    public void createTable(String tableName, int regionCount, String... columnFamily) throws IOException {
        TableName name = TableName.valueOf(tableName);
        admin = connection.getAdmin();
        // 存在
        if (admin.tableExists(name)) {
            LOGGER.error("Table named {} already exist!", name);
            deleteTable(tableName);
        }

        createTableTemplate(name, regionCount, columnFamily);
    }

    /**
     * 表是否存在
     *
     * @param tableName 表名
     */
    public boolean tableExists(String tableName) throws IOException {
        TableName name = TableName.valueOf(tableName);
        return getAdmin().tableExists(name);
    }


    /**
     * 插入数据：单行、单列族 => 多列多值
     *
     * @param tableName    表名
     * @param rowKey       行
     * @param columnFamily 列族
     * @param columns      列
     * @param values       值(与列一一对应)
     */
    public void insertRecords(String tableName, String rowKey, String columnFamily, String[] columns, String[] values) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Put put = new Put(Bytes.toBytes(rowKey));

        for (int i = 0; i < columns.length; i++) {
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
            table.put(put);
        }
    }

    /**
     * 插入数据：单行、单列族 => 单列单值
     *
     * @param tableName    表名
     * @param rowKey       行
     * @param columnFamily 列族
     * @param column       列名
     * @param value        列值
     */
    public void insertRecord(String tableName, String rowKey, String columnFamily, String column, String value) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Put put = new Put(Bytes.toBytes(rowKey));

        put.addColumn(
                Bytes.toBytes(columnFamily),
                Bytes.toBytes(column),
                Bytes.toBytes(value));
        table.put(put);
    }


    /**
     * 删除一份数据
     *
     * @param tableName 表名
     * @param rowKey    行名
     */
    public void deleteRow(String tableName, String rowKey) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        table.delete(new Delete(rowKey.getBytes()));
    }


    /**
     * 删除单行单列族记录
     *
     * @param tableName    表名
     * @param rowKey       行名
     * @param columnFamily 列族
     */
    public void deleteColumnFamily(String tableName, String rowKey, String columnFamily) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        table.delete(new Delete(rowKey.getBytes()).addFamily(Bytes.toBytes(columnFamily)));
    }

    /**
     * 删除单行单列族单列记录
     *
     * @param tableName    表名
     * @param rowKey       行名
     * @param columnFamily 列族
     * @param column       列
     */
    public void deleteColumn(String tableName, String rowKey, String columnFamily, String column) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        table.delete(new Delete(rowKey.getBytes()).addColumn(Bytes.toBytes(columnFamily),
                Bytes.toBytes(column)));
    }

    public void delete(String tableName, Delete delete) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        table.delete(delete);
    }

    public void deleteList(String tableName, List<Delete> delete) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        table.delete(delete);
    }

    /**
     * 查找一行记录(无返回值)
     *
     * @param tableName 表名
     * @param rowKey    行名
     * @return 结果
     */
    public void selectRowPrint(String tableName, String rowKey) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);

        Result result = table.get(new Get(rowKey.getBytes()));
        showCell(result);
        table.close();
    }

    /**
     * 查询单行、单列族、单列的值
     *
     * @param tableName    表名
     * @param rowKey       行名
     * @param columnFamily 列族
     * @param column       列名
     * @return 列值
     */
    public String selectValue(String tableName, String rowKey, String columnFamily, String column) throws IOException {
        Get get = new Get(rowKey.getBytes());
        get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));

        TableName name = TableName.valueOf(tableName);
        Result result = connection.getTable(name).get(get);
        return Bytes.toString(result.value());
    }

    /**
     * 获取一个表所有内容
     *
     * @param tableName
     * @throws IOException
     */
    public void scanData(String tableName) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);
        Scan scan = new Scan();
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            showCell(result);
        }
        table.close();
    }

    /**
     * 全表扫描
     *
     * @param tableName 表名
     * @see Scan
     */
    public String scanAllRecord(String tableName) throws IOException {
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);

        Scan scan = new Scan();
        StringBuffer sb = new StringBuffer();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            resultToString(sb, result);
        }
        scanner.close();
        return sb.toString();
    }

    /**
     * 格式化输出
     *
     * @param result
     */
    public void showCell(Result result) {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.print("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
            System.out.print(",Timetamp:" + cell.getTimestamp() + " ");
            System.out.print(",column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
            System.out.print(",row Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");
            System.out.print(",value:" + new String(CellUtil.cloneValue(cell)) + " ");
            System.out.println();
        }
    }

    /**
     * 拼接结果
     */
    private void resultToString(StringBuffer sb, Result result) {
        for (Cell cell : result.rawCells()) {
            sb.append(Bytes.toString(cell.getRowArray())).append("\t")
                    .append(Bytes.toString(cell.getFamilyArray())).append("\t")
                    .append(Bytes.toString(cell.getQualifierArray())).append("\t")
                    .append(Bytes.toString(cell.getValueArray())).append("\n");
        }
    }

    /**
     * 过滤器扫描：参考
     * https://www.cnblogs.com/Transkai/p/10727257.html<br>
     * 该 API 较老，使用时要注意查看过时方法上的注释，找新的 API 使用
     *
     * @param tableName 表名
     * @param filter    过滤器
     */
    public List<Cell> scanByFilter(String tableName, Filter filter) throws IOException {
        List<Cell> resultList = new ArrayList<Cell>();
        TableName name = TableName.valueOf(tableName);
        Table table = connection.getTable(name);

        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            resultList.addAll(Arrays.asList(result.rawCells()));
        }
        scanner.close();

        return resultList;
    }


    /**
     * 创建表
     *
     * @param tableName    表名
     * @param regionCount  分区数
     * @param columnFamily 列族
     */
    private void createTableTemplate(TableName tableName, int regionCount, String... columnFamily) {
        try {
            admin = connection.getAdmin();
            TableDescriptorBuilder tableBuilder = TableDescriptorBuilder.newBuilder(tableName);
            // 增加列族
            tableBuilder.setColumnFamilies(createColumnFamilyList(columnFamily));

            // 无分区（未指定）
            if (regionCount <= 0) {
                admin.createTable(tableBuilder.build());
            } else {
                // 预分区
                byte[][] splitKey = getSplitKeys(regionCount);
                admin.createTable(tableBuilder.build(), splitKey);
            }
            LOGGER.info("Created table named {}", tableName);
        } catch (IOException e) {
            LOGGER.error("Create table error, " + e.getMessage(), e);
        }
    }

    /**
     * 列族描述器：没有内容时，自定义加一个列族 info
     *
     * @param columnFamily 列族名
     * @return 列族描述器
     */
    private List<ColumnFamilyDescriptor> createColumnFamilyList(String... columnFamily) {
        List<ColumnFamilyDescriptor> results = new ArrayList<ColumnFamilyDescriptor>();
        // 设置默认列族 info
        if (columnFamily == null || columnFamily.length == 0) {
            columnFamily = new String[]{"info"};
        }

        for (String family : columnFamily) {
            ColumnFamilyDescriptorBuilder descriptorBuilder =
                    ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));

            results.add(descriptorBuilder.setBlockCacheEnabled(true).build());
        }

        return results;
    }

    /**
     * 生成分区键
     *
     * @param regionCount 分区数
     * @return 多个分区键
     */
    private byte[][] getSplitKeys(int regionCount) {
        int splitKeyCount = regionCount - 1;
        byte[][] bytes = new byte[splitKeyCount][];

        List<byte[]> byteList = new ArrayList<byte[]>();
        for (int i = 0; i < splitKeyCount; i++) {
            String key = i + "|";
            byteList.add(Bytes.toBytes(key));
        }

        byteList.toArray(bytes);
        return bytes;
    }


    public Connection getConnection() {
        return connection;
    }

    public Admin getAdmin() {
        try {
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admin;
    }


    /**
     * 用于创建线程池。
     * ThreadFactory实现类：重命名线程
     *
     * @see ThreadFactory
     */
    private static class ThreadFactoryImpl implements ThreadFactory {
        private final String name;
        private AtomicInteger id = new AtomicInteger(1);

        private ThreadFactoryImpl(String name) {
            this.name = "ThreadFactory-" + name + "-" + id.getAndIncrement();
        }

        @Override
        public Thread newThread(@Nonnull Runnable runnable) {
            return new Thread(runnable, name);
        }
    }
}


