//package cn.com.codingce.hbase.dao;
//
//import cn.com.codingce.hbase.phone.Phone;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.Cell;
//import org.apache.hadoop.hbase.CellUtil;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.NamespaceDescriptor;
//import org.apache.hadoop.hbase.NamespaceNotFoundException;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Admin;
//import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
//import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.Delete;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.ResultScanner;
//import org.apache.hadoop.hbase.client.Scan;
//import org.apache.hadoop.hbase.client.Table;
//import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
//import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
//import org.apache.hadoop.hbase.filter.Filter;
//import org.apache.hadoop.hbase.filter.FilterList;
//import org.apache.hadoop.hbase.filter.PrefixFilter;
//import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.annotation.Nonnull;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Created by Feng on 2020/1/13 11:35
// * CurrentProject's name is hbase-demo<br>
// * Hbase API 简单封装
// *
// * 2.1.5 版本写法
// */
//public class HbasePhoneDao {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(HbasePhoneDao.class);
//    private static final HbasePhoneDao INSTANCE = new HbasePhoneDao();
//    private static volatile Connection connection = null;
//
//    private static volatile Admin admin = null;
//
//    /*
//     * 初始化数据：配置信息；获取 connection 对象
//     */
//    static {
//        Configuration configuration = HBaseConfiguration.create();
//        configuration.set("hbase.zookeeper.quorum", "139.9.34.48");
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        System.setProperty("hadoop.home.dir", "/usr/local/hadoop/hadoop-3.1.1");
//
//        try {
//            connection = ConnectionFactory.createConnection(
//                    configuration,
//                    new ThreadPoolExecutor(
//                            20,
//                            100,
//                            60,
//                            TimeUnit.MINUTES,
//                            new LinkedBlockingQueue<>(20),
//                            new ThreadFactoryImpl("cn.com.codingce.habase.demo")
//                    )
//            );
//        } catch (IOException e) {
//            LOGGER.error("Create connection or admin error! " + e.getMessage(), e);
//        }
//    }
//
//    private HbasePhoneDao() {
//    }
//
//    public static HbasePhoneDao getInstance() {
//        return INSTANCE;
//    }
//
//
//    /**
//     * 初始化命名空间：若命名空间存在则不创建
//     *
//     * @param namespace 命名空间
//     */
//    public void createNameSpace(String namespace) throws IOException {
//        try {
//            admin = connection.getAdmin();
//            admin.getNamespaceDescriptor(namespace);
//            LOGGER.error("NameSpace {} is exist!", namespace);
//        } catch (NamespaceNotFoundException e) {
//            admin.createNamespace(NamespaceDescriptor.create(namespace).build());
//            LOGGER.info("Created namespace: {}", namespace);
//        }
//    }
//
//    /**
//     * 删除表：先禁用再删除
//     *
//     * @param name 表名
//     * @throws IOException io操作
//     */
//    public void deleteTable(String name) throws IOException {
//        TableName tableName = TableName.valueOf(name);
//        admin = connection.getAdmin();
//        admin.disableTable(tableName);
//        admin.deleteTable(tableName);
//        LOGGER.info("Deleted table {} !", name);
//    }
//
//    /**
//     * 创建表：表存在时，先删除再创建；
//     * 分区数为默认
//     *
//     * @param tableName    表名
//     * @param columnFamily 列族
//     * @throws IOException io操作
//     */
//    public void createTable(String tableName, String... columnFamily) throws IOException {
//        createTable(tableName, 0, columnFamily);
//    }
//
//
//    /**
//     * 创建表：表存在时，先删除再创建
//     *
//     * @param tableName    表名
//     * @param regionCount  分区数
//     * @param columnFamily 列族
//     * @throws IOException io操作
//     */
//    public void createTable(String tableName, int regionCount, String... columnFamily) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        admin = connection.getAdmin();
//        // 存在
//        if (admin.tableExists(name)) {
//            LOGGER.error("Table named {} already exist!", name);
//            deleteTable(tableName);
//        }
//
//        createTableTemplate(name, regionCount, columnFamily);
//    }
//
//    /**
//     * 表是否存在
//     *
//     * @param tableName 表名
//     */
//    public boolean tableExists(String tableName) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        return getAdmin().tableExists(name);
//    }
//
//
//    /**
//     * 插入数据：单行、单列族 => 多列多值
//     *
//     * @param tableName    表名
//     * @param rowKey       行
//     * @param columnFamily 列族
//     * @param columns      列
//     * @param values       值(与列一一对应)
//     */
//    public void insertRecords(String tableName, String rowKey, String columnFamily, String[] columns, String[] values)
//            throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        Put put = new Put(Bytes.toBytes(rowKey));
//
//        for (int i = 0; i < columns.length; i++) {
//            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
//            table.put(put);
//        }
//    }
//
//    /**
//     * 插入数据：单行、单列族 => 单列单值
//     *
//     * @param tableName    表名
//     * @param rowKey       行
//     * @param columnFamily 列族
//     * @param value        列值
//     */
//    public void insertRecord(String tableName, String rowKey, String columnFamily, String column, String value) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        Put put = new Put(Bytes.toBytes(rowKey));
//
//        put.addColumn(
//                Bytes.toBytes(columnFamily),
//                Bytes.toBytes(column),
//                Bytes.toBytes(value));
//        table.put(put);
//    }
//
//    /**
//     * 通话记录实例-生成并插入通话记录数据
//     */
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//    Random r = new Random();
//
//    public void insertPhoneRecord(String tableName) throws Exception {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//
//        List<Put> puts = new ArrayList<Put>();
//        for (int i = 0; i < 10; i++) {//生成10个用户的手机号码，他们都是186用户
//            String phoneNum = getPhoneNum("186");
//            for (int j = 0; j < 100; j++) {//生成10个用户的手机号码的通话记录
//                String dnum = getPhoneNum("158");//对方手机号生成
//                String length = r.nextInt(99) + "";//通话时长
//                String type = r.nextInt(2) + "";//通话类型（主叫：1、被叫：0）
//                String dateStr = getDate("2020");//通话时间，我们设置固定的年，月日 时分秒随机生成
//
//                //将获取的年月日 时分秒通过getTime()生成时间戳
//                String rowkey = phoneNum + "_" + (Long.MAX_VALUE - sdf.parse(dateStr).getTime());//时间戳是long类型值
//                Phone.PhoneDetail.Builder phoneDetail = Phone.PhoneDetail.newBuilder();
//                phoneDetail.setDate(dateStr);
//                phoneDetail.setDnum(dnum);
//                phoneDetail.setLength(length);
//                phoneDetail.setType(type);
//                Put put = new Put(rowkey.getBytes());
//                put.addColumn("cf".getBytes(), "phoneDetail".getBytes(), phoneDetail.build().toByteArray());
//                puts.add(put);
//            }
//        }
//        table.put(puts);//添加测试数据到数据表phone
//    }
//
//    /**
//     * 通话记录实例-生成随机的手机号码
//     *
//     * @param three 传递进来的手机号前三位，后面的8位随机生成
//     * @return
//     */
//    private String getPhoneNum(String three) {
//        //随机生成8位十进制随机数
//        return three + String.format("%08d", r.nextInt(99999999));
//    }
//
//    /**
//     * 通话记录实例-生成当前时间：年月日时分秒
//     */
//    private String getDate(String year) {
//        return year + String.format("%02d%02d%02d%02d%02d",
//                new Object[]{r.nextInt(12) + 1, r.nextInt(31) + 1, r.nextInt(24), r.nextInt(60), r.nextInt(60)});
//    }
//
//    /**
//     * 通话记录实例-
//     * 通过scan全表检索查询某个手机号二月份到三月份的通话记录
//     *
//     * @throws Exception
//     */
//    public void scanPhone() throws Exception {
//        TableName name = TableName.valueOf("phone");
//        Table table = connection.getTable(name);
//        //查询的通话记录的所属手机号
//        String phoneNum = "18683059215";
//        //查询通话记录的起始时间（因为rowkey是按字节顺序降序排列，最近的月份排在上面，所以startRow写三月）
//        String startRow = phoneNum + "_" + (Long.MAX_VALUE - sdf.parse("20200301000000").getTime());
//        //查询通话记录的结束时间
//        String stopRow = phoneNum + "_" + (Long.MAX_VALUE - sdf.parse("20200201000000").getTime());
//        Scan scan = new Scan();
//        scan.setStartRow(startRow.getBytes());
//        scan.setStopRow(stopRow.getBytes());
//        ResultScanner rss = table.getScanner(scan);
//
//        for (Result rs : rss) {
//            /*
//             * rs.getColumnLatestCell("cf".getBytes(), "dnum".getBytes())获取dnum字段
//             * CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "dnum".getBytes()))获取字段的值
//             * */
//            System.out
//                    .print(new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "dnum".getBytes()))));
//            System.out.print("-"
//                    + new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "length".getBytes()))));
//            System.out.print(
//                    "-" + new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "type".getBytes()))));
//            System.out.println(
//                    "-" + new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "date".getBytes()))));
//        }
//    }
//
//    /**
//     * 查询某个手机号主叫为1 的所有记录
//     *
//     * @throws Exception
//     */
//    public void scanPhone2() throws Exception {
//        TableName name = TableName.valueOf("phone");
//        Table table = connection.getTable(name);
//
//        /* 组装查询条件 */
//        //创建过滤器集合（用来存放过滤条件的），MUST_PASS_ALL代表所有的条件都必须满足
//        FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//        //第一个条件（查询的手机号是：18682958934，之所以使用前缀过滤器，是因为我们的rowkey命名设计时手机号正好是前缀），
//        //通过前缀过滤器可以把所有以该手机号的记录查询出来，其实就是完成了第一个条件的查询
//        PrefixFilter filter1 = new PrefixFilter("18683059215".getBytes());
//        //第二个条件（查询的是type字段=1的记录，在第一个条件的基础上我们查询所有该手机记录中type=1的记录，所以第二个条件），
//        //我们使用单一列值过滤器。EQUAL就是等于号
//        SingleColumnValueFilter filter2 = new SingleColumnValueFilter("cf".getBytes(), "type".getBytes(),
//                CompareOp.EQUAL, "1".getBytes());
//        list.addFilter(filter1);//将第一个条件放入过滤器集合中
//        list.addFilter(filter2);//将第二个条件放入过滤器集合中
//
//        Scan scan = new Scan();//创建扫描器
//        scan.setFilter(list);//给扫描器添加两个条件
//        ResultScanner rss = table.getScanner(scan);//扫描开始
//        for (Result rs : rss) {//循环结果
//            System.out
//                    .print(new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "dnum".getBytes()))));
//            System.out.print("-"
//                    + new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "length".getBytes()))));
//            System.out.print(
//                    "-" + new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "type".getBytes()))));
//            System.out.println(
//                    "-" + new String(CellUtil.cloneValue(rs.getColumnLatestCell("cf".getBytes(), "date".getBytes()))));
//        }
//    }
//
//
//    /**
//     * 删除一份数据
//     *
//     * @param tableName 表名
//     * @param rowKey    行名
//     */
//    public void deleteRow(String tableName, String rowKey) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        table.delete(new Delete(rowKey.getBytes()));
//    }
//
//
//    /**
//     * 删除单行单列族记录
//     *
//     * @param tableName    表名
//     * @param rowKey       行名
//     * @param columnFamily 列族
//     */
//    public void deleteColumnFamily(String tableName, String rowKey, String columnFamily) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        table.delete(new Delete(rowKey.getBytes()).addFamily(Bytes.toBytes(columnFamily)));
//    }
//
//    /**
//     * 删除单行单列族单列记录
//     *
//     * @param tableName    表名
//     * @param rowKey       行名
//     * @param columnFamily 列族
//     * @param column       列
//     */
//    public void deleteColumn(String tableName, String rowKey, String columnFamily, String column) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        table.delete(new Delete(rowKey.getBytes()).addColumn(Bytes.toBytes(columnFamily),
//                Bytes.toBytes(column)));
//    }
//
//    public void delete(String tableName, Delete delete) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        table.delete(delete);
//    }
//
//    public void deleteList(String tableName, List<Delete> delete) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        table.delete(delete);
//    }
//
//    /**
//     * 查找一行记录
//     *
//     * @param tableName 表名
//     * @param rowKey    行名
//     * @return 结果
//     */
//    public String selectRow(String tableName, String rowKey) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//
//        Result result = table.get(new Get(rowKey.getBytes()));
//        StringBuffer sb = new StringBuffer();
//        resultToString(sb, result);
//
//        return sb.toString();
//    }
//
//    /**
//     * 查找一行记录(无返回值)
//     *
//     * @param tableName 表名
//     * @param rowKey    行名
//     * @return 结果
//     */
//    public void selectRowPrint(String tableName, String rowKey) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//
//        Result result = table.get(new Get(rowKey.getBytes()));
//        showCell(result);
//        table.close();
//    }
//
//    /**
//     * 查询单行、单列族、单列的值
//     *
//     * @param tableName    表名
//     * @param rowKey       行名
//     * @param columnFamily 列族
//     * @param column       列名
//     * @return 列值
//     */
//    public String selectValue(String tableName, String rowKey, String columnFamily, String column) throws IOException {
//        Get get = new Get(rowKey.getBytes());//HBase中存放的所有数据都是byte类型，若想使用必须改变类型
//        get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
//
//        TableName name = TableName.valueOf(tableName);
//        Result result = connection.getTable(name).get(get);
//        return Bytes.toString(result.value());
//    }
//
//    /**
//     * 全表扫描
//     *
//     * @param tableName 表名
//     * @see Scan
//     */
//    public String scanAllRecord(String tableName) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//
//        Scan scan = new Scan();
//        StringBuffer sb = new StringBuffer();
//        try (ResultScanner scanner = table.getScanner(scan)) {
//            for (Result result : scanner) {
//                resultToString(sb, result);
//            }
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 获取一个表所有内容
//     *
//     * @param tableName
//     * @throws IOException
//     */
//    public void scanData(String tableName) throws IOException {
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//        Scan scan = new Scan();
//        ResultScanner resultScanner = table.getScanner(scan);
//        for (Result result : resultScanner) {
//            showCell(result);
//        }
//        table.close();
//    }
//
//    /**
//     * 拼接结果
//     */
//    private void resultToString(StringBuffer sb, Result result) {
//        for (Cell cell : result.rawCells()) {
//            sb.append(Bytes.toString(cell.getRowArray())).append("\t")
//                    .append(Bytes.toString(cell.getFamilyArray())).append("\t")
//                    .append(Bytes.toString(cell.getQualifierArray())).append("\t")
//                    .append(Bytes.toString(cell.getValueArray())).append("\n");
//        }
//    }
//
//    /**
//     * 格式化输出
//     *
//     * @param result
//     */
//    public void showCell(Result result) {
//        Cell[] cells = result.rawCells();
//        for (Cell cell : cells) {
//            System.out.print("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//            System.out.print(",Timetamp:" + cell.getTimestamp() + " ");
//            System.out.print(",column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
//            System.out.print(",row Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");
//            System.out.print(",value:" + new String(CellUtil.cloneValue(cell)) + " ");
//            System.out.println();
//        }
//    }
//
//    /**
//     * 过滤器扫描：参考
//     * https://www.cnblogs.com/Transkai/p/10727257.html<br>
//     * 该 API 较老，使用时要注意查看过时方法上的注释，找新的 API 使用
//     *
//     * @param tableName 表名
//     * @param filter    过滤器
//     */
//    public List<Cell> scanByFilter(String tableName, Filter filter) throws IOException {
//        List<Cell> resultList = new ArrayList<>();
//        TableName name = TableName.valueOf(tableName);
//        Table table = connection.getTable(name);
//
//        Scan scan = new Scan();
//        scan.setFilter(filter);
//        try (ResultScanner scanner = table.getScanner(scan)) {
//            for (Result result : scanner) {
//                resultList.addAll(Arrays.asList(result.rawCells()));
//            }
//        }
//
//        return resultList;
//    }
//
//
//    /**
//     * 创建表
//     *
//     * @param tableName    表名
//     * @param regionCount  分区数
//     * @param columnFamily 列族
//     */
//    private void createTableTemplate(TableName tableName, int regionCount, String... columnFamily) {
//        try {
//            admin = connection.getAdmin();
//            TableDescriptorBuilder tableBuilder = TableDescriptorBuilder.newBuilder(tableName);
//            // 增加列族
//            tableBuilder.setColumnFamilies(createColumnFamilyList(columnFamily));
//
//            // 无分区（未指定）
//            if (regionCount <= 0) {
//                admin.createTable(tableBuilder.build());
//            } else {
//                // 预分区
//                byte[][] splitKey = getSplitKeys(regionCount);
//                admin.createTable(tableBuilder.build(), splitKey);
//            }
//            LOGGER.info("Created table named {}", tableName);
//        } catch (IOException e) {
//            LOGGER.error("Create table error, " + e.getMessage(), e);
//        }
//    }
//
//    /**
//     * 列族描述器：没有内容时，自定义加一个列族 info
//     *
//     * @param columnFamily 列族名
//     * @return 列族描述器
//     */
//    private List<ColumnFamilyDescriptor> createColumnFamilyList(String... columnFamily) {
//        List<ColumnFamilyDescriptor> results = new ArrayList<>();
//        // 设置默认列族 info
//        if (columnFamily == null || columnFamily.length == 0) {
//            columnFamily = new String[]{"info"};
//        }
//
//        for (String family : columnFamily) {
//            ColumnFamilyDescriptorBuilder descriptorBuilder =
//                    ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));
//
//            results.add(descriptorBuilder.setBlockCacheEnabled(true).build());
//        }
//
//        return results;
//    }
//
//    /**
//     * 生成分区键
//     *
//     * @param regionCount 分区数
//     * @return 多个分区键
//     */
//    private byte[][] getSplitKeys(int regionCount) {
//        int splitKeyCount = regionCount - 1;
//        byte[][] bytes = new byte[splitKeyCount][];
//
//        List<byte[]> byteList = new ArrayList<>();
//        for (int i = 0; i < splitKeyCount; i++) {
//            String key = i + "|";
//            byteList.add(Bytes.toBytes(key));
//        }
//
//        byteList.toArray(bytes);
//        return bytes;
//    }
//
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public Admin getAdmin() {
//        try {
//            admin = connection.getAdmin();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return admin;
//    }
//
//
//    /**
//     * 用于创建线程池。
//     * ThreadFactory实现类：重命名线程
//     *
//     * @see ThreadFactory
//     */
//    private static class ThreadFactoryImpl implements ThreadFactory {
//        private final String name;
//        private AtomicInteger id = new AtomicInteger(1);
//
//        Map<String, Object> map = new HashMap<>();
//
//        private ThreadFactoryImpl(String name) {
//            this.name = "ThreadFactory-" + name + "-" + id.getAndIncrement();
//        }
//
//        @Override
//        public Thread newThread(@Nonnull Runnable runnable) {
//            return new Thread(runnable, name);
//        }
//    }
//}