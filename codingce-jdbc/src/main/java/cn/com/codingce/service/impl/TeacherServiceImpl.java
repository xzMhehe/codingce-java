package cn.com.codingce.service.impl;

import cn.com.codingce.dao.TeacherDao;
import cn.com.codingce.dao.impl.TeacherDaoImpl;
import cn.com.codingce.entity.Teacher;
import cn.com.codingce.service.TeacherService;

import java.util.List;

/**
 * 业务逻辑层service  逻辑性的增、删、改(增:查+增，   对dao层进行的组装)
 *
 * @author williamma
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
