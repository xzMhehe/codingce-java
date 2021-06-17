package org.service.impl;


import org.dao.TeacherDao;
import org.dao.impl.TeacherDaoImpl;
import org.entity.Teacher;
import org.service.TeacherService;

import java.util.List;


/**
 * 业务逻辑层  逻辑性的增、删、改(增:查+增，   对dao层进行的组装)
 *
 * @author org
 */
public class TeacherServiceImpl implements TeacherService {

    TeacherDao tea = new TeacherDaoImpl();

    @Override
    public int login(Teacher teacher) {
        return tea.login(teacher);
    }

    @Override
    public int getTotalCount() {
        return tea.getTotalCount();
    }

    @Override
    public List<Teacher> queryAllTeachers() {
        return tea.queryAllTeachers();
    }

    @Override
    public Teacher queryLoginByPid(int pid) {
        return tea.queryLoginByPid(pid);
    }

    @Override
    public boolean updateTeacherByPid(int pid, Teacher login) {
        return tea.updateTeacherByPid(pid, login);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return tea.addTeacher(teacher);
    }

    @Override
    public boolean deleteTeacherByPid(int pid) {
        // TODO Auto-generated method stub
        return tea.deleteTeacherByPid(pid);
    }

}
