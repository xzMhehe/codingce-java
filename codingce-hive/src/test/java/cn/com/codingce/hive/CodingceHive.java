package cn.com.codingce.hive;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class CodingceHive {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://hadoop104:10000/userdb";
    private static String user = "hive2";
    private static String password = "password";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // 加载驱动、创建连接
    @Before
    public void init() throws Exception {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }

    // 创建数据库
    @Test
    public void createDatabase() throws Exception {
        String sql = "create database hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询所有数据库
    @Test
    public void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 创建表
    @Test
    public void createTable() throws Exception {
        String sql = "create table emp(\n" +
                "empno int,\n" +
                "ename string,\n" +
                "job string,\n" +
                "mgr int,\n" +
                "hiredate string,\n" +
                "sal double,\n" +
                "comm double,\n" +
                "deptno int\n" +
                ")\n" +
                "row format delimited fields terminated by '\\t'";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询所有表
    @Test
    public void showTables() throws Exception {
        String sql = "show tables";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 查看表结构
    @Test
    public void descTable() throws Exception {
        String sql = "desc emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
    }

    // 加载数据
    @Test
    public void loadData() throws Exception {
        String filePath = "/home/hadoop/data/emp.txt";
        String sql = "load data local inpath '" + filePath + "' overwrite into table emp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询数据
    @Test
    public void selectData() throws Exception {
        String sql = "select * from emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        System.out.println("员工编号" + "\t" + "员工姓名" + "\t" + "工作岗位");
        while (rs.next()) {
            System.out.println(rs.getString("empno") + "\t\t" + rs.getString("ename") + "\t\t" + rs.getString("job"));
        }
    }

    // 统计查询（会运行mapreduce作业）
    @Test
    public void countData() throws Exception {
        String sql = "select count(1) from emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1));
        }
    }

    // 删除数据库
    @Test
    public void dropDatabase() throws Exception {
        String sql = "drop database if exists hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 删除数据库表
    @Test
    public void deopTable() throws Exception {
        String sql = "drop table if exists emp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 释放资源
    @After
    public void destory() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
