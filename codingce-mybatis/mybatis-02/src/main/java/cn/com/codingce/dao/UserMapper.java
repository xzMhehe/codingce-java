package cn.com.codingce.dao;

import cn.com.codingce.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    //根据ID查询用户
    User getUserById(int id);

    //insert一个用户
    int addUser(User user);

    //修改
    int updateUser(User user);

    //删除
    int deleteUser(int id);
}
