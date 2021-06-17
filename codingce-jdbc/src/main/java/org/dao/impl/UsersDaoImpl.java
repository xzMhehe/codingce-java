package org.dao.impl;


import org.dao.UsersDao;
import org.entity.Users;
import org.util.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 数据访问层 原子性的增删改查
 *
 * @author org
 */
public class UsersDaoImpl implements UsersDao {

    /**
     * @param sno
     * @param users
     * @return
     */
    @Override
    public boolean updateUsersBySno(int sno, Users users) {
        String sql = "update users set sno=?,sname=?,sage=?,saddress=? where sno = ?";
        Object[] params = {users.getSno(), users.getSname(), users.getSage(), users.getSaddress(), sno};
        return Dbutil.executeUpdate(sql, params);
    }


    /**
     * @param sno
     * @return
     */
    @Override
    public boolean deleteUsersBySno(int sno) {

        String sql = "delete from users where sno = ?";
        Object[] params = {sno};
        return Dbutil.executeUpdate(sql, params);
    }

    /**
     * @param users
     * @return
     */
    @Override
    public boolean addUsers(Users users) {
        String sql = "INSERT INTO users(sno, sname, sage, saddress) values(?, ?, ?, ?)";
        Object[] params = {users.getSno(), users.getSname(), users.getSage(), users.getSaddress()};
        return Dbutil.executeUpdate(sql, params);
    }

    /**
     * 判断是否存在
     *
     * @param sno
     * @return
     */
    @Override
    public boolean isExist(int sno) {// true此人存在 false 不存在
        return queryUsersBySno(sno) == null ? false : true;
    }


    /**
     * @return
     */
    @Override
    public List<Users> queryAllUsers() {
        List<Users> usersList = new ArrayList<>();
        Users users = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users";
            rs = Dbutil.executeQuery(sql, null);

            while (rs.next()) {
                int pid = rs.getInt("pid");
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                users = new Users(pid, no, name, age, address);
                usersList.add(users);
            }
            return usersList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Dbutil.closeAll(rs, pstmt, Dbutil.connection);
        }
    }

    /**
     * @return
     */
    @Override
    public List<Users> queryAllUsersByName(String username) {
        List<Users> usersList = new ArrayList<>();
        Users users = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users WHERE sname LIKE CONCAT('%', ?,'%')";
            Object[] params = {username};
            rs = Dbutil.executeQuery(sql, params);

            while (rs.next()) {
                int pid = rs.getInt("pid");
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                users = new Users(pid, no, name, age, address);
                usersList.add(users);
            }
            return usersList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Dbutil.closeAll(rs, pstmt, Dbutil.connection);
        }
    }

    /**
     * 查询总数据数
     *
     * @return
     */
    @Override
    public int getTotalCount() {
        String sql = "select count(1) from student";
        return Dbutil.getTotalCount(sql);
    }

    /**
     * 查询数据  页数    页面大小
     *
     * @param currentPage
     * @param pagesSize
     * @return
     */
    @Override
    public List<Users> queryUsersByPage(int currentPage, int pagesSize) {
        List<Users> usersList = new ArrayList<>();
        System.out.println(currentPage + " " + pagesSize);
        String sql = "select * from users order by sno limit ?, ?";
        Object[] params = {currentPage - 1, pagesSize};
        ResultSet rs = Dbutil.executeQuery(sql, params);
        try {
            while (rs.next()) {
                Users student = new Users(rs.getInt("pid"), rs.getInt("sno"), rs.getString("sname"), rs.getInt("sage"),
                        rs.getString("saddress"));
                usersList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersList;
    }

    /**
     * 根据学号 查询生
     *
     * @param sno
     * @return
     */
    @Override
    public Users queryUsersBySno(int sno) {
        Users users = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Dbutil.getConnection();
            // 执行操作
            String sql = "SELECT * FROM users where sno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int pid = rs.getInt("pid");
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                users = new Users(pid, no, name, age, address);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
