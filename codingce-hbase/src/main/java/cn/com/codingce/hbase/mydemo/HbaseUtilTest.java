//package cn.com.codingce.hbase.mydemo;
//
//import cn.com.codingce.hbase.dao.HbaseDao;
//import org.apache.hadoop.hbase.CompareOperator;
//import org.apache.hadoop.hbase.filter.BinaryComparator;
//import org.apache.hadoop.hbase.filter.Filter;
//import org.apache.hadoop.hbase.filter.QualifierFilter;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
///**
// * 测试
// *
// * 2.1.5 版本写法
// */
//public class HbaseUtilTest {
//
//    private HbaseDao dao = null;
//
//    @Before
//    public void init() {
//        dao = HbaseDao.getInstance();
//    }
//
//    @Test
//    public void run() throws IOException {
//        //---------------------通过HbaseUtilTest的函数间接调用HbaseDao的方法操纵数据库，模拟类之间的调用-----
//        //testCreateNameSpace();//创建数据表空间
//        //createTableTest();//创建数据表
//        //insertRecordTest();//插入多条数据记录
//        //insertRecordsTest();//给一条数据记录插入多列
//        //deleteColumnTest();//删除记录的列族
//        //deleteTableTest();//根据表名删除数据表
//        //--------------------以下直接调用HbaseDao的方法操纵数据库-------------------------
//        //删除记录的列族
//        //dao.deleteColumnFamily("ibm_space:ibm_user","1002","info");//删除1002这条记录的列族info
//        //dao.deleteColumn("ibm_space:ibm_user","1001","info","username");//删除单行单列族单列记录
//        //dao.deleteRow("ibm_space:ibm_user","1001");//删除一条记录
//        //插入一行多列的值
//        /*String[] fileds = {"name","age","gender"};
//        String[] values = {"wanghai","22","男"};
//        dao.insertRecords("ibm_space:ibm_user","1001","info",fileds,values);*/
//        //dao.selectRowPrint("ibm_space:ibm_user","1001");//查找一条记录
//        //dao.scanData("ibm_space:ibm_user");//查询一张表的所有内容
//        //查询单行、单列族、单列的值
//        /*String age = dao.selectValue("ibm_space:ibm_user","1001","info","age");
//        System.out.println(age);*/
//        //更新单行单列的值
//        //dao.insertRecord("ibm_space:ibm_user","1001","info","age","30");
//    }
//
//    @Test
//    public void testConnection() {
//        Assert.assertNotNull(dao.getConnection());
//    }
//
//    @Test
//    public void testAdmin() {
//        Assert.assertNotNull(dao.getAdmin());
//    }
//
//    /**
//     * 创建表空间
//     */
//    @Test
//    public void testCreateNameSpace() throws IOException {
//        // 不存在，创建
//        dao.createNameSpace("ibm_space");
//        // 存在，不创建
//        dao.createNameSpace("ibm_space");
//        //断言方法：判断一个对象是否为空，如果结果与预期相同，程序继续运行，否则抛出异常。
//        Assert.assertNotNull(dao.getAdmin().getNamespaceDescriptor("ibm_namespace"));
//    }
//
//    /**
//     * 创建表；
//     * 判断表是否存在；
//     */
//    private void createTableTest() throws IOException {
//        String tableName = "ibm_space:ibm_user";
//        dao.createTable(tableName, "info", "data");
//
//        //如果条件的真假与预期相同，程序继续运行，否则抛出异常，不会打印报错信息。
//        Assert.assertTrue(dao.tableExists(tableName));
//    }
//
//    /**
//     * 插入数据；
//     * 查询某一行指定列族，指定列的值
//     */
//    private void insertRecordTest() throws IOException {
//        String tableName = "ibm_space:ibm_user";
//        String columnFamily = "info";
//        String rowKey1 = "1001";
//        String rowKey = "1002";
//        dao.insertRecord(tableName, rowKey1, columnFamily, "userName", "小冯");
//        dao.insertRecord(tableName, rowKey, columnFamily, "adminName", "admin");
//
//        //比较实际值与预期值是否一致。如果一致，程序继续运行，否则抛出异常，会打印报错信息。常用断言方法，便于调试
//        /*Assert.assertEquals("小冯", dao.selectValue(tableName, rowKey1, columnFamily, "loginName"));
//        Assert.assertNotNull("admin", dao.selectValue(tableName, rowKey, columnFamily, "adminName"));*/
//    }
//
//    /**
//     * 插入多个列；
//     * 扫描全表
//     */
//    private void insertRecordsTest() throws IOException {
//        String tableName = "ibm_space:ibm_user";
//        String columnFamily = "data";
//
//        String[] columns = {"name", "addr"};
//        String[] values = {"tom", "天津"};
//        dao.insertRecords(tableName, "1002", columnFamily, columns, values);
//
//        String result = dao.scanAllRecord(tableName);
//        /*Assert.assertTrue(result.contains("fatherName"));
//        Assert.assertTrue(result.contains("montherName"));*/
//    }
//
//    /**
//     * 指定过滤器，扫描表
//     */
//    private void scanByFilterTest() throws IOException {
//        String tableName = "ibm_namespace:ibm_user";
//
//        // 列过滤器
//        Filter filter = new QualifierFilter(CompareOperator.EQUAL, new BinaryComparator(Bytes.toBytes("father")));
//        // 扫描到指定列
//        dao.scanByFilter(tableName, filter)
//                .forEach(cell -> {
//                    Assert.assertTrue(new String(cell.getValueArray()).contains("fatherName"));
//                    Assert.assertTrue(new String(cell.getValueArray()).contains("father"));
//                });
//    }
//
//    /**
//     * 删除列族；
//     * 查询行；
//     */
//    private void deleteColumnTest() throws IOException {
//        String tableName = "ibm_space:ibm_user";
//
//        dao.deleteColumnFamily(tableName, "1001", "info");
//    }
//
//    /**
//     * 删除表；
//     * 判断表是否存在
//     */
//    private void deleteTableTest() throws IOException {
//        String tableName = "ibm_namespace:ibm_user";
//        dao.deleteTable(tableName);
//
//        Assert.assertTrue(!dao.tableExists(tableName));
//    }
//}
//
//
//
