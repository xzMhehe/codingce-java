package org;

import cn.com.codingce.entity.Student;
import cn.com.codingce.entity.Teacher;
import cn.com.codingce.entity.Users;
import cn.com.codingce.service.StudentService;
import cn.com.codingce.service.TeacherService;
import cn.com.codingce.service.UsersService;
import cn.com.codingce.service.impl.StudentServiceImpl;
import cn.com.codingce.service.impl.TeacherServiceImpl;
import cn.com.codingce.service.impl.UsersServiceImpl;
import cn.com.codingce.util.MyPage;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author williamma
 */
public class MyTest {

    private StudentService studentService = new StudentServiceImpl();

    private UsersService usersService = new UsersServiceImpl();

    private TeacherService teacherService = new TeacherServiceImpl();

    @Test
    public void queryAllUsers() {
        usersService.queryAllUsers().forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void saveAllUsers() throws IOException {
        List<Users> users = usersService.queryAllUsers();
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("mxz.txt"));

        // 遍历集合
        for (Users s : users) {
            // 写数据
            bw.write(s.toString());
            bw.newLine();
            bw.flush();
        }

        // 释放资源
        bw.close();
    }

    @Test
    public void queryAllUsersByName() {
        usersService.queryAllUsersByName("里").forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void addUsers() {
        Users users = new Users();
        users.setSno(10100);
        users.setSname("呵呵");
        users.setSage(20);
        users.setSaddress("中国");
        System.out.println(usersService.addUsers(users));
    }

    @Test
    public void getUsersTotalCount() {
        System.out.println(usersService.getTotalCount());
    }

    @Test
    public void deleteUsersBySno() {
        System.out.println(usersService.deleteUsersBySno(10100));
    }

    @Test
    public void queryUsersBySno() {
        System.out.println(usersService.queryUsersBySno(1001).toString());
    }

    @Test
    public void queryUsersByPage() {
        System.out.println(usersService.queryUsersByPage(1, 10));
    }

    @Test
    public void updateUsersBySno() {
        Users users = new Users();
        users.setSno(1003);
        users.setSname("细细");
        users.setSage(19);
        users.setSaddress("天津");
        System.out.println(usersService.updateUsersBySno(1003, users));
    }

    /**
     * ===================================================
     **/

    @Test
    public void queryAllStudents() {
        studentService.queryAllStudents().forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setSno(1003);
        student.setSname("里斯");
        student.setSage(19);
        student.setSaddress("天津");
        System.out.println(studentService.addStudent(student));
    }

    @Test
    public void getTotalCount() {
        System.out.println(studentService.getTotalCount());
    }

    @Test
    public void deleteStudentBySno() {
        System.out.println(studentService.deleteStudentBySno(1002));
    }

    @Test
    public void queryStudentBySno() {
        System.out.println(studentService.queryStudentBySno(1001).toString());
    }

    @Test
    public void queryStudentByPage() {
        System.out.println(studentService.queryStudentByPage(1, 10));
    }

    @Test
    public void updateStudentBySno() {
        Student student = new Student();
        student.setSno(1010);
        student.setSname("里斯");
        student.setSage(19);
        student.setSaddress("河北");
        System.out.println(studentService.updateStudentBySno(1001, student));
    }

    @Test
    public void queryAllTeachers() {
        teacherService.queryAllTeachers().forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void login() {
        Teacher teacher = new Teacher();
        teacher.setTname("1001");
        teacher.setTpwd("admin");
        System.out.println(teacherService.login(teacher));
    }

    @Test
    public void addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setTname("1002");
        teacher.setTpwd("admin");
        System.out.println(teacherService.addTeacher(teacher));
    }

    @Test
    public void deleteTeacherByPid() {
        System.out.println(teacherService.deleteTeacherByPid(1));
    }

    @Test
    public void updateTeacherByPid() {
        Teacher teacher = new Teacher();
        teacher.setTname("1005");
        teacher.setPid(10);
        teacher.setTpwd("haha");
        System.out.println(teacherService.updateTeacherByPid(2, teacher));
    }

    @Test
    public void queryLoginById() {
        System.out.println(teacherService.queryLoginByPid(10).toString());
    }

    @Test
    public void studentPage() {
        Map<Integer, Object> map = new HashMap<>();
        studentService.queryAllStudents().forEach(e -> {
            map.put(e.getPid(), e);
        });

        System.out.println(map.size());

        MyPage myPage = new MyPage();
        myPage.setData(map);
        myPage.setCurrentPage(1);
        myPage.setPageSize(2);

        System.out.println(myPage.toString());
    }

    @Test
    public void teacherPage() {
        Map<Integer, Object> map = new HashMap<>();
        teacherService.queryAllTeachers().forEach(e -> {
            map.put(e.getPid(), e);
        });

        System.out.println(map.size());

        MyPage myPage = new MyPage();
        myPage.setData(map);
        myPage.setCurrentPage(1);
        myPage.setPageSize(2);

        System.out.println(myPage.toString());
    }
}
