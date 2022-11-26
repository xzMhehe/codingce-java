package cn.com.codingce.dao;

import cn.com.codingce.pojo.User;
import cn.com.codingce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    @Test
    public void getUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();
        for (User u : userList) {
            System.out.println(u);
        }
        sqlSession.close();
    }

    @Test
    public void test() {
        //第一步: 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            //方式一:getMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();
            //方式二:
//            List<User> userList = sqlSession.selectList("cn.com.codingce.dao.UserDao.getUserList");
            User user = userMapper.getUserById(1);
            System.out.println(user);
            for (User u:userList
            ) {
                System.out.println(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(6);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.addUser(new User(8, "李飞", "123456"));
        System.out.println(i);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int res = userMapper.updateUser(new User(5, "bytedance", "123456"));
        if (res > 0) {
            System.out.println("修改成功");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int res = userMapper.deleteUser(4);
        if (res > 0) {
            System.out.println("删除成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
