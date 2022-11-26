package cn.com.codingce;

import cn.com.codingce.dao.UserMapper;
import cn.com.codingce.pojo.User;
import cn.com.codingce.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void getUserByRowBounds() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //通过RowBounds实现
        RowBounds rowBounds = new RowBounds(1, 3);
        //通过Java代码层面实现分页
        List<User> userList = sqlSession.selectList("cn.com.codingce.dao.UserMapper.getUserByRowBounds", null, rowBounds);
        for (User u : userList
        ) {
            System.out.println(u);
        }
        sqlSession.close();
    }

    @Test
    public void getUserLimit() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", 0);
        map.put("pageSize", 3);
        List<User> userList = userMapper.getUserByLimit(map);
        for (User u : userList
        ) {
            System.out.println(u);
        }
        sqlSession.close();
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
    public void testLog4j() {
        logger.info("info");
        logger.debug("debug");
        logger.error("error");
    }

}
