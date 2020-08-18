package cn.com.codingce.mapper;

import cn.com.codingce.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper : 表示本类是一个 MyBatis 的 Mapper  在启动类@MapperScan("cn.com.codingce.mapper")也行
@Mapper
@Repository
public interface UserMapper {

    // 获取所有人信息
    List<User> queryUserList();

    User queryUserByName(String name);

    // 通过id获得信息
    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

}
