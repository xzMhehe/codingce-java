package cn.com.codingce.dao;

import cn.com.codingce.pojo.User;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    //根据ID查询用户
    User getUserById(int id);

    User getUserByIdTwo(Map<String, Object> map);

    //模糊查询
    List<User> getUserListLike(String name);

    //insert一个用户
    int addUser(User user);

    int addUserTwo(Map<String, Object> map);

    //注解插入
    @Insert(" insert into mybatis.user(id, name, pwd) values (#{id}, #{name}, #{pwd})")
    int insertUser(User user);

    //修改
    int updateUser(User user);

    //删除
    int deleteUser(int id);
}
