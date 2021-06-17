package cn.com.codingce.service;


import cn.com.codingce.entity.Student;

import java.util.List;


/**
 * @author org
 */
public interface StudentService {

    /**
     * 查询数据总条数
     *
     * @return
     */
    int getTotalCount();

    /**
     * 查询当前数据总条数
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Student> queryStudentByPage(int currentPage, int pageSize);

    /**
     * 查询全部学生
     *
     * @return
     */
    List<Student> queryAllStudents();

    /**
     * 根据学号查询一个人
     *
     * @param sno
     * @return
     */
    Student queryStudentBySno(int sno);

    /**
     * 修改学生
     *
     * @param sno
     * @param student
     * @return
     */
    boolean updateStudentBySno(int sno, Student student);

    boolean deleteStudentBySno(int sno);

    boolean addStudent(Student student);
}
