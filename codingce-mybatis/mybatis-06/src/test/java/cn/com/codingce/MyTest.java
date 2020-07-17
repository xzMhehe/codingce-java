package cn.com.codingce;

import cn.com.codingce.dao.StudentMapper;
import cn.com.codingce.dao.TeacherMapper;
import cn.com.codingce.pojo.Student;
import cn.com.codingce.pojo.Teacher;
import cn.com.codingce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void getTeachers() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //底层主要应用反射
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        sqlSession.close();
    }

    @Test
    public void getStudents() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //底层主要应用反射
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudent2();
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }

}
