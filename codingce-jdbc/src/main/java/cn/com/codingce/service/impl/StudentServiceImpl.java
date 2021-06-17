package cn.com.codingce.service.impl;


import cn.com.codingce.dao.StudentDao;
import cn.com.codingce.dao.impl.StudentDaoImpl;
import cn.com.codingce.entity.Student;
import cn.com.codingce.service.StudentService;

import java.util.List;


/**
 * 业务逻辑层  逻辑性的增、删、改(增:查+增，   对dao层进行的组装)
 *
 * @author williamma
 */
public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> queryAllStudents() {
        return studentDao.queryAllStudents();
    }

    @Override
    public Student queryStudentBySno(int sno) {
        return studentDao.queryStudentBySno(sno);
    }

    @Override
    public boolean updateStudentBySno(int sno, Student student) {
        if (studentDao.isExist(sno)) {
            return studentDao.updateStudentBySno(sno, student);
        }
            return false;
    }

    @Override
    public boolean deleteStudentBySno(int sno) {
        if (studentDao.isExist(sno))
            return studentDao.deleteStudentBySno(sno);
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        if (!studentDao.isExist(student.getSno())) {
            studentDao.addStudent(student);
            return true;
        }
        System.out.println("此人已存在!");
        return false;
    }

    @Override
    public List<Student> queryStudentByPage(int currentPage, int pageSize) {
        return studentDao.queryStudentsByPage(currentPage, pageSize);
    }

    @Override
    public int getTotalCount() {
        return studentDao.getTotalCount();
    }
}
