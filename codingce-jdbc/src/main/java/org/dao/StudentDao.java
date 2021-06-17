package org.dao;


import org.entity.Student;

import java.util.List;

/**
 * @author org
 */
public interface StudentDao {

    /**
     * 修改学生  根据学号找到学生
     *
     * @param sno
     * @param student
     * @return
     */
    boolean updateStudentBySno(int sno, Student student);

    /**
     * 查询总数据数
     *
     * @return
     */
    int getTotalCount();

    /**
     * 查询页    页码     每页显示的数(页面大小)
     *
     * @param currentPage
     * @param pagesSize
     * @return
     */
    List<Student> queryStudentsByPage(int currentPage, int pagesSize);

    /**
     * 根据学号删除学生
     *
     * @param sno
     * @return
     */
    boolean deleteStudentBySno(int sno);

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    boolean addStudent(Student student);


    /**
     * 查询全部学生
     *
     * @return
     */
    List<Student> queryAllStudents();


    /**
     * 判断是否存在
     *
     * @param sno
     * @return
     */
    boolean isExist(int sno);


    /**
     * 根据学号  查询生
     *
     * @param sno
     * @return
     */
    Student queryStudentBySno(int sno);
}
