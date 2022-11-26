package cn.com.codingce.dao;

import cn.com.codingce.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    //获取老师信息
    //List<Teacher> getTeacher();

    //获取指定老师下所有学生信息
    Teacher getTeacher(@Param("id") int id);

    Teacher getTeacher2(@Param("id") int id);

}
