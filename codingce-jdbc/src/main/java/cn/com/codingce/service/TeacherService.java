package cn.com.codingce.service;

import cn.com.codingce.entity.Teacher;

import java.util.List;

/**
 * @author williamma
 */
public interface TeacherService {

    int login(Teacher teacher);

    int getTotalCount();

    List<Teacher> queryAllTeachers();

    Teacher queryLoginByPid(int pid);

    boolean updateTeacherByPid(int pid, Teacher login);

    boolean addTeacher(Teacher teacher);

    boolean deleteTeacherByPid(int pid);
}
