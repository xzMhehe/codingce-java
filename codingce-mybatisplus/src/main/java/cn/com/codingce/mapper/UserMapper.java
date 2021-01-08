package cn.com.codingce.mapper;

import cn.com.codingce.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 在对应的Mapper上实现基本接口BaseMapper
 * @author xzMa
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD操作都已经编写完成
}
