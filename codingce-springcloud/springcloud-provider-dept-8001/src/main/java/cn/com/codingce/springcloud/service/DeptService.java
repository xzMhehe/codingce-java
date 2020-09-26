package cn.com.codingce.springcloud.service;

import cn.com.codingce.springcloud.pojo.Dept;

import java.util.List;

/**
 * @author xzMa
 */
public interface DeptService{

    public boolean addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();
}
