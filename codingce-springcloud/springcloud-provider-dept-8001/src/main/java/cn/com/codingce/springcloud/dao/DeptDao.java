package cn.com.codingce.springcloud.dao;

import cn.com.codingce.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {

    /**
     * @param dept
     * @return
     */
    public boolean addDept(Dept dept);

    /**
     * @param id
     * @return
     */
    public Dept queryById(Long id);

    /**
     * @return
     */
    public List<Dept> queryAll();

}
