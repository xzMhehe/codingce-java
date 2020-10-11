package cn.com.codingce;

import cn.com.codingce.mapper.UserMapper;
import cn.com.codingce.pojo.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /*
    wrapper条件构造器, 这里我们先不用 null
    //查询全部用户
     */
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("掌上编程");
        user.setAge(19);
        user.setEmail("2460798168@qq.com");
        userMapper.insert(user);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setEmail("21211@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void testOptimisticLocker() {
        //查询用户信息
        User user = userMapper.selectById(1L);
        //修改用户信息
        user.setName("xzMhehe");
        user.setEmail("codingce@ce.com");
        //执行更新操作
        userMapper.updateById(user);
    }

    /**
     * 测试乐观锁失败  多线程下
     */
    @Test
    void testOptimisticLocker2() {
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("xzMhehe111");
        user.setEmail("codingce@ce.com");

        //模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("xzMhehe222");
        user2.setEmail("codingce@ce.com");
        userMapper.updateById(user2);

        //自旋锁来多次尝试提交
        userMapper.updateById(user);    //如果没有乐观锁就会覆盖插队线程的值！
    }

    @Test
    public void select() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(System.out::println);
    }

    //条件查询 map
    @Test
    public void testSelectByBathIds() {
        HashMap<String, Object> map = new HashMap<>();
        //自定义要查询
        map.put("name", "xzMhehe222");
        map.put("age", 18);

        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    /**
     * 测试分页
     */
    @Test
    public void testPage() {
        //current参数一：当前页
        //参数二：页面大小
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
    }

    //真删
    @Test
    public void testDelete() {
        userMapper.deleteById(1L);
    }


    //批量删除
    @Test
    public void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
    }

    //通过map删除
    @Test
    public void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "xzMhehe");
        userMapper.deleteByMap(map);
    }
}
