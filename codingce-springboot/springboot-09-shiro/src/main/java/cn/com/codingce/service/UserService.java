package cn.com.codingce.service;

import cn.com.codingce.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> queryUserList();

    public User queryUserByName(String name);

}
