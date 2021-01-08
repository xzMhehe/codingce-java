package cn.com.codingce;

import cn.com.codingce.mapper.UserMapper;
import cn.com.codingce.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    /*
        wrapper条件构造器, 这里我们先不用 null
        查询全部用户
     */
    @Test
    void contextLoads() {
        //查询条件name和邮箱不为空的用户, 年龄大于等于12的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println); //对比Map
    }

    @Test
    public void Test() {
        //查询名字 全栈自学社区
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "全栈自学社区");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void Test3() {
        //年龄在20~30岁之间的人
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void Test4() {
        //名字中不包含
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name", "e")
                .likeRight("email", "t");   //左和右   %t%         likeRight("email", "t")         t%
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void Test5() {
        //id在子查询查出来
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 3");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void Test6() {
        //排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

}
