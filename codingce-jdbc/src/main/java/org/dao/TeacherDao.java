package org.dao;


import org.entity.Teacher;

import java.util.List;


/**
 * @author org
 */
public interface TeacherDao {

    int login(Teacher login);

    /**
     * 查询总数据数
     *
     * @return
     */
    int getTotalCount();

    /**
     * 查询老师
     *
     * @return
     */
    List<Teacher> queryAllTeachers();

    /**
     * 根据工号删除老师
     *
     * @param pid
     * @return
     */
    boolean deleteTeacherByPid(int pid);

    /**
     * 根据id 查
     *
     * @param pid
     * @return
     */
    Teacher queryLoginByPid(int pid);

    boolean updateTeacherByPid(int pid, Teacher teacher);

    boolean addTeacher(Teacher teacher);

}
