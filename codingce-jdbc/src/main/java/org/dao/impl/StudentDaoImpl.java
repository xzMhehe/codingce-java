package org.dao.impl;


import org.dao.StudentDao;
import org.entity.Student;
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
public class StudentDaoImpl implements StudentDao {

    /**
     * 修改学生 根据学号找到学生
     *
     * @param sno
     * @param student
     * @return
     */
    @Override
    public boolean updateStudentBySno(int sno, Student student) {
        String sql = "update student set sno=?,sname=?,sage=?,saddress=? where sno = ?";
        Object[] params = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress(), sno};
        return Dbutil.executeUpdate(sql, params);
    }


    /**
     * 根据学号删除学生
     *
     * @param sno
     * @return
     */
    @Override
    public boolean deleteStudentBySno(int sno) {

        String sql = "delete from student where sno = ?";
        Object[] params = {sno};
        return Dbutil.executeUpdate(sql, params);
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student(sno, sname, sage, saddress) values(?, ?, ?, ?)";
        Object[] params = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress()};
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
        return queryStudentBySno(sno) == null ? false : true;
    }


    /**
     * 查询全部学生
     *
     * @return
     */
    @Override
    public List<Student> queryAllStudents() {
        List<Student> students = new ArrayList<>();
        Student student = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM student";
            rs = Dbutil.executeQuery(sql, null);

            while (rs.next()) {
                int pid = rs.getInt("pid");
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(pid, no, name, age, address);
                students.add(student);
            }
            return students;

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
    public List<Student> queryStudentsByPage(int currentPage, int pagesSize) {
        List<Student> students = new ArrayList<>();
        System.out.println(currentPage + " " + pagesSize);
        String sql = "select * from student order by sno limit ?, ?";
        Object[] params = {currentPage - 1, pagesSize};
        ResultSet rs = Dbutil.executeQuery(sql, params);
        try {
            while (rs.next()) {
                Student student = new Student(rs.getInt("pid"), rs.getInt("sno"), rs.getString("sname"), rs.getInt("sage"),
                        rs.getString("saddress"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    /**
     * 根据学号 查询生
     *
     * @param sno
     * @return
     */
    @Override
    public Student queryStudentBySno(int sno) {
        Student student = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Dbutil.getConnection();
            // 执行操作
            String sql = "SELECT * FROM student where sno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int pid = rs.getInt("pid");
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(pid, no, name, age, address);
            }
            return student;
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
