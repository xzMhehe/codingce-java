package cn.com.codingce.dao.impl;


import cn.com.codingce.dao.TeacherDao;
import cn.com.codingce.entity.Teacher;
import cn.com.codingce.util.Dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO层
 *
 * @author williamma
 */
public class TeacherDaoImpl implements TeacherDao {

    /**
     * @param login
     * @return
     */
    @Override
    public int login(Teacher login) {
        // boolean fag = false;
        // -1系统异常 0用户名密码错误 1正确
        int flag = -1;
        int result = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            conn = Dbutil.getConnection();
            // 执行操作
            String sql = "SELECT * FROM teacher where tname = ? and tpwd = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login.getTname());
            pstmt.setString(2, login.getTpwd());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询总数据数
     *
     * @return
     */
    @Override
    public int getTotalCount() {
        String sql = "select count(1) from login";
        return Dbutil.getTotalCount(sql);
    }

    /**
     * 查询全部老师
     *
     * @return
     */
    @Override
    public List<Teacher> queryAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM teacher";
            rs = Dbutil.executeQuery(sql, null);

            while (rs.next()) {

                int id = rs.getInt("pid");
                String uname = rs.getString("tname");

                String upwd = rs.getString("tpwd");
                teacher = new Teacher(id, uname, upwd);
                teachers.add(teacher);
            }
            return teachers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Dbutil.closeAll(rs, pstmt, Dbutil.connection);
        }
    }

    /**
     * 根据工号删除老师
     *
     * @param pid
     * @return
     */
    @Override
    public boolean deleteTeacherByPid(int pid) {
        String sql = "delete from teacher where pid = ?";
        Object[] params = {pid};
        return Dbutil.executeUpdate(sql, params);
    }

    /**
     *
     * @param pid
     * @return
     */
    @Override
    public Teacher queryLoginByPid(int pid) {
        Teacher tea = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Dbutil.getConnection();
            // 执行操作
            String sql = "SELECT * FROM teacher where pid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int tPid = rs.getInt("pid");
                String tname = rs.getString("tname");
                String tpwd = rs.getString("tpwd");
                tea = new Teacher(tPid, tname, tpwd);
            }
            return tea;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改教师
     *
     * @param pid
     * @param teacher
     * @return
     */
    @Override
    public boolean updateTeacherByPid(int pid, Teacher teacher) {
        String sql = "update teacher set pid=?, tname=?, tpwd=? where pid = ?";
        Object[] params = {teacher.getPid(), teacher.getTname(), teacher.getTpwd(), pid};
        return Dbutil.executeUpdate(sql, params);
    }

    /**
     * @param tea
     * @return
     */
    @Override
    public boolean addTeacher(Teacher tea) {
        String sql = "INSERT INTO teacher(tname, tpwd) values(?, ?)";
        Object[] params = {tea.getTname(), tea.getTpwd()};
        return Dbutil.executeUpdate(sql, params);
    }
}
