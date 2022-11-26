package cn.com.codingce;

import cn.com.codingce.dao.UserMapper;
import cn.com.codingce.pojo.User;
import cn.com.codingce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void getUsers() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //底层主要应用反射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUsers();
        for (User u : userList) {
            System.out.println(u);
        }
        sqlSession.close();
    }
    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //底层主要应用反射
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testAddUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User(10, "掌上编程", "123456");
        mapper.addUser(user);
        session.close();
    }

    @Test
    public void testUpdateUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = new User(6, "天津", "xzzxz");
        mapper.updateUser(user);

        session.close();
    }

    @Test
    public void testDeleteUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.deleteUser(6);
        session.close();
    }

}
