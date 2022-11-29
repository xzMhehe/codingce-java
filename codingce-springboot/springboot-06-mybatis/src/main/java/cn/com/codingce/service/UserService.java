package cn.com.codingce.service;

import cn.com.codingce.mapper.UserMapper;
import cn.com.codingce.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

}
