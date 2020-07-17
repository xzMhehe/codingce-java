package cn.com.codingce.dao;

import cn.com.codingce.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    //方法存在多个参数, 所有参数前面添加@Param("字段名")
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

    //添加一个用户
    @Insert("insert into user (id,name,pwd) values (#{id},#{name},#{password})")
    int addUser(User user);

    //修改一个用户
    @Update("update user set name=#{name},pwd=#{password} where id = #{id}")
    int updateUser(User user);

    //根据id删除用
    @Delete("delete from user where id = #{id}")
    int deleteUser(@Param("id")int id);

}
