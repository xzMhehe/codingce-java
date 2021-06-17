package cn.com.codingce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库常用的增删改查
 *
 * @author williamma
 */
public class Dbutil {

    private static final String URL = "jdbc:mysql://localhost:3306/mxz?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String USENAME = "root";
    private static final String PASSWORD = "1234567890";
    public static Connection connection = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;

    /**
     * 获取数量
     *
     * @param sql sql
     * @param sql
     * @return
     */
    public static int getTotalCount(String sql) {
        int count = -1;
        try {
            pstmt = createPreparedStatement(sql, null);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, connection);
        }
        return count;
    }

    /**
     * 通用的增删改
     *
     * @param sql
     * @param params
     * @return
     */
    public static boolean executeUpdate(String sql, Object[] params) {
        try {
            // 执行操作
            pstmt = createPreparedStatement(sql, params);
            int count = pstmt.executeUpdate();
            if (count > 0)
                return true;
            else
                return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll(null, pstmt, connection);
        }
    }

    /**
     * 通用查 返回的是一个集合
     *
     * @param sql
     * @param params
     * @return
     */
    public static ResultSet executeQuery(String sql, Object[] params) {
        try {
            pstmt = createPreparedStatement(sql, params);
            rs = pstmt.executeQuery();
            return rs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        /*
         * 你要用rs不能关
         */
    }

    /**
     * 连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USENAME, PASSWORD);

    }

    public static PreparedStatement createPreparedStatement(String sql, Object[] params) throws ClassNotFoundException, SQLException {
        pstmt = getConnection().prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    /**
     * 关闭
     *
     * @param rs
     * @param stmt
     * @param connection
     */
    public static void closeAll(ResultSet rs, Statement stmt, Connection connection) {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
