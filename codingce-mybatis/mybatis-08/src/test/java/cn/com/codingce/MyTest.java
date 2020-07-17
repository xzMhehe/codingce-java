package cn.com.codingce;

import cn.com.codingce.dao.BlogMapper;
import cn.com.codingce.pojo.Blog;
import cn.com.codingce.utils.IdUtils;
import cn.com.codingce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyTest {

    @Test
    public void myTest() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IdUtils.getId());
        blog.setAuthor("SQL");
        blog.setTitle("mybatis");
        blog.setCreateTime(new Date());
        blog.setViews(99);
        int i = mapper.addBlog(blog);
        System.out.println(i);
        session.close();
    }
    @Test
    public void queryBlogIf() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "mybatis");
        map.put("author", "SQL");
        List<Blog> blogList = mapper.queryBlogIf(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
        session.close();
    }

    @Test
    public void queryBlogForEach() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        ArrayList<Object> list = new ArrayList<Object>();
        list.add("974f3a24647c44158c1a68ff2d525931");
        list.add("04d3e517967745888792fbc6bc16883f");
        list.add("86317fe6084c49c785e93df77aedcc59");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ids", list);
        List<Blog> blogList = mapper.queryBlogForEach(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
        session.close();
    }

    @Test
    public void queryBlogChoose() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("title", "mybatis");
//        map.put("author", "Java");
        map.put("views", 99);
        List<Blog> blogList = mapper.queryBlogChoose(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
        session.close();
    }

    @Test
    public void updateBlog() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "Spring");
        map.put("author", "123");
        map.put("id", "974f3a24647c44158c1a68ff2d525931");
        int i = mapper.updateBlog(map);
        System.out.println(i);
        session.close();
    }
}
