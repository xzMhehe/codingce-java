package cn.com.codingce.mapper;

import cn.com.codingce.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> selectUser();

    public int addUser(User user);

    public int deleteUser(int id);
}
