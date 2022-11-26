package cn.com.codingce;

import cn.com.codingce.dao.UserMapper;
import cn.com.codingce.pojo.User;
import cn.com.codingce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void getUsers() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> userList = mapper.getUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryUserById(){
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
        mapper.updateUser(new User(2, "aaaaaa", "bbbbbb"));
        System.out.println("====================================");
        User user2 = mapper.queryUserById(1);
        System.out.println(user2);
        System.out.println(user==user2);
        session.close();
    }

    @Test
    public void test() {
        SqlSession session = MybatisUtils.getSession();

        SqlSession session2 = MybatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        UserMapper mapper2 = session2.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);

        User user2 = mapper2.queryUserById(1);

        System.out.println(user);
        System.out.println("===================");
        System.out.println(user2);

        session.close();
        session2.close();
    }
}
